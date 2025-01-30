package hotelmanagementsystem.infrastructure.api.grpc.generated;

import io.grpc.BindableService;
import io.quarkus.grpc.GrpcService;
import io.quarkus.grpc.MutinyBean;

@jakarta.annotation.Generated(value = "by Mutiny Grpc generator", comments = "Source: api.proto")
public class RoomServiceBean extends MutinyRoomServiceGrpc.RoomServiceImplBase implements BindableService, MutinyBean {

    private final RoomService delegate;

    RoomServiceBean(@GrpcService RoomService delegate) {
        this.delegate = delegate;
    }

    @Override
    public io.smallrye.mutiny.Uni<hotelmanagementsystem.infrastructure.api.grpc.generated.RoomResponse> createRoom(hotelmanagementsystem.infrastructure.api.grpc.generated.CreateRoomRequest request) {
        try {
            return delegate.createRoom(request);
        } catch (UnsupportedOperationException e) {
            throw new io.grpc.StatusRuntimeException(io.grpc.Status.UNIMPLEMENTED);
        }
    }

    @Override
    public io.smallrye.mutiny.Uni<hotelmanagementsystem.infrastructure.api.grpc.generated.RoomResponse> getRoomById(hotelmanagementsystem.infrastructure.api.grpc.generated.GetRoomByIdRequest request) {
        try {
            return delegate.getRoomById(request);
        } catch (UnsupportedOperationException e) {
            throw new io.grpc.StatusRuntimeException(io.grpc.Status.UNIMPLEMENTED);
        }
    }

    @Override
    public io.smallrye.mutiny.Uni<hotelmanagementsystem.infrastructure.api.grpc.generated.RoomResponse> updateRoomPrice(hotelmanagementsystem.infrastructure.api.grpc.generated.UpdateRoomPriceRequest request) {
        try {
            return delegate.updateRoomPrice(request);
        } catch (UnsupportedOperationException e) {
            throw new io.grpc.StatusRuntimeException(io.grpc.Status.UNIMPLEMENTED);
        }
    }

    @Override
    public io.smallrye.mutiny.Uni<hotelmanagementsystem.infrastructure.api.grpc.generated.Empty> removeRoom(hotelmanagementsystem.infrastructure.api.grpc.generated.RemoveRoomRequest request) {
        try {
            return delegate.removeRoom(request);
        } catch (UnsupportedOperationException e) {
            throw new io.grpc.StatusRuntimeException(io.grpc.Status.UNIMPLEMENTED);
        }
    }
}
