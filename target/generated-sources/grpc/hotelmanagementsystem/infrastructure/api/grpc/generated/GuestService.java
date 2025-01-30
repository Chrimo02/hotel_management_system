package hotelmanagementsystem.infrastructure.api.grpc.generated;

import io.quarkus.grpc.MutinyService;

@jakarta.annotation.Generated(value = "by Mutiny Grpc generator", comments = "Source: api.proto")
public interface GuestService extends MutinyService {

    io.smallrye.mutiny.Uni<hotelmanagementsystem.infrastructure.api.grpc.generated.GuestResponse> createGuest(hotelmanagementsystem.infrastructure.api.grpc.generated.CreateGuestRequest request);

    io.smallrye.mutiny.Uni<hotelmanagementsystem.infrastructure.api.grpc.generated.GuestResponse> getGuestById(hotelmanagementsystem.infrastructure.api.grpc.generated.GetGuestByIdRequest request);

    io.smallrye.mutiny.Uni<hotelmanagementsystem.infrastructure.api.grpc.generated.GuestResponse> updateGuestEmail(hotelmanagementsystem.infrastructure.api.grpc.generated.UpdateGuestEmailRequest request);

    io.smallrye.mutiny.Uni<hotelmanagementsystem.infrastructure.api.grpc.generated.GuestResponse> updateGuestPhone(hotelmanagementsystem.infrastructure.api.grpc.generated.UpdateGuestPhoneRequest request);

    io.smallrye.mutiny.Uni<hotelmanagementsystem.infrastructure.api.grpc.generated.GuestResponse> updateGuestLastName(hotelmanagementsystem.infrastructure.api.grpc.generated.UpdateGuestLastNameRequest request);

    io.smallrye.mutiny.Uni<hotelmanagementsystem.infrastructure.api.grpc.generated.GuestResponse> updateGuestTitle(hotelmanagementsystem.infrastructure.api.grpc.generated.UpdateGuestTitleRequest request);

    io.smallrye.mutiny.Uni<hotelmanagementsystem.infrastructure.api.grpc.generated.Empty> deleteGuest(hotelmanagementsystem.infrastructure.api.grpc.generated.DeleteGuestRequest request);

    io.smallrye.mutiny.Uni<hotelmanagementsystem.infrastructure.api.grpc.generated.ListBookingsResponse> getBookingsByGuestId(hotelmanagementsystem.infrastructure.api.grpc.generated.GetBookingsByGuestIdRequest request);
}
