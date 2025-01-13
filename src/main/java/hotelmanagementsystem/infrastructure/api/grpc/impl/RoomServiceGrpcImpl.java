package hotelmanagementsystem.infrastructure.api.grpc.impl;
import hotelmanagementsystem.infrastructure.api.grpc.generated.*;
import io.grpc.stub.StreamObserver;


//TODO:Change
public class RoomServiceGrpcImpl  extends RoomServiceGrpc.RoomServiceImplBase
{
    @Override
    public void createRoom(CreateRoomRequest request, StreamObserver<RoomResponse> responseObserver) {
        super.createRoom(request, responseObserver);
    }

    @Override
    public void getRoomById(GetRoomByIdRequest request, StreamObserver<RoomResponse> responseObserver) {
        super.getRoomById(request, responseObserver);
    }

    @Override
    public void listAvailableRooms(ListAvailableRoomsRequest request, StreamObserver<ListRoomsResponse> responseObserver) {
        super.listAvailableRooms(request, responseObserver);
    }

    @Override
    public void updateRoomPrice(UpdateRoomPriceRequest request, StreamObserver<RoomResponse> responseObserver) {
        super.updateRoomPrice(request, responseObserver);
    }

    @Override
    public void removeRoom(RemoveRoomRequest request, StreamObserver<Empty> responseObserver) {
        super.removeRoom(request, responseObserver);
    }
}
