package hotelmanagementsystem.infrastructure.api.grpc.impl;
import hotelmanagementsystem.domain.services.GuestService;
import hotelmanagementsystem.infrastructure.api.grpc.generated.*;
import io.grpc.stub.StreamObserver;
import jakarta.inject.Inject;

import java.time.LocalDate;

public class GuestServiceGrpcImpl  extends GuestServiceGrpc.GuestServiceImplBase
{
    private final GuestService guestService;

    @Inject
    public GuestServiceGrpcImpl(GuestService guestService){
        this.guestService = guestService;
    }
    @Override
    public void createGuest(CreateGuestRequest request, StreamObserver<GuestResponse> responseObserver) {

        try{


        }




    }



    @Override
    public void getGuestById(GetGuestByIdRequest request, StreamObserver<GuestResponse> responseObserver) {
        super.getGuestById(request, responseObserver);
    }

    @Override
    public void updateGuestEmail(UpdateGuestEmailRequest request, StreamObserver<GuestResponse> responseObserver) {
        super.updateGuestEmail(request, responseObserver);
    }

    @Override
    public void updateGuestPhone(UpdateGuestPhoneRequest request, StreamObserver<GuestResponse> responseObserver) {
        super.updateGuestPhone(request, responseObserver);
    }

    @Override
    public void updateGuestLastName(UpdateGuestLastNameRequest request, StreamObserver<GuestResponse> responseObserver) {
        super.updateGuestLastName(request, responseObserver);
    }

    @Override
    public void updateGuestTitle(UpdateGuestTitleRequest request, StreamObserver<GuestResponse> responseObserver) {
        super.updateGuestTitle(request, responseObserver);
    }

    @Override
    public void deleteGuest(DeleteGuestRequest request, StreamObserver<Empty> responseObserver) {
        super.deleteGuest(request, responseObserver);
    }

    @Override
    public void getBookingsByGuestId(GetBookingsByGuestIdRequest request, StreamObserver<ListBookingsResponse> responseObserver) {
        super.getBookingsByGuestId(request, responseObserver);
    }
}
