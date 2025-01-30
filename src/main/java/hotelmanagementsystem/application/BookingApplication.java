package hotelmanagementsystem.application;

import hotelmanagementsystem.infrastructure.api.grpc.client.BookingClient;
import hotelmanagementsystem.infrastructure.api.grpc.generated.*;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import java.util.List;

/**
 * BookingApplication - Orchestrates calls between CLI and BookingClient.
 */
@ApplicationScoped
public class BookingApplication {

    @Inject
    BookingClient bookingClient;

    public BookingResponse createBooking(long hotelId, List<String> roomTypes, List<Long> guestIds, String checkInDate, String checkOutDate) {
        CreateBookingRequest request = CreateBookingRequest.newBuilder()
                .setHotelId(hotelId)
                .addAllRoomTypes(roomTypes)
                .addAllGuestIds(guestIds)
                .setCheckInDate(checkInDate)
                .setCheckOutDate(checkOutDate)
                .build();
        return bookingClient.createBooking(request);
    }

    public BookingResponse getBookingById(long bookingId) {
        GetBookingByIdRequest request = GetBookingByIdRequest.newBuilder().setId(bookingId).build();
        return bookingClient.getBookingById(request);
    }

    public void cancelBooking(long bookingId) {
        bookingClient.cancelBooking(bookingId);
    }

    public void checkInGuest(long bookingId) {
        bookingClient.checkInGuest(bookingId);
    }

    public void checkOutGuest(long bookingId) {
        bookingClient.checkOutGuest(bookingId);
    }
}
