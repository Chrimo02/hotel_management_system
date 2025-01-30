package hotelmanagementsystem.infrastructure.api.grpc.generated;

import io.grpc.BindableService;
import io.quarkus.grpc.GrpcService;
import io.quarkus.grpc.MutinyBean;

@jakarta.annotation.Generated(value = "by Mutiny Grpc generator", comments = "Source: api.proto")
public class HotelServiceBean extends MutinyHotelServiceGrpc.HotelServiceImplBase implements BindableService, MutinyBean {

    private final HotelService delegate;

    HotelServiceBean(@GrpcService HotelService delegate) {
        this.delegate = delegate;
    }

    @Override
    public io.smallrye.mutiny.Uni<hotelmanagementsystem.infrastructure.api.grpc.generated.HotelResponse> createHotel(hotelmanagementsystem.infrastructure.api.grpc.generated.CreateHotelRequest request) {
        try {
            return delegate.createHotel(request);
        } catch (UnsupportedOperationException e) {
            throw new io.grpc.StatusRuntimeException(io.grpc.Status.UNIMPLEMENTED);
        }
    }

    @Override
    public io.smallrye.mutiny.Uni<hotelmanagementsystem.infrastructure.api.grpc.generated.HotelResponse> getHotelById(hotelmanagementsystem.infrastructure.api.grpc.generated.GetHotelByIdRequest request) {
        try {
            return delegate.getHotelById(request);
        } catch (UnsupportedOperationException e) {
            throw new io.grpc.StatusRuntimeException(io.grpc.Status.UNIMPLEMENTED);
        }
    }

    @Override
    public io.smallrye.mutiny.Uni<hotelmanagementsystem.infrastructure.api.grpc.generated.ListHotelsResponse> listHotels(hotelmanagementsystem.infrastructure.api.grpc.generated.ListHotelsRequest request) {
        try {
            return delegate.listHotels(request);
        } catch (UnsupportedOperationException e) {
            throw new io.grpc.StatusRuntimeException(io.grpc.Status.UNIMPLEMENTED);
        }
    }

    @Override
    public io.smallrye.mutiny.Uni<hotelmanagementsystem.infrastructure.api.grpc.generated.HotelResponse> updateHotel(hotelmanagementsystem.infrastructure.api.grpc.generated.UpdateHotelRequest request) {
        try {
            return delegate.updateHotel(request);
        } catch (UnsupportedOperationException e) {
            throw new io.grpc.StatusRuntimeException(io.grpc.Status.UNIMPLEMENTED);
        }
    }

    @Override
    public io.smallrye.mutiny.Uni<hotelmanagementsystem.infrastructure.api.grpc.generated.Empty> deleteHotel(hotelmanagementsystem.infrastructure.api.grpc.generated.DeleteHotelRequest request) {
        try {
            return delegate.deleteHotel(request);
        } catch (UnsupportedOperationException e) {
            throw new io.grpc.StatusRuntimeException(io.grpc.Status.UNIMPLEMENTED);
        }
    }

    @Override
    public io.smallrye.mutiny.Uni<hotelmanagementsystem.infrastructure.api.grpc.generated.Empty> rateHotel(hotelmanagementsystem.infrastructure.api.grpc.generated.RateHotelRequest request) {
        try {
            return delegate.rateHotel(request);
        } catch (UnsupportedOperationException e) {
            throw new io.grpc.StatusRuntimeException(io.grpc.Status.UNIMPLEMENTED);
        }
    }

    @Override
    public io.smallrye.mutiny.Uni<hotelmanagementsystem.infrastructure.api.grpc.generated.ListRoomsResponse> findAvailableRooms(hotelmanagementsystem.infrastructure.api.grpc.generated.FindAvailableRoomsRequest request) {
        try {
            return delegate.findAvailableRooms(request);
        } catch (UnsupportedOperationException e) {
            throw new io.grpc.StatusRuntimeException(io.grpc.Status.UNIMPLEMENTED);
        }
    }
}
