package hotelmanagementsystem.infrastructure.api.grpc.impl;

import hotelmanagementsystem.domain.interfaces.RoomServicePort;
import hotelmanagementsystem.domain.models.DoubleRoom;
import hotelmanagementsystem.domain.models.Room;
import hotelmanagementsystem.domain.models.RoomIdentifier;
import hotelmanagementsystem.domain.models.SingleRoom;
import hotelmanagementsystem.infrastructure.api.dto.RoomDTO;
import hotelmanagementsystem.infrastructure.api.mapper.RoomMapper;
import hotelmanagementsystem.infrastructure.api.grpc.generated.*;
import io.grpc.Status;
import io.grpc.stub.StreamObserver;
import io.quarkus.grpc.GrpcService;
import io.smallrye.common.annotation.Blocking;
import jakarta.inject.Inject;
import jakarta.inject.Singleton;

@GrpcService
@Singleton
@Blocking
public class RoomServiceGrpcImpl extends RoomServiceGrpc.RoomServiceImplBase {

    private final RoomServicePort roomServicePort;

    @Inject
    public RoomServiceGrpcImpl(RoomServicePort roomServicePort) {
        this.roomServicePort = roomServicePort;
    }

    @Override
    public void createRoom(CreateRoomRequest request, StreamObserver<RoomResponse> responseObserver) {
        try {
            String typeString = request.getType();
            System.out.println(typeString);

            Class<? extends Room> roomTypeClass;
            if ("SINGLE".equalsIgnoreCase(typeString)) {
                roomTypeClass = SingleRoom.class;
            } else if ("DOUBLE".equalsIgnoreCase(typeString)) {
                roomTypeClass = DoubleRoom.class;
            } else {
                throw new IllegalArgumentException("in gprcservice" + "Unsupported room type: " + typeString);
            }
            Room room = roomServicePort.createRoom(
                    request.getPricePerNight(),
                    new RoomIdentifier(request.getRoomIdentifier().getBuilding(),request.getRoomIdentifier().getFloor(),request.getRoomIdentifier().getRoomNumber()),
                    request.getHotelId(),
                    roomTypeClass
            );

            RoomDTO roomDTO = RoomMapper.toDTO(room);
            RoomResponse response = RoomResponse.newBuilder()
                    .setRoom(roomDTO.toProtobuf())
                    .build();

            responseObserver.onNext(response);
            responseObserver.onCompleted();
        } catch (Exception e) {
            responseObserver.onError(
                    Status.INTERNAL.withDescription(e.getMessage()).asRuntimeException()
            );
        }
    }

    @Override
    public void getRoomById(GetRoomByIdRequest request, StreamObserver<RoomResponse> responseObserver) {
        try {
            Room room = roomServicePort.getRoomById(request.getId());
            RoomDTO roomDTO = RoomMapper.toDTO(room);

            RoomResponse response = RoomResponse.newBuilder()
                    .setRoom(roomDTO.toProtobuf())
                    .build();

            responseObserver.onNext(response);
            responseObserver.onCompleted();
        } catch (Exception e) {
            responseObserver.onError(
                    Status.NOT_FOUND.withDescription(e.getMessage()).asRuntimeException()
            );
        }
    }

    @Override
    public void updateRoomPrice(UpdateRoomPriceRequest request, StreamObserver<RoomResponse> responseObserver) {
        try {
            Room room = roomServicePort.getRoomById(request.getRoomId());
            room.setPricePerNight(request.getNewPricePerNight());
            roomServicePort.updatePricePerNight(request.getRoomId(), request.getNewPricePerNight());

            RoomDTO roomDTO = RoomMapper.toDTO(room);
            RoomResponse response = RoomResponse.newBuilder()
                    .setRoom(roomDTO.toProtobuf())
                    .build();

            responseObserver.onNext(response);
            responseObserver.onCompleted();
        } catch (Exception e) {
            responseObserver.onError(
                    Status.INTERNAL.withDescription(e.getMessage()).asRuntimeException()
            );
        }
    }

    @Override
    public void removeRoom(RemoveRoomRequest request, StreamObserver<Empty> responseObserver) {
        try {
            roomServicePort.removeRoom(request.getRoomId());
            responseObserver.onNext(Empty.newBuilder().build());
            responseObserver.onCompleted();
        } catch (Exception e) {
            responseObserver.onError(
                    Status.INTERNAL.withDescription(e.getMessage()).asRuntimeException()
            );
        }
    }
}
