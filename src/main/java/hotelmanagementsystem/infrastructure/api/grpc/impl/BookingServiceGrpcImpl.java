package hotelmanagementsystem.infrastructure.api.grpc.impl;
import hotelmanagementsystem.infrastructure.api.grpc.generated.*;
import io.grpc.stub.StreamObserver;


public class BookingServiceGrpcImpl  extends BookingServiceGrpc.BookingServiceImplBase
{
    @Override
    public void createBooking(CreateBookingRequest request, StreamObserver<BookingResponse> responseObserver) {
        super.createBooking(request, responseObserver);
    }

    @Override
    public void getBookingById(GetBookingByIdRequest request, StreamObserver<BookingResponse> responseObserver) {
        super.getBookingById(request, responseObserver);
    }

    @Override
    public void cancelBooking(CancelBookingRequest request, StreamObserver<Empty> responseObserver) {
        super.cancelBooking(request, responseObserver);
    }

    @Override
    public void checkInGuest(CheckInGuestRequest request, StreamObserver<Empty> responseObserver) {
        super.checkInGuest(request, responseObserver);
    }

    @Override
    public void checkOutGuest(CheckOutGuestRequest request, StreamObserver<Empty> responseObserver) {
        super.checkOutGuest(request, responseObserver);
    }
}
