package hotelmanagementsystem.infrastructure.api.grpc.generated;

import io.grpc.BindableService;
import io.quarkus.grpc.GrpcService;
import io.quarkus.grpc.MutinyBean;

@jakarta.annotation.Generated(value = "by Mutiny Grpc generator", comments = "Source: api.proto")
public class BookingServiceBean extends MutinyBookingServiceGrpc.BookingServiceImplBase implements BindableService, MutinyBean {

    private final BookingService delegate;

    BookingServiceBean(@GrpcService BookingService delegate) {
        this.delegate = delegate;
    }

    @Override
    public io.smallrye.mutiny.Uni<hotelmanagementsystem.infrastructure.api.grpc.generated.BookingResponse> createBooking(hotelmanagementsystem.infrastructure.api.grpc.generated.CreateBookingRequest request) {
        try {
            return delegate.createBooking(request);
        } catch (UnsupportedOperationException e) {
            throw new io.grpc.StatusRuntimeException(io.grpc.Status.UNIMPLEMENTED);
        }
    }

    @Override
    public io.smallrye.mutiny.Uni<hotelmanagementsystem.infrastructure.api.grpc.generated.BookingResponse> getBookingById(hotelmanagementsystem.infrastructure.api.grpc.generated.GetBookingByIdRequest request) {
        try {
            return delegate.getBookingById(request);
        } catch (UnsupportedOperationException e) {
            throw new io.grpc.StatusRuntimeException(io.grpc.Status.UNIMPLEMENTED);
        }
    }

    @Override
    public io.smallrye.mutiny.Uni<hotelmanagementsystem.infrastructure.api.grpc.generated.Empty> cancelBooking(hotelmanagementsystem.infrastructure.api.grpc.generated.CancelBookingRequest request) {
        try {
            return delegate.cancelBooking(request);
        } catch (UnsupportedOperationException e) {
            throw new io.grpc.StatusRuntimeException(io.grpc.Status.UNIMPLEMENTED);
        }
    }

    @Override
    public io.smallrye.mutiny.Uni<hotelmanagementsystem.infrastructure.api.grpc.generated.Empty> checkInGuest(hotelmanagementsystem.infrastructure.api.grpc.generated.CheckInGuestRequest request) {
        try {
            return delegate.checkInGuest(request);
        } catch (UnsupportedOperationException e) {
            throw new io.grpc.StatusRuntimeException(io.grpc.Status.UNIMPLEMENTED);
        }
    }

    @Override
    public io.smallrye.mutiny.Uni<hotelmanagementsystem.infrastructure.api.grpc.generated.Empty> checkOutGuest(hotelmanagementsystem.infrastructure.api.grpc.generated.CheckOutGuestRequest request) {
        try {
            return delegate.checkOutGuest(request);
        } catch (UnsupportedOperationException e) {
            throw new io.grpc.StatusRuntimeException(io.grpc.Status.UNIMPLEMENTED);
        }
    }
}
