package hotelmanagementsystem.infrastructure.api.grpc.impl;
import hotelmanagementsystem.infrastructure.api.grpc.generated.*;
import io.grpc.stub.StreamObserver;

public class GuestServiceGrpcImpl  extends GuestServiceGrpc.GuestServiceImplBase
{
    @Override
    public void createGuest(CreateGuestRequest request, StreamObserver<GuestResponse> responseObserver) {
        super.createGuest(request, responseObserver);
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
