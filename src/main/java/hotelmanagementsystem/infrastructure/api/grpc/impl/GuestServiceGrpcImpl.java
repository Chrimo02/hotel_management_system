package hotelmanagementsystem.infrastructure.api.grpc.impl;

import hotelmanagementsystem.domain.exceptions.GuestNotFoundException;
import hotelmanagementsystem.domain.interfaces.GuestServicePort;
import hotelmanagementsystem.domain.models.Booking;
import hotelmanagementsystem.domain.models.Guest;
import hotelmanagementsystem.domain.services.GuestService;
import hotelmanagementsystem.infrastructure.api.dto.BookingDTO;
import hotelmanagementsystem.infrastructure.api.dto.GuestDTO;
import hotelmanagementsystem.infrastructure.api.grpc.generated.*;
import hotelmanagementsystem.infrastructure.api.mapper.BookingMapper;
import hotelmanagementsystem.infrastructure.api.mapper.GuestMapper;
import io.grpc.Status;
import io.grpc.stub.StreamObserver;
import io.quarkus.grpc.GrpcService;
import io.smallrye.common.annotation.Blocking;
import jakarta.inject.Inject;
import jakarta.inject.Singleton;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
@GrpcService
@Singleton
@Blocking
public class GuestServiceGrpcImpl  extends GuestServiceGrpc.GuestServiceImplBase
{
    private final GuestServicePort guestServicePort;

    @Inject
    public GuestServiceGrpcImpl(GuestServicePort guestServicePort){
        this.guestServicePort = guestServicePort;
    }
    @Override
    public void createGuest(CreateGuestRequest request, StreamObserver<GuestResponse> responseObserver) {
        try{
            Guest guest = guestServicePort.createGuest(
                    request.getFirstName(),
                    request.getLastName(),
                    LocalDate.parse(request.getBirthday()).getYear(),
                    LocalDate.parse(request.getBirthday()).getMonthValue(),
                    LocalDate.parse(request.getBirthday()).getDayOfMonth(),
                    request.getEmail(),
                    request.getPhoneNumber()
            );
            GuestDTO guestDTO = GuestMapper.toDTO(guest);
            GuestResponse response = GuestResponse.newBuilder()
                    .setGuest(guestDTO.toProtobuf())
                    .build();
            responseObserver.onNext(response);
            responseObserver.onCompleted();
        } catch (Exception e){
            responseObserver.onError(Status.INTERNAL.withDescription(e.getMessage()).asRuntimeException());
        }
    }

    @Override
    public void getGuestById(GetGuestByIdRequest request, StreamObserver<GuestResponse> responseObserver) {
        try {
            Guest guest = guestServicePort.getGuestById(request.getId());
            GuestDTO dto = GuestMapper.toDTO(guest);
            GuestResponse response = GuestResponse.newBuilder()
                    .setGuest(dto.toProtobuf())
                    .build();

            responseObserver.onNext(response);
            responseObserver.onCompleted();
        }
        catch (GuestNotFoundException e){
            responseObserver.onError(Status.NOT_FOUND.withDescription(e.getMessage()).asRuntimeException());
        }
        catch (Exception e){
            responseObserver.onError(Status.INTERNAL.withDescription(e.getMessage()).asRuntimeException());
        }
    }

    @Override
    public void updateGuestEmail(UpdateGuestEmailRequest request, StreamObserver<GuestResponse> responseObserver) {
        try {
            Guest updatedGuest = guestServicePort.updateEMail(request.getGuestId(), request.getNewEmail());
            GuestDTO dto = GuestMapper.toDTO(updatedGuest);
            GuestResponse response = GuestResponse.newBuilder()
                    .setGuest(dto.toProtobuf())
                    .build();

            responseObserver.onNext(response);
            responseObserver.onCompleted();
        } catch (GuestNotFoundException e) {
            responseObserver.onError(
                    Status.NOT_FOUND.withDescription(e.getMessage()).asRuntimeException()
            );
        } catch (Exception e) {
            responseObserver.onError(
                    Status.INTERNAL.withDescription(e.getMessage()).asRuntimeException()
            );
        }
    }

    @Override
    public void updateGuestPhone(UpdateGuestPhoneRequest request, StreamObserver<GuestResponse> responseObserver) {
        try {
            Guest updatedGuest = guestServicePort.updatePhone(request.getGuestId(), request.getNewPhone());
            GuestDTO dto = GuestMapper.toDTO(updatedGuest);
            GuestResponse response = GuestResponse.newBuilder()
                    .setGuest(dto.toProtobuf())
                    .build();

            responseObserver.onNext(response);
            responseObserver.onCompleted();
        } catch (GuestNotFoundException e) {
            responseObserver.onError(
                    Status.NOT_FOUND.withDescription(e.getMessage()).asRuntimeException()
            );
        } catch (Exception e) {
            responseObserver.onError(
                    Status.INTERNAL.withDescription(e.getMessage()).asRuntimeException()
            );
        }
    }

    @Override
    public void updateGuestLastName(UpdateGuestLastNameRequest request, StreamObserver<GuestResponse> responseObserver) {
        try {
            Guest updatedGuest = guestServicePort.updateLastName(request.getGuestId(), request.getNewLastName());
            GuestDTO dto = GuestMapper.toDTO(updatedGuest);
            GuestResponse response = GuestResponse.newBuilder()
                    .setGuest(dto.toProtobuf())
                    .build();

            responseObserver.onNext(response);
            responseObserver.onCompleted();
        } catch (GuestNotFoundException e) {
            responseObserver.onError(
                    Status.NOT_FOUND.withDescription(e.getMessage()).asRuntimeException()
            );
        } catch (Exception e) {
            responseObserver.onError(
                    Status.INTERNAL.withDescription(e.getMessage()).asRuntimeException()
            );
        }
    }

    @Override
    public void deleteGuest(DeleteGuestRequest request, StreamObserver<Empty> responseObserver) {
        try {
            guestServicePort.deleteGuest(request.getId());
            responseObserver.onNext(Empty.newBuilder().build());
            responseObserver.onCompleted();
        } catch (GuestNotFoundException e) {
            responseObserver.onError(
                    Status.NOT_FOUND.withDescription(e.getMessage()).asRuntimeException()
            );
        } catch (Exception e) {
            responseObserver.onError(
                    Status.INTERNAL.withDescription(e.getMessage()).asRuntimeException()
            );
        }
    }

    @Override
    public void getBookingsByGuestId(GetBookingsByGuestIdRequest request, StreamObserver<ListBookingsResponse> responseObserver) {
        try {
            List<Booking> bookings = guestServicePort.getAllBookingsFromGuest(request.getGuestId());

            List<BookingDTO> bookingDTOS = bookings.stream()
                    .map(BookingMapper::toDTO)
                    .toList();

            List<hotelmanagementsystem.infrastructure.api.grpc.generated.Booking> bookingList = new ArrayList<>();
            for (BookingDTO bookingDTO : bookingDTOS) {
                bookingList.add(bookingDTO.toProtobuf());
            }
            ListBookingsResponse response = ListBookingsResponse.newBuilder().addAllBookings(bookingList).build();

            responseObserver.onNext(response);
            responseObserver.onCompleted();
        } catch (Exception e) {
            responseObserver.onError(
                    Status.INTERNAL.withDescription(e.getMessage()).asRuntimeException()
            );
        }
    }
}
