package hotelmanagementsystem.domain.services;
import hotelmanagementsystem.domain.exceptions.GuestNotFoundException;
import hotelmanagementsystem.domain.exceptions.HotelNotFoundException;
import hotelmanagementsystem.domain.models.*;
import hotelmanagementsystem.infrastructure.persistence.repositories.interfaces.HotelLocationRepository;
import hotelmanagementsystem.infrastructure.persistence.repositories.interfaces.HotelRatingRepository;
import hotelmanagementsystem.infrastructure.persistence.repositories.interfaces.HotelRepository;
import hotelmanagementsystem.domain.models.Hotel;
import hotelmanagementsystem.domain.models.HotelLocation;
import hotelmanagementsystem.domain.models.HotelRating;
import hotelmanagementsystem.domain.models.Room;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

@ApplicationScoped
public class HotelService {

    private final HotelRepository hotelRepository;
    private final HotelLocationRepository hotelLocationRepository;
    private final HotelRatingRepository hotelRatingRepository;
    private final RoomService roomService;
    private final GuestService guestService;
    @Inject
    public HotelService(RoomService roomService, HotelRepository hotelRepository, HotelLocationRepository hotelLocationRepository, HotelRatingRepository hotelRatingRepository, GuestService guestService) {
        this.hotelRepository = hotelRepository;
        this.hotelLocationRepository = hotelLocationRepository;
        this.hotelRatingRepository = hotelRatingRepository;
        this.roomService = roomService;
        this.guestService = guestService;
    }

    public Hotel createHotel(String name, String description, HotelLocation hotelLocation) throws RuntimeException {
        validateInputs(name, description, hotelLocation);
        Hotel hotel = new Hotel.HotelBuilder()
                .withName(name)
                .withDescription(description)
                .withLocation(hotelLocation)
                .build();
        return hotelRepository.createHotel(hotel);
    }

    public boolean deleteHotel(long hotelId) throws HotelNotFoundException{
        validateHotelId(hotelId);
        hotelRepository.deleteById(hotelId);
        return true;
    }

    public Hotel updateHotel(long hotelId, Map<String, String> updates) throws HotelNotFoundException {
        Hotel hotel = validateHotelIdAndReturnObject(hotelId);
        boolean isUpdated = false;
        for (Map.Entry<String, String> entry : updates.entrySet()) {
            String field = entry.getKey().toLowerCase();
            String value = entry.getValue();
            if (value == null || value.isBlank()) continue;
            switch (field) {
                case "name":
                    if (!value.equals(hotel.getName())) {
                        hotel.setName(value);
                        isUpdated = true;
                    }
                    break;
                case "description":
                    if (!value.equals(hotel.getDescription())) {
                        hotel.setDescription(value);
                        isUpdated = true;
                    }
                    break;
                default:
                    throw new IllegalArgumentException("Invalid field: " + field);
            }
        }

        if (isUpdated) {
            hotelRepository.update(hotel);
            System.out.println("Hotel with ID " + hotelId + " has been successfully updated.");
        } else {
            System.out.println("No changes were made to the hotel with ID " + hotelId + ".");
        }

        return hotel;
    }


    public PagedHotels listHotelsFilteredAndPaged(String city, double minRating, int pageNumber, int pageSize) {
        if (pageNumber < 1) pageNumber = 1;
        if (pageSize < 1) pageSize = 10;
        return hotelRepository.findPagedByFilter(city, minRating, pageNumber, pageSize);
    }


    public Hotel getHotelByHotelId(long hotelId) throws HotelNotFoundException{
        return validateHotelIdAndReturnObject(hotelId);
    }

    public HotelLocation getHotelLocationByHotelId(long hotelId) throws HotelNotFoundException{
        validateHotelId(hotelId);
        return hotelLocationRepository.getHotelLocationByHotelId(hotelId, HotelLocation.class);
    }

    public List<HotelRating> getHotelRatingsByHotelId(long hotelID, int starRating, boolean onlyWithComment) throws HotelNotFoundException {
        List<HotelRating> hotelRatingMap = validateHotelRatings(hotelID);
        return hotelRatingMap.stream().toList();
    }
    public void rateHotel(long guestID, long hotelID, int starRating, String comment)
            throws HotelNotFoundException, GuestNotFoundException {
        // Hotel und Gast laden
        Hotel hotel = validateHotelIdAndReturnObject(hotelID);
        Guest guest = guestService.getGuestById(guestID);

        // Neues Rating erstellen
        HotelRating rating = new HotelRating.Builder()
                .withRating(starRating)
                .withComment(comment != null ? comment : "")
                .withGuest(guest)
                .withHotel(hotel)
                .build();

        hotel.addRating(rating);
        double newAverage = calculateHotelRating(hotel);
        hotel.setAverageRating(newAverage);
        hotelRepository.update(hotel);
    }



    public List<Room> findAvailableRooms(long hotelID, Class<? extends Room> roomType) throws HotelNotFoundException {
        Hotel hotel = validateHotelIdAndReturnObject(hotelID);
        List<Room> availableRooms = new ArrayList<>();
        for (Room room : hotel.getRooms()) {
            boolean available = roomService.isAvailable(room, LocalDate.now(), LocalDate.now().plusDays(1));
            boolean matchesType = (roomType == null || roomType.isInstance(room));
            if (available && matchesType) {
                availableRooms.add(room);
            }
        }
        return availableRooms;
    }

    public List<HotelRating> filterHotelRatings(long hotelID, int starRating, boolean onlyWithComment) throws HotelNotFoundException{
        List<HotelRating> hotelRatingMap = validateHotelRatings(hotelID);
        return hotelRatingMap.stream()
                .filter(rating -> rating.getStarRating() == starRating)
                .filter(rating -> {
                    if (onlyWithComment) {
                        return rating.getGuestComment() != null && !rating.getGuestComment().isEmpty();
                    }
                    return true;
                })
                .collect(Collectors.toList());
    }

    private void validateInputs(String name, String description, HotelLocation hotelLocation) throws RuntimeException {

        if (name == null) {
            throw new IllegalArgumentException("Hotel name must not be empty");
        }
        if (description == null) {
            throw new IllegalArgumentException("Hotel description must not be empty");
        }
        //HotelLocation validieren
    }

    private Hotel validateHotelIdAndReturnObject(long hotelID) throws HotelNotFoundException{
        return hotelRepository.findById(hotelID)
                .orElseThrow(() -> new HotelNotFoundException("Hotel with ID " + hotelID + " not found!"));
    }
    private boolean validateHotelId(long hotelID) throws HotelNotFoundException {
        return hotelRepository.findById(hotelID)
                .map(hotel -> true)
                .orElseThrow(() -> new HotelNotFoundException("Hotel with ID " + hotelID + " not found!"));
    }

    private double calculateHotelRating(Hotel hotel) {
        return hotel.getRatings().stream()
                .filter(Objects::nonNull)
                .mapToDouble(HotelRating::getStarRating)
                .average()
                .orElse(0.0);
    }


    private  List<HotelRating> validateHotelRatings(long hotelID) throws HotelNotFoundException{
        List<HotelRating> ratings = validateHotelIdAndReturnObject(hotelID).getRatings();
        if (ratings.isEmpty()) {
            throw new IllegalArgumentException("No Ratings so far");
        }
        return ratings;
    }

}





