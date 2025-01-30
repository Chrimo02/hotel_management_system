package hotelmanagementsystem.infrastructure.api.grpc.generated;

import io.quarkus.grpc.MutinyService;

@jakarta.annotation.Generated(value = "by Mutiny Grpc generator", comments = "Source: api.proto")
public interface RoomService extends MutinyService {

    io.smallrye.mutiny.Uni<hotelmanagementsystem.infrastructure.api.grpc.generated.RoomResponse> createRoom(hotelmanagementsystem.infrastructure.api.grpc.generated.CreateRoomRequest request);

    io.smallrye.mutiny.Uni<hotelmanagementsystem.infrastructure.api.grpc.generated.RoomResponse> getRoomById(hotelmanagementsystem.infrastructure.api.grpc.generated.GetRoomByIdRequest request);

    io.smallrye.mutiny.Uni<hotelmanagementsystem.infrastructure.api.grpc.generated.RoomResponse> updateRoomPrice(hotelmanagementsystem.infrastructure.api.grpc.generated.UpdateRoomPriceRequest request);

    io.smallrye.mutiny.Uni<hotelmanagementsystem.infrastructure.api.grpc.generated.Empty> removeRoom(hotelmanagementsystem.infrastructure.api.grpc.generated.RemoveRoomRequest request);
}
