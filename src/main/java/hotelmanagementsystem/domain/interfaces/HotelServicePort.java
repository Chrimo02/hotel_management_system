package hotelmanagementsystem.domain.interfaces;

import hotelmanagementsystem.domain.exceptions.GuestNotFoundException;
import hotelmanagementsystem.domain.exceptions.HotelNotFoundException;
import hotelmanagementsystem.domain.models.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

public interface HotelServicePort {

    Hotel createHotel(String name, String description, HotelLocation hotelLocation) throws RuntimeException;
    boolean deleteHotel(long hotelId) throws HotelNotFoundException;
    Hotel updateHotel(long hotelId, Map<String, String> updates) throws HotelNotFoundException;
    PagedHotels listHotelsFilteredAndPaged(String city, double minRating, int pageNumber, int pageSize);
    Hotel getHotelByHotelId(long hotelId) throws HotelNotFoundException;
    void rateHotel(long guestID, long hotelID, int starRating, String comment)
            throws HotelNotFoundException, GuestNotFoundException;
    List<Room> findAvailableRooms(long hotelID, Class<? extends Room> roomType, LocalDate checkIn, LocalDate checkOut)
            throws HotelNotFoundException;
    List<HotelRating> filterHotelRatings(long hotelID, int starRating, boolean onlyWithComment)
            throws HotelNotFoundException;
}
