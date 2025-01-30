package hotelmanagementsystem.infrastructure.api.grpc.client;

import hotelmanagementsystem.infrastructure.api.grpc.generated.*;
import io.quarkus.grpc.GrpcClient;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

/**
 * RoomClient - Calls the RoomService gRPC methods.
 */
@ApplicationScoped
public class RoomClient {

    @Inject
    @GrpcClient("room")
    RoomServiceGrpc.RoomServiceBlockingStub roomStub;

    public RoomResponse createRoom(CreateRoomRequest request) {
        return roomStub.createRoom(request);
    }

    public RoomResponse getRoomById(GetRoomByIdRequest request) {
        return roomStub.getRoomById(request);
    }

    public RoomResponse updateRoomPrice(UpdateRoomPriceRequest request) {
        return roomStub.updateRoomPrice(request);
    }

    public void removeRoom(RemoveRoomRequest request) {
        roomStub.removeRoom(request);
    }
}
