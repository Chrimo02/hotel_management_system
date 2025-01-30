package hotelmanagementsystem.infrastructure.api.grpc.client;

import hotelmanagementsystem.domain.models.*;
import hotelmanagementsystem.infrastructure.api.dto.RoomIdentifierDTO;

import hotelmanagementsystem.infrastructure.api.grpc.generated.RoomServiceGrpc;
import hotelmanagementsystem.infrastructure.api.grpc.generated.CreateRoomRequest;
import hotelmanagementsystem.infrastructure.api.grpc.generated.RoomResponse;
import hotelmanagementsystem.infrastructure.api.grpc.generated.GetRoomByIdRequest;
import hotelmanagementsystem.infrastructure.api.grpc.generated.UpdateRoomPriceRequest;
import hotelmanagementsystem.infrastructure.api.grpc.generated.RemoveRoomRequest;
import hotelmanagementsystem.infrastructure.api.grpc.impl.RoomServiceGrpcImpl;
import io.grpc.Channel;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.StatusRuntimeException;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

public class RoomServiceGrpcClient {
    private static final Logger logger = Logger.getLogger(RoomServiceGrpcClient.class.getName());
    private final RoomServiceGrpc.RoomServiceBlockingStub blockingStub;
    public RoomServiceGrpcClient(Channel channel) {
        blockingStub = RoomServiceGrpc.newBlockingStub(channel);
    }
    public RoomResponse createRoom(double pricePerNight, String building, int floor, String roomNumber, long hotelId, String roomType) {
        RoomIdentifierDTO roomIdentifierDTO = new RoomIdentifierDTO(building, floor, roomNumber);
        CreateRoomRequest request = CreateRoomRequest.newBuilder()
                .setPricePerNight(pricePerNight)
                .setRoomIdentifier(roomIdentifierDTO.toProtobuf())
                .setHotelId(hotelId)
                .setType(roomType) // "SingleRoom" or "DoubleRoom"
                .build();
        RoomResponse response;
        try {
            response = blockingStub.createRoom(request);
        } catch (StatusRuntimeException e) {
            logger.log(Level.WARNING, "RPC failed: {0}", e.getStatus());
            return null;
        }
        return response;
    }
    public RoomResponse getRoomById(long roomId) {
        GetRoomByIdRequest request = GetRoomByIdRequest.newBuilder()
                .setId(roomId)
                .build();
        RoomResponse response;
        try {
            response = blockingStub.getRoomById(request);
        } catch (StatusRuntimeException e) {
            logger.log(Level.WARNING, "RPC failed: {0}", e.getStatus());
            return null;
        }
        return response;
    }
    public RoomResponse updateRoomPrice(long roomId, double newPricePerNight) {
        UpdateRoomPriceRequest request = UpdateRoomPriceRequest.newBuilder()
                .setRoomId(roomId)
                .setNewPricePerNight(newPricePerNight)
                .build();
        RoomResponse response;
        try {
            response = blockingStub.updateRoomPrice(request);
        } catch (StatusRuntimeException e) {
            logger.log(Level.WARNING, "RPC failed: {0}", e.getStatus());
            return null;
        }
        return response;
    }
    public void removeRoom(long roomId) {
        RemoveRoomRequest request = RemoveRoomRequest.newBuilder()
                .setRoomId(roomId)
                .build();
        try {
            blockingStub.removeRoom(request);
        } catch (StatusRuntimeException e) {
            logger.log(Level.WARNING, "RPC failed: {0}", e.getStatus());
        }
    }
    public static void main(String[] args) throws Exception {
        String serverAddress = "localhost:50051";
        ManagedChannel channel = ManagedChannelBuilder.forTarget(serverAddress)
                .usePlaintext().build();
        try {
            RoomServiceGrpcClient client = new RoomServiceGrpcClient(channel);
            HotelLocation location = new HotelLocation.HotelLocationBuilder(1L)
                    .withCity("Berlin")
                    .withCountry("Germany")
                    .build();
            Hotel hotel = new Hotel.HotelBuilder()
                    .withName("Grand Hotel")
                    .withLocation(location)
                    .withRoomsList(new ArrayList<>()) // Erst eine leere Liste setzen
                    .build();
            //create
            RoomResponse roomResponse = client.createRoom(150, "Building A", 2, "202", hotel.getId(), "SingleRoom");
            //get room by id
            RoomResponse fetchedRoom = client.getRoomById(roomResponse.getRoom().getId());
            System.out.println("Fetched room: " + fetchedRoom.getRoom());
            //update room price
            RoomResponse updateRoom = client.updateRoomPrice(roomResponse.getRoom().getId(), 170);
            System.out.println("Updated room price: " + updateRoom.getRoom());
            //remove room
            client.removeRoom(roomResponse.getRoom().getId());
            System.out.println("Room removed successfully");
        } finally {
            channel.shutdownNow().awaitTermination(5, TimeUnit.SECONDS);
        }
    }
}
