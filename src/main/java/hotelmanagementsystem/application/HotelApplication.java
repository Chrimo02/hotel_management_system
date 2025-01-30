package hotelmanagementsystem.application;

import hotelmanagementsystem.infrastructure.api.grpc.client.HotelClient;
import hotelmanagementsystem.infrastructure.api.grpc.generated.*;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class HotelApplication {

    @Inject
    HotelClient hotelClient;

    public HotelResponse createHotel(String name, String description, HotelLocation location) {
        CreateHotelRequest request = CreateHotelRequest.newBuilder()
                .setName(name)
                .setDescription(description)
                .setLocation(location)
                .build();
        return hotelClient.createHotel(request);
    }

    public HotelResponse getHotelById(long hotelId) {
        GetHotelByIdRequest request = GetHotelByIdRequest.newBuilder()
                .setId(hotelId)
                .build();
        return hotelClient.getHotelById(request);
    }

    public ListHotelsResponse listHotels(String filterCity, double minRating, int pageNumber, int pageSize) {
        ListHotelsRequest request = ListHotelsRequest.newBuilder()
                .setFilterCity(filterCity == null ? "" : filterCity)
                .setMinRating(minRating)
                .setPageNumber(pageNumber)
                .setPageSize(pageSize)
                .build();
        return hotelClient.listHotels(request);
    }

    public HotelResponse updateHotel(long hotelId, String newName, String newDescription, HotelLocation location) {
        UpdateHotelRequest request = UpdateHotelRequest.newBuilder()
                .setId(hotelId)
                .setName(newName)
                .setDescription(newDescription)
                .setLocation(location)
                .build();
        return hotelClient.updateHotel(request);
    }

    public void deleteHotel(long hotelId) {
        DeleteHotelRequest request = DeleteHotelRequest.newBuilder()
                .setId(hotelId)
                .build();
        hotelClient.deleteHotel(request);
    }

    public void rateHotel(long hotelId, long guestId, int rating, String comment) {
        RateHotelRequest request = RateHotelRequest.newBuilder()
                .setHotelId(hotelId)
                .setGuestId(guestId)
                .setRating(rating)
                .setComment(comment)
                .build();
        hotelClient.rateHotel(request);
    }

    public ListRoomsResponse findAvailableRooms(long hotelId, String roomType, String checkInDate, String checkOutDate) {
        FindAvailableRoomsRequest request = FindAvailableRoomsRequest.newBuilder()
                .setHotelId(hotelId)
                .setRoomType(roomType == null ? "" : roomType)
                .setCheckInDate(checkInDate)
                .setCheckOutDate(checkOutDate)
                .build();
        return hotelClient.findAvailableRooms(request);
    }
}
