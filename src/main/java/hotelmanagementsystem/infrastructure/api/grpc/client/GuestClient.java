package hotelmanagementsystem.infrastructure.api.grpc.client;

import hotelmanagementsystem.infrastructure.api.grpc.generated.*;
import io.quarkus.grpc.GrpcClient;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

/**
 * GuestClient - Calls the GuestService gRPC methods.
 */
@ApplicationScoped
public class GuestClient {

    @Inject
    @GrpcClient("guest")
    GuestServiceGrpc.GuestServiceBlockingStub guestStub;

    public GuestResponse createGuest(CreateGuestRequest request) {
        return guestStub.createGuest(request);
    }

    public GuestResponse getGuestById(GetGuestByIdRequest request) {
        return guestStub.getGuestById(request);
    }

    public GuestResponse updateGuestEmail(UpdateGuestEmailRequest request) {
        return guestStub.updateGuestEmail(request);
    }

    public GuestResponse updateGuestPhone(UpdateGuestPhoneRequest request) {
        return guestStub.updateGuestPhone(request);
    }

    public GuestResponse updateGuestLastName(UpdateGuestLastNameRequest request) {
        return guestStub.updateGuestLastName(request);
    }

    public GuestResponse updateGuestTitle(UpdateGuestTitleRequest request) {
        return guestStub.updateGuestTitle(request);
    }

    public void deleteGuest(DeleteGuestRequest request) {
        guestStub.deleteGuest(request);
    }

    public ListBookingsResponse getBookingsByGuestId(GetBookingsByGuestIdRequest request) {
        return guestStub.getBookingsByGuestId(request);
    }
}
