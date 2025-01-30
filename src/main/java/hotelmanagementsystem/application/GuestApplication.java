package hotelmanagementsystem.application;

import hotelmanagementsystem.infrastructure.api.grpc.client.GuestClient;
import hotelmanagementsystem.infrastructure.api.grpc.generated.*;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class GuestApplication {

    @Inject
    GuestClient guestClient;

    public GuestResponse createGuest(String firstName, String lastName, String title, String birthday,
                                     String email, String phoneNumber) {
        CreateGuestRequest.Builder builder = CreateGuestRequest.newBuilder()
                .setFirstName(firstName)
                .setLastName(lastName)
                .setBirthday(birthday)
                .setEmail(email)
                .setPhoneNumber(phoneNumber);

        if (title != null) {
            builder.setTitle(title);
        }
        return guestClient.createGuest(builder.build());
    }

    public GuestResponse getGuestById(long guestId) {
        GetGuestByIdRequest request = GetGuestByIdRequest.newBuilder()
                .setId(guestId)
                .build();
        return guestClient.getGuestById(request);
    }

    public GuestResponse updateGuestEmail(long guestId, String newEmail) {
        UpdateGuestEmailRequest request = UpdateGuestEmailRequest.newBuilder()
                .setGuestId(guestId)
                .setNewEmail(newEmail)
                .build();
        return guestClient.updateGuestEmail(request);
    }

    public GuestResponse updateGuestPhone(long guestId, String newPhone) {
        UpdateGuestPhoneRequest request = UpdateGuestPhoneRequest.newBuilder()
                .setGuestId(guestId)
                .setNewPhone(newPhone)
                .build();
        return guestClient.updateGuestPhone(request);
    }

    public GuestResponse updateGuestLastName(long guestId, String newLastName) {
        UpdateGuestLastNameRequest request = UpdateGuestLastNameRequest.newBuilder()
                .setGuestId(guestId)
                .setNewLastName(newLastName)
                .build();
        return guestClient.updateGuestLastName(request);
    }

    public GuestResponse updateGuestTitle(long guestId, String newTitle) {
        UpdateGuestTitleRequest request = UpdateGuestTitleRequest.newBuilder()
                .setGuestId(guestId)
                .setNewTitle(newTitle)
                .build();
        return guestClient.updateGuestTitle(request);
    }

    public void deleteGuest(long guestId) {
        DeleteGuestRequest request = DeleteGuestRequest.newBuilder()
                .setId(guestId)
                .build();
        guestClient.deleteGuest(request);
    }

    public ListBookingsResponse getBookingsByGuestId(long guestId) {
        GetBookingsByGuestIdRequest request = GetBookingsByGuestIdRequest.newBuilder()
                .setGuestId(guestId)
                .build();
        return guestClient.getBookingsByGuestId(request);
    }
}
