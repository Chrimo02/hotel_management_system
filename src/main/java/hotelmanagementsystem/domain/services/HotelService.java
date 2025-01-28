package hotelmanagementsystem.domain.services;
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
    @Inject
    public HotelService(RoomService roomService, HotelRepository hotelRepository, HotelLocationRepository hotelLocationRepository, HotelRatingRepository hotelRatingRepository) {
        this.hotelRepository = hotelRepository;
        this.hotelLocationRepository = hotelLocationRepository;
        this.hotelRatingRepository = hotelRatingRepository;
        this.roomService = roomService;
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
                case "address":
                    if (!value.equals(hotel.getLocation().getAddress())) {
                        hotel.getLocation().setAddress(value);
                        isUpdated = true;
                    }
                    break;
                case "city":
                    if (!value.equals(hotel.getLocation().getCity())) {
                        hotel.getLocation().setCity(value);
                        isUpdated = true;
                    }
                    break;
                case "country":
                    if (!value.equals(hotel.getLocation().getCountry())) {
                        hotel.getLocation().setCountry(value);
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


    //public List<Hotel> getHotels(){ return hotelRepository.findAll();}

    public PagedHotels listHotelsFilteredAndPaged(
            String city, double minRating,
            int pageNumber, int pageSize
    ) {
        // Validierung, Standardwerte etc.
        if (pageNumber < 1) pageNumber = 1;
        if (pageSize   < 1) pageSize   = 10;

        // Den eigentlichen Datenbankzugriff delegieren wir ans Repository
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
    public void rateHotel(long guestID, long hotelID, HotelRating rating) throws HotelNotFoundException{
        Hotel hotel = validateInputs(hotelID,guestID);
        hotel.addRating(rating);
        hotel.setAverageRating(calculateHotelRating(hotel));
        hotelRepository.update(hotel);
    }
    public List<Room> findAvailableRooms(long hotelID, Class<? extends Room> roomType) throws HotelNotFoundException{
        Hotel hotel = validateHotelIdAndReturnObject(hotelID);
        List<Room> availableRooms = new ArrayList<>();
        for (Room room : hotel.getRooms()) {
            if (roomService.isAvailable(room, LocalDate.now(), LocalDate.now().plusDays(1)) && (roomType == null || roomType.isInstance(room))) {
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

        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Hotel name must not be empty");
        }
        if (description == null || description.isBlank()) {
            throw new IllegalArgumentException("Hotel description must not be empty");
        }
        //HotelLocation validieren
    }
    private Hotel validateInputs(long guestId, long hotelID) throws HotelNotFoundException{
        Hotel hotel = validateHotelIdAndReturnObject(hotelID);
        validateGuestId(hotel, guestId);
        return hotel;
    }
    private Hotel validateHotelIdAndReturnObject(long hotelID) throws HotelNotFoundException{
        return hotelRepository.findById(hotelID)
                .orElseThrow(() -> new HotelNotFoundException("Hotel with ID " + hotelID + " not found!"));
    }
    private boolean validateHotelId(long hotelID) throws HotelNotFoundException{
        return hotelRepository.findById(hotelID)
                .map(hotel -> true)
                .orElseThrow(() -> new HotelNotFoundException("Hotel with ID " + hotelID + " not found!"));
    }
    private void validateGuestId(Hotel hotel, long guestID){
        if (!(hotel.getBookings().stream()
                .flatMap(booking -> booking.getGuests().stream())
                .anyMatch(guest -> guest.getId() == guestID))) {
            throw new IllegalArgumentException("Guest with ID " + guestID + " has no booking in this hotel!");
        }
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





