package hotelmanagementsystem.infrastructure.api.grpc.generated;

import io.quarkus.grpc.MutinyService;

@jakarta.annotation.Generated(value = "by Mutiny Grpc generator", comments = "Source: api.proto")
public interface BookingService extends MutinyService {

    io.smallrye.mutiny.Uni<hotelmanagementsystem.infrastructure.api.grpc.generated.BookingResponse> createBooking(hotelmanagementsystem.infrastructure.api.grpc.generated.CreateBookingRequest request);

    io.smallrye.mutiny.Uni<hotelmanagementsystem.infrastructure.api.grpc.generated.BookingResponse> getBookingById(hotelmanagementsystem.infrastructure.api.grpc.generated.GetBookingByIdRequest request);

    io.smallrye.mutiny.Uni<hotelmanagementsystem.infrastructure.api.grpc.generated.Empty> cancelBooking(hotelmanagementsystem.infrastructure.api.grpc.generated.CancelBookingRequest request);

    io.smallrye.mutiny.Uni<hotelmanagementsystem.infrastructure.api.grpc.generated.Empty> checkInGuest(hotelmanagementsystem.infrastructure.api.grpc.generated.CheckInGuestRequest request);

    io.smallrye.mutiny.Uni<hotelmanagementsystem.infrastructure.api.grpc.generated.Empty> checkOutGuest(hotelmanagementsystem.infrastructure.api.grpc.generated.CheckOutGuestRequest request);
}
