package hotelmanagementsystem.infrastructure.api.grpc.impl;

import hotelmanagementsystem.domain.exceptions.BookingNotFoundException;
import hotelmanagementsystem.domain.models.Booking;
import hotelmanagementsystem.domain.services.BookingService;
import hotelmanagementsystem.infrastructure.api.dto.BookingDTO;
import hotelmanagementsystem.infrastructure.api.grpc.generated.*;
import hotelmanagementsystem.infrastructure.api.mapper.BookingMapper;
import io.grpc.Status;
import io.grpc.stub.StreamObserver;

import jakarta.inject.Inject;
import java.time.LocalDate;
import java.util.Collections;

public class BookingServiceGrpcImpl extends BookingServiceGrpc.BookingServiceImplBase {

    private final BookingService bookingService;

    @Inject
    public BookingServiceGrpcImpl(BookingService bookingService) {
        this.bookingService = bookingService;
    }

    @Override
    public void createBooking(CreateBookingRequest request, StreamObserver<BookingResponse> responseObserver) {
        try {
            // Create booking in domain service
            Booking booking = bookingService.makeBooking(
                    request.getHotelId(),
                    LocalDate.parse(request.getCheckInDate()),
                    LocalDate.parse(request.getCheckOutDate()),
                    Collections.emptyList(), // Replace with room types if needed
                    request.getRoomIdsList()
            );

            // Map domain booking to DTO and gRPC response
            BookingDTO bookingDTO = BookingMapper.toDTO(booking);
            BookingResponse response = BookingResponse.newBuilder()
                    .setBooking(bookingDTO.toProtobuf())
                    .build();

            responseObserver.onNext(response);
            responseObserver.onCompleted();
        } catch (Exception e) {
            responseObserver.onError(Status.INTERNAL.withDescription(e.getMessage()).asRuntimeException());
        }
    }

    @Override
    public void getBookingById(GetBookingByIdRequest request, StreamObserver<BookingResponse> responseObserver) {
        try {
            // Fetch booking by ID
            Booking booking = bookingService.getBookingById(request.getId());

            // Map domain booking to DTO and gRPC response
            BookingDTO dto = BookingMapper.toDTO(booking);
            BookingResponse response = BookingResponse.newBuilder()
                    .setBooking(dto.toProtobuf())
                    .build();

            responseObserver.onNext(response);
            responseObserver.onCompleted();
        } catch (BookingNotFoundException e) {
            responseObserver.onError(Status.NOT_FOUND.withDescription(e.getMessage()).asRuntimeException());
        } catch (Exception e) {
            responseObserver.onError(Status.INTERNAL.withDescription(e.getMessage()).asRuntimeException());
        }
    }

    @Override
    public void cancelBooking(CancelBookingRequest request, StreamObserver<Empty> responseObserver) {
        try {
            // Cancel the booking
            bookingService.cancelBooking(request.getId());

            // Return an empty response
            responseObserver.onNext(Empty.newBuilder().build());
            responseObserver.onCompleted();
        } catch (BookingNotFoundException e) {
            responseObserver.onError(Status.NOT_FOUND.withDescription(e.getMessage()).asRuntimeException());
        } catch (Exception e) {
            responseObserver.onError(Status.INTERNAL.withDescription(e.getMessage()).asRuntimeException());
        }
    }

    @Override
    public void checkInGuest(CheckInGuestRequest request, StreamObserver<Empty> responseObserver) {
        try {
            // Check in the guest
            bookingService.guestCheckIn(request.getBookingId());

            // Return an empty response
            responseObserver.onNext(Empty.newBuilder().build());
            responseObserver.onCompleted();
        } catch (BookingNotFoundException e) {
            responseObserver.onError(Status.NOT_FOUND.withDescription(e.getMessage()).asRuntimeException());
        } catch (Exception e) {
            responseObserver.onError(Status.INTERNAL.withDescription(e.getMessage()).asRuntimeException());
        }
    }

    @Override
    public void checkOutGuest(CheckOutGuestRequest request, StreamObserver<Empty> responseObserver) {
        try {
            // Check out the guest
            bookingService.guestCheckOut(request.getBookingId());

            // Return an empty response
            responseObserver.onNext(Empty.newBuilder().build());
            responseObserver.onCompleted();
        } catch (BookingNotFoundException e) {
            responseObserver.onError(Status.NOT_FOUND.withDescription(e.getMessage()).asRuntimeException());
        } catch (Exception e) {
            responseObserver.onError(Status.INTERNAL.withDescription(e.getMessage()).asRuntimeException());
        }
    }
}


