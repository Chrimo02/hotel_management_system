package de.thws.fiw.backendsystems.templates.jpatemplate.domain.services;
import de.thws.fiw.backendsystems.templates.jpatemplate.domain.models.*;
import de.thws.fiw.backendsystems.templates.jpatemplate.infrastructure.persistence.repositories.interfaces.HotelLocationRepository;
import de.thws.fiw.backendsystems.templates.jpatemplate.infrastructure.persistence.repositories.interfaces.HotelRepository;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

public class HotelService {

    private final HotelRepository hotelRepository;
    private final HotelLocationRepository hotelLocationRepository;

    public HotelService(HotelRepository hotelRepository, HotelLocationRepository hotelLocationRepository) {
        this.hotelRepository = hotelRepository;
        this.hotelLocationRepository = hotelLocationRepository;
    }

    public Hotel createHotel(String name, String description, HotelLocation hotelLocation) throws Exception {
        validateInputs(name, description, hotelLocation);
        Hotel hotel = new Hotel.HotelBuilder()
                .withName(name)
                .withDescription(description)
                .withLocation(hotelLocation)
                .build();
        if(hotelRepository.createHotel(hotel)) return hotel;
        else throw new Exception();
    }

    public void deleteHotel(long hotelId) {
        validateHotelId(hotelId);
        hotelRepository.deleteById(hotelId);
    }

    public Hotel updateHotel(long hotelId, Map<String, String> updates) {
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


    public List<Hotel> getHotels(){ return hotelRepository.findAll();}

    public Hotel getHotelById(long hotelId) {
        return validateHotelIdAndReturnObject(hotelId);
    }

    public HotelLocation getHotelLocation(long hotelId) {
        validateHotelId(hotelId);
        return hotelLocationRepository.getLocationByHotelId(hotelId, );
    }

    public List<HotelRating> getHotelRatings(long hotelID, int starRating, boolean onlyWithComment) {
        Map<Long, HotelRating> hotelRatingMap = validateHotelRatings(hotelID);
        return hotelRatingMap.values().stream().toList();
    }

    public void rateHotel(long guestID, long hotelID, int starRating) {
        Hotel hotel = validateInputs(hotelID,guestID, starRating);
        HotelRating rating = createHotelRating(starRating); // Map starRating to enum
        hotel.addRating(guestID, rating);
        hotel.setAverageRating(calculateHotelRating(hotel.getBookings()));
    }

    public void rateHotel(long guestID, long hotelID, int starRating, String comment) {
        Hotel hotel = validateInputs(hotelID,guestID, starRating, comment);
        HotelRating rating = createHotelRating(starRating); // Map starRating to enum
        rating.setCommentRating(comment);
        hotel.addRating(guestID, rating);
        hotel.setAverageRating(calculateHotelRating(hotel.getBookings()));
    }

    public List<Room> findAvailableRooms(long hotelID, Class<? extends Room> roomType) {
        Hotel hotel = validateHotelIdAndReturnObject(hotelID);
        RoomService roomService = new RoomService();
        List<Room> availableRooms = new ArrayList<>();
        for (Room room : hotel.getRooms()) {
            if (roomService.isAvailable(room, LocalDate.now(), LocalDate.now().plusDays(1)) && (roomType == null || roomType.isInstance(room))) {
                availableRooms.add(room);
            }
        }
        return availableRooms;
    }

    public List<HotelRating> filterHotelRatings(long hotelID, int starRating, boolean onlyWithComment) {
        Map<Long, HotelRating> hotelRatingMap = validateHotelRatings(hotelID);
        return hotelRatingMap.values().stream()
                .filter(rating -> rating.getStarRating() == starRating)
                .filter(rating -> {
                    if (onlyWithComment) {
                        return rating.getGuestComment() != null && !rating.getGuestComment().isEmpty();
                    }
                    return true;
                })
                .collect(Collectors.toList());
    }

    private void validateInputs(String name, String description, HotelLocation hotelLocation) throws Exception {

        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Hotel name must not be empty");
        }
        if (description == null || description.isBlank()) {
            throw new IllegalArgumentException("Hotel description must not be empty");
        }
        //HotelLocation validieren
    }
    private Hotel validateInputs(long guestId, long hotelID, int starRating) {
        Hotel hotel = validateHotelId(hotelID);
        validateGuestId(hotel, guestId);
        validateStarRating(starRating);
        return hotel;
    }
    private Hotel validateInputs(long guestId, long hotelID, int starRating, String comment) {
        Hotel hotel = validateHotelIdAndReturnObject(hotelID);
        validateGuestId(hotel, guestId);
        validateStarRating(starRating);
        validateComment(comment);
        return hotel;
    }
    private Hotel validateHotelIdAndReturnObject(long hotelID) {
        return hotelRepository.findById(hotelID)
                .orElseThrow(() -> new IllegalArgumentException("Hotel with ID " + hotelID + " not found!"));
    }
    private boolean validateHotelId(long hotelID) {
        return hotelRepository.findById(hotelID)
                .orElseThrow(() -> new IllegalArgumentException("Hotel with ID " + hotelID + " not found!"));
    }
    private void validateGuestId(Hotel hotel, long guestID){
        if (!(hotel.getBookings().stream()
                .flatMap(booking -> booking.getGuests().stream())
                .anyMatch(guest -> guest.getId() == guestID))) {
            throw new IllegalArgumentException("Guest with ID " + guestID + " has no booking in this hotel!");
        }
    }
    private void validateStarRating(int starRating) {
        if (starRating < 1 || starRating > 5) {
            throw new IllegalArgumentException("Star rating must be between 1 and 5.");
        }
    }
    private void validateComment(String comment) {
        if (comment == null || comment.isBlank()) {
            throw new IllegalArgumentException("Hotel comment must not be empty");
        }
    }
    private HotelRating createHotelRating(int starRating){
        return HotelRating.values()[starRating - 1];
    }
    private double calculateHotelRating(List<Booking> bookings) {
        double totalRating = 0;
        int count = 0;
        for (Booking booking : bookings) {
            HotelRating rating = booking.getRating();
            if (rating != null) {
                totalRating += rating.getStarRating();
                count++;
            }
        }
        return count > 0 ? totalRating / count : 0.0;
    }
    private  Map<Long, HotelRating> validateHotelRatings(long hotelID) {
        Map<Long, HotelRating> ratings = validateHotelId(hotelID).getRatings();
        if (ratings.isEmpty()) {
            throw new IllegalArgumentException("No Ratings so far");
        }
        return ratings;
    }

}








