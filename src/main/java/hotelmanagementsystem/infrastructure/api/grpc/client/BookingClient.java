package hotelmanagementsystem.infrastructure.api.grpc.client;

import hotelmanagementsystem.infrastructure.api.grpc.generated.*;
import io.quarkus.grpc.GrpcClient;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

/**
 * BookingClient - Calls the BookingService gRPC methods.
 */
@ApplicationScoped
public class BookingClient {

    @Inject
    @GrpcClient("booking")
    BookingServiceGrpc.BookingServiceBlockingStub bookingStub;

    public BookingResponse createBooking(CreateBookingRequest request) {
        return bookingStub.createBooking(request);
    }

    public BookingResponse getBookingById(GetBookingByIdRequest request) {
        return bookingStub.getBookingById(request);
    }

    public void cancelBooking(long bookingId) {
        CancelBookingRequest request = CancelBookingRequest.newBuilder()
                .setId(bookingId)
                .build();
        bookingStub.cancelBooking(request);
    }

    public void checkInGuest(long bookingId) {
        CheckInGuestRequest request = CheckInGuestRequest.newBuilder()
                .setBookingId(bookingId)
                .build();
        bookingStub.checkInGuest(request);
    }

    public void checkOutGuest(long bookingId) {
        CheckOutGuestRequest request = CheckOutGuestRequest.newBuilder()
                .setBookingId(bookingId)
                .build();
        bookingStub.checkOutGuest(request);
    }
}
