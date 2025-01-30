package hotelmanagementsystem.infrastructure.api.grpc.generated;

import io.grpc.BindableService;
import io.quarkus.grpc.GrpcService;
import io.quarkus.grpc.MutinyBean;

@jakarta.annotation.Generated(value = "by Mutiny Grpc generator", comments = "Source: api.proto")
public class GuestServiceBean extends MutinyGuestServiceGrpc.GuestServiceImplBase implements BindableService, MutinyBean {

    private final GuestService delegate;

    GuestServiceBean(@GrpcService GuestService delegate) {
        this.delegate = delegate;
    }

    @Override
    public io.smallrye.mutiny.Uni<hotelmanagementsystem.infrastructure.api.grpc.generated.GuestResponse> createGuest(hotelmanagementsystem.infrastructure.api.grpc.generated.CreateGuestRequest request) {
        try {
            return delegate.createGuest(request);
        } catch (UnsupportedOperationException e) {
            throw new io.grpc.StatusRuntimeException(io.grpc.Status.UNIMPLEMENTED);
        }
    }

    @Override
    public io.smallrye.mutiny.Uni<hotelmanagementsystem.infrastructure.api.grpc.generated.GuestResponse> getGuestById(hotelmanagementsystem.infrastructure.api.grpc.generated.GetGuestByIdRequest request) {
        try {
            return delegate.getGuestById(request);
        } catch (UnsupportedOperationException e) {
            throw new io.grpc.StatusRuntimeException(io.grpc.Status.UNIMPLEMENTED);
        }
    }

    @Override
    public io.smallrye.mutiny.Uni<hotelmanagementsystem.infrastructure.api.grpc.generated.GuestResponse> updateGuestEmail(hotelmanagementsystem.infrastructure.api.grpc.generated.UpdateGuestEmailRequest request) {
        try {
            return delegate.updateGuestEmail(request);
        } catch (UnsupportedOperationException e) {
            throw new io.grpc.StatusRuntimeException(io.grpc.Status.UNIMPLEMENTED);
        }
    }

    @Override
    public io.smallrye.mutiny.Uni<hotelmanagementsystem.infrastructure.api.grpc.generated.GuestResponse> updateGuestPhone(hotelmanagementsystem.infrastructure.api.grpc.generated.UpdateGuestPhoneRequest request) {
        try {
            return delegate.updateGuestPhone(request);
        } catch (UnsupportedOperationException e) {
            throw new io.grpc.StatusRuntimeException(io.grpc.Status.UNIMPLEMENTED);
        }
    }

    @Override
    public io.smallrye.mutiny.Uni<hotelmanagementsystem.infrastructure.api.grpc.generated.GuestResponse> updateGuestLastName(hotelmanagementsystem.infrastructure.api.grpc.generated.UpdateGuestLastNameRequest request) {
        try {
            return delegate.updateGuestLastName(request);
        } catch (UnsupportedOperationException e) {
            throw new io.grpc.StatusRuntimeException(io.grpc.Status.UNIMPLEMENTED);
        }
    }

    @Override
    public io.smallrye.mutiny.Uni<hotelmanagementsystem.infrastructure.api.grpc.generated.GuestResponse> updateGuestTitle(hotelmanagementsystem.infrastructure.api.grpc.generated.UpdateGuestTitleRequest request) {
        try {
            return delegate.updateGuestTitle(request);
        } catch (UnsupportedOperationException e) {
            throw new io.grpc.StatusRuntimeException(io.grpc.Status.UNIMPLEMENTED);
        }
    }

    @Override
    public io.smallrye.mutiny.Uni<hotelmanagementsystem.infrastructure.api.grpc.generated.Empty> deleteGuest(hotelmanagementsystem.infrastructure.api.grpc.generated.DeleteGuestRequest request) {
        try {
            return delegate.deleteGuest(request);
        } catch (UnsupportedOperationException e) {
            throw new io.grpc.StatusRuntimeException(io.grpc.Status.UNIMPLEMENTED);
        }
    }

    @Override
    public io.smallrye.mutiny.Uni<hotelmanagementsystem.infrastructure.api.grpc.generated.ListBookingsResponse> getBookingsByGuestId(hotelmanagementsystem.infrastructure.api.grpc.generated.GetBookingsByGuestIdRequest request) {
        try {
            return delegate.getBookingsByGuestId(request);
        } catch (UnsupportedOperationException e) {
            throw new io.grpc.StatusRuntimeException(io.grpc.Status.UNIMPLEMENTED);
        }
    }
}