//    public void addRoom(Class<? extends Room> roomClass, long id, double pricePerNight, boolean available, Hotel hotel, String building, int floor, String roomNumber) {
//        RoomIdentifier roomIdentifier = new RoomIdentifier(building, floor, roomNumber);
//
//        try {
//            // Holt den passenden Konstruktor der Unterklasse
//            Constructor<? extends Room> constructor = roomClass.getConstructor(long.class, double.class, boolean.class, RoomIdentifier.class, Hotel.class);
//            // Erstellt eine Instanz der Unterklasse mit den angegebenen Parametern
//            Room newRoom = constructor.newInstance(id, pricePerNight, available, roomIdentifier, hotel);
//            // Fügt den Raum der Zimmerliste des Hotels hinzu
//            hotel.addRoom(newRoom);
//
//            //Beispiel Repository speicherung: roomRepository.save(newRoom);
//
//        } catch (Exception e) {
//            throw new RuntimeException("Fehler beim Erstellen des Zimmers", e);
//        }
//    }

//    public boolean removeRoom(Hotel hotel, long id){
//        //TODO:An DatabaseAdapter (HotelRepository) weiterleiten, der mit der read Methode den Room findet und löschen kann
//        try{
//
//            return true;
//        }catch(Exception e){
//            throw new RuntimeException("RaumID nicht vorhanden");
//        }
//    }
