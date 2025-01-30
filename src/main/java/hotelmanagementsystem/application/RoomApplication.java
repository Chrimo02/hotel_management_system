package hotelmanagementsystem.application;

import hotelmanagementsystem.infrastructure.api.grpc.client.RoomClient;
import hotelmanagementsystem.infrastructure.api.grpc.generated.*;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class RoomApplication {

    @Inject
    RoomClient roomClient;

    public RoomResponse createRoom(double pricePerNight, String type, long hotelId,
                                   RoomIdentifier roomIdentifier) {
        CreateRoomRequest request = CreateRoomRequest.newBuilder()
                .setPricePerNight(pricePerNight)
                .setType(type)
                .setHotelId(hotelId)
                .setRoomIdentifier(roomIdentifier)
                .build();
        return roomClient.createRoom(request);
    }

    public RoomResponse getRoomById(long roomId) {
        GetRoomByIdRequest request = GetRoomByIdRequest.newBuilder()
                .setId(roomId)
                .build();
        return roomClient.getRoomById(request);
    }

    public RoomResponse updateRoomPrice(long roomId, double newPricePerNight) {
        UpdateRoomPriceRequest request = UpdateRoomPriceRequest.newBuilder()
                .setRoomId(roomId)
                .setNewPricePerNight(newPricePerNight)
                .build();
        return roomClient.updateRoomPrice(request);
    }

    public void removeRoom(long roomId) {
        RemoveRoomRequest request = RemoveRoomRequest.newBuilder()
                .setRoomId(roomId)
                .build();
        roomClient.removeRoom(request);
    }
}
