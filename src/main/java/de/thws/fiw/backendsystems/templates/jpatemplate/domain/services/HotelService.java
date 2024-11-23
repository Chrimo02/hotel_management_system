package de.thws.fiw.backendsystems.templates.jpatemplate.domain.services;
import de.thws.fiw.backendsystems.templates.jpatemplate.domain.models.*;
import de.thws.fiw.backendsystems.templates.jpatemplate.domain.shared.IdGenerator;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class HotelService {
    private final IdGenerator hotelIdGenerator;
    private final IdGenerator locationIdGenerator;
    private final Map<Long, Hotel> hotelMap = new HashMap<>();

    public HotelService(IdGenerator hotelIdGenerator, IdGenerator locationIdGenerator) {
        this.hotelIdGenerator = hotelIdGenerator;
        this.locationIdGenerator = locationIdGenerator;
    }

    public Hotel createHotel(String name, String description, String address, String city, String county){
        validateInputs(name, description, address, city, county);
        HotelLocation hotelLocation = new HotelLocation.HotelLocationBuilder(Long.parseLong(locationIdGenerator.generateId()))
                .withAddress(address)
                .withCity(city)
                .withCountry(county)
                .build();

        Hotel hotel = new Hotel.HotelBuilder(Long.parseLong(hotelIdGenerator.generateId()))
                .withName(name)
                .withDescription(description)
                .withLocation(hotelLocation)
                .build();

        hotelMap.put(hotel.getId(), hotel);
        return hotel;
    }

    public void deleteHotel(Long hotelId) {
        hotelMap.remove(validateHotelId(hotelId).getId());
    }

    public Hotel updateHotel(long hotelId, String name, String description, String address, String city, String country) {
        Hotel hotel = validateHotelId(hotelId);
        if (name != null && !name.isBlank()) {
            hotel.setName(name);
        }
        if (description != null && !description.isBlank()) {
            hotel.setDescription(description);
        }
        if (address != null && !address.isBlank()) {
            hotel.getLocation().setAddress(address);
        }
        if (city != null && !city.isBlank()) {
            hotel.getLocation().setCity(city);
        }
        if (country != null && !country.isBlank()) {
            hotel.getLocation().setCountry(country);
        }
        System.out.println("Hotel with ID " + hotelId + " has been successfully updated.");
        return hotel;
    }

    public List<Hotel> getHotels(){ return new ArrayList<>(hotelMap.values());}

    public Hotel getHotelById(long hotelId) {
        return hotelMap.get(hotelId);
    }

    public HotelLocation getHotelLocation(long id) {
        return hotelMap.get(id).getLocation();
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
        Hotel hotel = validateHotelId(hotelID);
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

    private void validateInputs(String name, String description, String address, String city, String country) {

        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Hotel name must not be empty");
        }
        if (description == null || description.isBlank()) {
            throw new IllegalArgumentException("Hotel description must not be empty");
        }
        if (address == null || address.isBlank()) {
            throw new IllegalArgumentException("Hotel address must not be empty");
        }
        if (city == null || city.isBlank()) {
            throw new IllegalArgumentException("Hotel city must not be empty");
        }
        if (country == null || country.isBlank()) {
            throw new IllegalArgumentException("Hotel country must not be empty");
        }
    }

    private Hotel validateInputs(long guestId, long hotelID, int starRating) {
        Hotel hotel = validateHotelId(hotelID);
        validateGuestId(hotel, guestId);
        validateStarRating(starRating);
        return hotel;
    }

    private Hotel validateInputs(long guestId, long hotelID, int starRating, String comment) {
        Hotel hotel = validateHotelId(hotelID);
        validateGuestId(hotel, guestId);
        validateStarRating(starRating);
        validateComment(comment);
        return hotel;
    }

    private Hotel validateHotelId(long hotelID){
        Hotel hotel = hotelMap.get(hotelID);
        if (hotel == null) {
            throw new IllegalArgumentException("Hotel with ID " + hotelID + " not found!");
        }
        return hotel;
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
