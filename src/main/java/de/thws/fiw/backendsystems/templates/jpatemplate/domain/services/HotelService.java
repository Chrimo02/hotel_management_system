package de.thws.fiw.backendsystems.templates.jpatemplate.domain.services;
import de.thws.fiw.backendsystems.templates.jpatemplate.domain.models.*;

import java.lang.reflect.Constructor;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class HotelService {

    //Alle verfügbare Zimmer: List<Room> availableRooms = hotelService.findAvailableRooms(hotel, Room.class);
    //Aufruf: List<Room> availableSingleRooms = hotelService.findAvailableRooms(hotel, SingleRoom.class);
    public List<Room> findAvailableRooms(Hotel hotel, Class<? extends Room> roomType) {
        RoomService roomService = new RoomService();
        List<Room> availableRooms = new ArrayList<>();
        for (Room room : hotel.getRooms()) {
            // Überprüfen, ob das Zimmer verfügbar ist und ob der Raumtyp übereinstimmt (falls ein Typ angegeben wurde)
            if (roomService.isAvailable(room, LocalDate.now(), LocalDate.now().plusDays(1)) && (roomType == null || roomType.isInstance(room))) {
                availableRooms.add(room);
            }
        }
        return availableRooms;
    }


    public void addRoom(Class<? extends Room> roomClass, long id, double pricePerNight, boolean available, Hotel hotel, String building, int floor, String roomNumber) {
        RoomIdentifier roomIdentifier = new RoomIdentifier(building, floor, roomNumber);

        try {
            // Holt den passenden Konstruktor der Unterklasse
            Constructor<? extends Room> constructor = roomClass.getConstructor(long.class, double.class, boolean.class, RoomIdentifier.class, Hotel.class);
            // Erstellt eine Instanz der Unterklasse mit den angegebenen Parametern
            Room newRoom = constructor.newInstance(id, pricePerNight, available, roomIdentifier, hotel);
            // Fügt den Raum der Zimmerliste des Hotels hinzu
            hotel.addRoom(newRoom);

            //Beispiel Repository speicherung: roomRepository.save(newRoom);

        } catch (Exception e) {
            throw new RuntimeException("Fehler beim Erstellen des Zimmers", e);
        }
    }

    public boolean removeRoom(Hotel hotel, long id){
        //TODO:An DatabaseAdapter (HotelRepository) weiterleiten, der mit der read Methode den Room findet und löschen kann
        try{

            return true;
        }catch(Exception e){
            throw new RuntimeException("RaumID nicht vorhanden");
        }
    }

    public double calculateHotelRating(List<Booking> bookings) {

        if (bookings == null || bookings.isEmpty()) {
            return 0.0; // Wenn keine Buchungen vorliegen, keine Bewertung
        }
        double totalRating = 0;
        int count = 0;
        // Durch alle Buchungen iterieren und Sternebewertung summieren
        for (Booking booking : bookings) {
            HotelRating rating = booking.getRating();
            if (rating != null) {
                totalRating += rating.getStarRating(); // Sternebewertung summieren
                count++;
            }
        }
        // Durchschnittliche Bewertung berechnen
        return count > 0 ? totalRating / count : 0.0;
    }
    public HotelLocation getHotelLocation(Hotel hotel){
        return hotel.getLocation();
    }

    //TODO: HotelRatings-Filter Methode nach Sternen und nach Comment (true / false)

    /**
     * Filters hotel ratings based on the specified star rating and comment presence.
     *
     * @param hotel the hotel whose bookings are being filtered
     * @param starRating the minimum star rating to filter by
     * @param onlyWithComment if true, only include ratings with comments; if false, only include ratings without comments;
     *                    if null, include all ratings that meet the star rating requirement regardless of comments
     * @return a list of HotelRating objects that match the filtering criteria
     */
    public List<HotelRating> filterHotelRatings(Hotel hotel, int starRating, boolean onlyWithComment) {
        List<Booking> bookings = hotel.getBookings();
        if (bookings.isEmpty()) {
            throw new RuntimeException("No Ratings so far");
        }

        return bookings.stream()
                .map(Booking::getRating)
                .filter(rating -> rating.getStarRating() == starRating)
                .filter(rating -> {
                    // Apply comment filter based on the value of withComment
                    if (onlyWithComment) {
                        return rating.getGuestComment() != null && !rating.getGuestComment().isEmpty();
                    } else {
                        return true;
                    }
                })
                .collect(Collectors.toList());
    }
}

