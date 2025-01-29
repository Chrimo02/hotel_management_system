package hotelmanagementsystem.infrastructure.api.grpc.impl;

import hotelmanagementsystem.domain.models.Room;
import hotelmanagementsystem.domain.models.RoomIdentifier;
import hotelmanagementsystem.infrastructure.api.dto.RoomDTO;
import hotelmanagementsystem.infrastructure.api.mapper.RoomMapper;
import hotelmanagementsystem.domain.services.RoomService;
import hotelmanagementsystem.infrastructure.api.grpc.generated.*;
import io.grpc.Status;
import io.grpc.stub.StreamObserver;
import io.quarkus.grpc.GrpcService;
import io.smallrye.common.annotation.Blocking;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.inject.Singleton;

import java.time.LocalDate;
import java.util.List;
@GrpcService
@Singleton
@Blocking
public class RoomServiceGrpcImpl extends RoomServiceGrpc.RoomServiceImplBase {

    private final RoomService roomService;

    @Inject
    public RoomServiceGrpcImpl(RoomService roomService) {
        this.roomService = roomService;
    }

    @Override
    public void createRoom(CreateRoomRequest request, StreamObserver<RoomResponse> responseObserver) {
        try {
            Room room = roomService.createRoom(
                    request.getPricePerNight(),
                    new RoomIdentifier(request.getRoomIdentifier().getBuilding(),request.getRoomIdentifier().getFloor(),request.getRoomIdentifier().getRoomNumber()),
                    request.getHotelId(),
                    Class.forName(request.getType()).asSubclass(Room.class)
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
            Room room = roomService.getRoomById(request.getId());
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
            Room room = roomService.getRoomById(request.getRoomId());
            room.setPricePerNight(request.getNewPricePerNight());
            roomService.updatePricePerNight(request.getRoomId(), request.getNewPricePerNight());

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
            roomService.removeRoom(request.getRoomId());
            responseObserver.onNext(Empty.newBuilder().build());
            responseObserver.onCompleted();
        } catch (Exception e) {
            responseObserver.onError(
                    Status.INTERNAL.withDescription(e.getMessage()).asRuntimeException()
            );
        }
    }
}
