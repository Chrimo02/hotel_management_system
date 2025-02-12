package hotelmanagementsystem.infrastructure.api.grpc.impl;

import hotelmanagementsystem.domain.exceptions.BookingNotFoundException;
import hotelmanagementsystem.domain.models.Booking;
import hotelmanagementsystem.domain.services.BookingService;
import hotelmanagementsystem.infrastructure.api.dto.BookingDTO;
import hotelmanagementsystem.infrastructure.api.grpc.generated.*;
import hotelmanagementsystem.infrastructure.api.mapper.BookingMapper;
import io.grpc.Status;
import io.grpc.stub.StreamObserver;
import io.quarkus.grpc.GrpcService;
import io.smallrye.common.annotation.Blocking;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.inject.Singleton;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@GrpcService
@Singleton
@Blocking
public class BookingServiceGrpcImpl extends BookingServiceGrpc.BookingServiceImplBase {

    private final BookingService bookingService;

    @Inject
    public BookingServiceGrpcImpl(BookingService bookingService) {
        this.bookingService = bookingService;
    }

    @Override
    public void createBooking(CreateBookingRequest request, StreamObserver<BookingResponse> responseObserver) {
        try {
            // 1) Konvertiere Request-Raumtypen (Strings) -> Domain-Klassen
            List<Class<? extends hotelmanagementsystem.domain.models.Room>> domainRoomTypes =
                    request.getRoomTypesList().stream()
                            .map(this::parseRoomType)
                            .collect(Collectors.toList());

            // 2) Konvertiere String-Daten -> LocalDate
            LocalDate checkIn = LocalDate.parse(request.getCheckInDate());
            LocalDate checkOut = LocalDate.parse(request.getCheckOutDate());

            // 3) Ruf die Domain-Methode makeBooking auf
            Booking booking = bookingService.makeBooking(
                    request.getHotelId(),
                    checkIn,
                    checkOut,
                    domainRoomTypes,
                    request.getGuestIdsList()  // Mehrere Gäste
            );

            // 4) Konvertiere Domain-Buchung -> DTO -> Protobuf
            BookingDTO bookingDTO = BookingMapper.toDTO(booking);
            BookingResponse response = BookingResponse.newBuilder()
                    .setBooking(bookingDTO.toProtobuf())
                    .build();

            responseObserver.onNext(response);
            responseObserver.onCompleted();

        } catch (Exception e) {
            responseObserver.onError(Status.INTERNAL
                    .withDescription(e.getMessage())
                    .asRuntimeException());
        }
    }

    @Override
    public void getBookingById(GetBookingByIdRequest request, StreamObserver<BookingResponse> responseObserver) {
        try {
            Booking booking = bookingService.getBookingById(request.getId());
            BookingDTO dto = BookingMapper.toDTO(booking);
            hotelmanagementsystem.infrastructure.api.grpc.generated.Booking protobufBooking = dto.toProtobuf(); // Nutze die korrekte Booking-Klasse

            BookingResponse response = BookingResponse.newBuilder()
                    .setBooking(protobufBooking)
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
            bookingService.cancelBooking(request.getId());
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
            bookingService.guestCheckIn(request.getBookingId());
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
            bookingService.guestCheckOut(request.getBookingId());
            responseObserver.onNext(Empty.newBuilder().build());
            responseObserver.onCompleted();
        } catch (BookingNotFoundException e) {
            responseObserver.onError(Status.NOT_FOUND.withDescription(e.getMessage()).asRuntimeException());
        } catch (Exception e) {
            responseObserver.onError(Status.INTERNAL.withDescription(e.getMessage()).asRuntimeException());
        }
    }

    // Beispiel für eine Hilfsmethode zur Konvertierung von String zu Room-Klasse
    private Class<? extends hotelmanagementsystem.domain.models.Room> parseRoomType(String typeName) {
        // Minimalbeispiel; erweitere für weitere Typen
        switch (typeName.toUpperCase()) {
            case "SINGLE":
                return hotelmanagementsystem.domain.models.SingleRoom.class;
            case "DOUBLE":
                return hotelmanagementsystem.domain.models.DoubleRoom.class;
            default:
                throw new IllegalArgumentException("Unbekannter Raumtyp: " + typeName);
        }
    }
}
