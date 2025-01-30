package hotelmanagementsystem.infrastructure.api.grpc.generated;

import java.util.function.BiFunction;
import io.quarkus.grpc.MutinyClient;

@jakarta.annotation.Generated(value = "by Mutiny Grpc generator", comments = "Source: api.proto")
public class RoomServiceClient implements RoomService, MutinyClient<MutinyRoomServiceGrpc.MutinyRoomServiceStub> {

    private final MutinyRoomServiceGrpc.MutinyRoomServiceStub stub;

    public RoomServiceClient(String name, io.grpc.Channel channel, BiFunction<String, MutinyRoomServiceGrpc.MutinyRoomServiceStub, MutinyRoomServiceGrpc.MutinyRoomServiceStub> stubConfigurator) {
        this.stub = stubConfigurator.apply(name, MutinyRoomServiceGrpc.newMutinyStub(channel));
    }

    private RoomServiceClient(MutinyRoomServiceGrpc.MutinyRoomServiceStub stub) {
        this.stub = stub;
    }

    public RoomServiceClient newInstanceWithStub(MutinyRoomServiceGrpc.MutinyRoomServiceStub stub) {
        return new RoomServiceClient(stub);
    }

    @Override
    public MutinyRoomServiceGrpc.MutinyRoomServiceStub getStub() {
        return stub;
    }

    @Override
    public io.smallrye.mutiny.Uni<hotelmanagementsystem.infrastructure.api.grpc.generated.RoomResponse> createRoom(hotelmanagementsystem.infrastructure.api.grpc.generated.CreateRoomRequest request) {
        return stub.createRoom(request);
    }

    @Override
    public io.smallrye.mutiny.Uni<hotelmanagementsystem.infrastructure.api.grpc.generated.RoomResponse> getRoomById(hotelmanagementsystem.infrastructure.api.grpc.generated.GetRoomByIdRequest request) {
        return stub.getRoomById(request);
    }

    @Override
    public io.smallrye.mutiny.Uni<hotelmanagementsystem.infrastructure.api.grpc.generated.RoomResponse> updateRoomPrice(hotelmanagementsystem.infrastructure.api.grpc.generated.UpdateRoomPriceRequest request) {
        return stub.updateRoomPrice(request);
    }

    @Override
    public io.smallrye.mutiny.Uni<hotelmanagementsystem.infrastructure.api.grpc.generated.Empty> removeRoom(hotelmanagementsystem.infrastructure.api.grpc.generated.RemoveRoomRequest request) {
        return stub.removeRoom(request);
    }
}
