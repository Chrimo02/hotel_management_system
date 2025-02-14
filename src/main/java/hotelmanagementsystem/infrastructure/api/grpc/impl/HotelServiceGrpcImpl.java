package hotelmanagementsystem.infrastructure.api.grpc.impl;

import hotelmanagementsystem.domain.exceptions.GuestNotFoundException;
import hotelmanagementsystem.domain.exceptions.HotelNotFoundException;
import hotelmanagementsystem.domain.models.Hotel;
import hotelmanagementsystem.domain.models.PagedHotels;
import hotelmanagementsystem.domain.models.Room;
import hotelmanagementsystem.domain.services.HotelService;
import hotelmanagementsystem.infrastructure.api.dto.HotelDTO;
import hotelmanagementsystem.infrastructure.api.dto.RoomDTO;
import hotelmanagementsystem.infrastructure.api.grpc.generated.*;
import hotelmanagementsystem.infrastructure.api.mapper.HotelMapper;
import hotelmanagementsystem.infrastructure.api.mapper.RoomMapper;
import io.grpc.Status;
import io.grpc.stub.StreamObserver;
import io.quarkus.grpc.GrpcService;
import io.smallrye.common.annotation.Blocking;
import jakarta.inject.Inject;
import jakarta.inject.Singleton;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@GrpcService
@Singleton
@Blocking
public class HotelServiceGrpcImpl extends HotelServiceGrpc.HotelServiceImplBase {

    private final HotelService hotelService;

    @Inject
    public HotelServiceGrpcImpl(HotelService hotelService) {
        this.hotelService = hotelService;
    }

    @Override
    public void createHotel(CreateHotelRequest request, StreamObserver<HotelResponse> responseObserver) {
        try {
            hotelmanagementsystem.domain.models.HotelLocation hotelLocation =
                    new hotelmanagementsystem.domain.models.HotelLocation.HotelLocationBuilder()
                            .withAddress(request.getLocation().getAddress())
                            .withCity(request.getLocation().getCity())
                            .withCountry(request.getLocation().getCountry())
                            .build();

            Hotel createdHotel = hotelService.createHotel(
                    request.getName(),
                    request.getDescription(),
                    hotelLocation
            );

            HotelDTO hotelDTO = HotelMapper.toDTO(createdHotel);
            HotelResponse response = HotelResponse.newBuilder()
                    .setHotel(hotelDTO.toProtobuf())
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
    public void getHotelById(GetHotelByIdRequest request, StreamObserver<HotelResponse> responseObserver) {
        try {
            Hotel hotel = hotelService.getHotelByHotelId(request.getId());
            HotelDTO hotelDTO = HotelMapper.toDTO(hotel);

            HotelResponse response = HotelResponse.newBuilder()
                    .setHotel(hotelDTO.toProtobuf())
                    .build();

            responseObserver.onNext(response);
            responseObserver.onCompleted();
        } catch (HotelNotFoundException e) {
            responseObserver.onError(
                    Status.NOT_FOUND.withDescription(e.getMessage()).asRuntimeException()
            );
        } catch (Exception e) {
            responseObserver.onError(
                    Status.INTERNAL.withDescription(e.getMessage()).asRuntimeException()
            );
        }
    }

    @Override
    public void listHotels(ListHotelsRequest request, StreamObserver<ListHotelsResponse> responseObserver) {
        try {
            String city = request.getFilterCity();
            double minRating = request.getMinRating();
            int pageNumber = (request.getPageNumber() <= 0) ? 1 : request.getPageNumber();
            int pageSize = (request.getPageSize() <= 0) ? 10 : request.getPageSize();

            PagedHotels result = hotelService.listHotelsFilteredAndPaged(city, minRating, pageNumber, pageSize);

            List<hotelmanagementsystem.infrastructure.api.grpc.generated.Hotel> protoHotels = result.getHotels().stream()
                    .map(h -> HotelMapper.toDTO(h).toProtobuf())
                    .collect(Collectors.toList());

            ListHotelsResponse response = ListHotelsResponse.newBuilder()
                    .addAllHotels(protoHotels)
                    .setTotalCount(result.getTotalCount())
                    .setPageNumber(pageNumber)
                    .setPageSize(pageSize)
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
    public void updateHotel(UpdateHotelRequest request, StreamObserver<HotelResponse> responseObserver) {
        try {
            Map<String, String> updateMap = new HashMap<>();

            String newName = request.getName();
            String newDescription = request.getDescription();

            if (newName != null && !newName.isBlank()) {
                updateMap.put("name", newName);
            }
            if (newDescription != null && !newDescription.isBlank()) {
                updateMap.put("description", newDescription);
            }

            Hotel updatedHotel = hotelService.updateHotel(request.getId(), updateMap);

            HotelDTO hotelDTO = HotelMapper.toDTO(updatedHotel);
            HotelResponse response = HotelResponse.newBuilder()
                    .setHotel(hotelDTO.toProtobuf())
                    .build();

            responseObserver.onNext(response);
            responseObserver.onCompleted();
        } catch (HotelNotFoundException e) {
            responseObserver.onError(
                    Status.NOT_FOUND.withDescription(e.getMessage()).asRuntimeException()
            );
        } catch (Exception e) {
            responseObserver.onError(
                    Status.INTERNAL.withDescription(e.getMessage()).asRuntimeException()
            );
        }
    }

    @Override
    public void deleteHotel(DeleteHotelRequest request, StreamObserver<Empty> responseObserver) {
        try {
            hotelService.deleteHotel(request.getId());
            responseObserver.onNext(Empty.newBuilder().build());
            responseObserver.onCompleted();
        } catch (HotelNotFoundException e) {
            responseObserver.onError(
                    Status.NOT_FOUND.withDescription(e.getMessage()).asRuntimeException()
            );
        } catch (Exception e) {
            responseObserver.onError(
                    Status.INTERNAL.withDescription(e.getMessage()).asRuntimeException()
            );
        }
    }

    @Override
    public void rateHotel(RateHotelRequest request, StreamObserver<Empty> responseObserver) {
        try {
            hotelService.rateHotel(
                    request.getGuestId(),
                    request.getHotelId(),
                    request.getRating(),
                    request.getComment()
            );
            responseObserver.onNext(Empty.newBuilder().build());
            responseObserver.onCompleted();
        } catch (HotelNotFoundException e) {
            responseObserver.onError(
                    Status.NOT_FOUND.withDescription(e.getMessage()).asRuntimeException()
            );
        } catch (GuestNotFoundException e) {
            responseObserver.onError(
                    Status.NOT_FOUND.withDescription(e.getMessage()).asRuntimeException()
            );
        } catch (Exception e) {
            responseObserver.onError(
                    Status.INTERNAL.withDescription(e.getMessage()).asRuntimeException()
            );
        }
    }

    @Override
    public void findAvailableRooms(FindAvailableRoomsRequest request, StreamObserver<ListRoomsResponse> responseObserver) {
        try {
            LocalDate checkIn = LocalDate.parse(request.getCheckInDate());
            LocalDate checkOut = LocalDate.parse(request.getCheckOutDate());
            Class<? extends Room> roomClass = parseRoomType(request.getRoomType());
            List<Room> availableRooms = hotelService.findAvailableRooms(request.getHotelId(), roomClass);

            List<hotelmanagementsystem.infrastructure.api.grpc.generated.Room> roomProtos = availableRooms.stream()
                    .map(RoomMapper::toDTO)
                    .map(RoomDTO::toProtobuf)
                    .collect(Collectors.toList());

            ListRoomsResponse response = ListRoomsResponse.newBuilder()
                    .addAllRooms(roomProtos)
                    .build();

            responseObserver.onNext(response);
            responseObserver.onCompleted();
        } catch (HotelNotFoundException e) {
            responseObserver.onError(
                    Status.NOT_FOUND.withDescription(e.getMessage()).asRuntimeException()
            );
        } catch (Exception e) {
            responseObserver.onError(
                    Status.INTERNAL.withDescription(e.getMessage()).asRuntimeException()
            );
        }
    }

    private Class<? extends Room> parseRoomType(String typeName) {
        if (typeName == null || typeName.isBlank()) {
            return null;
        }
        switch (typeName.toUpperCase()) {
            case "SINGLE":
                return hotelmanagementsystem.domain.models.SingleRoom.class;
            case "DOUBLE":
                return hotelmanagementsystem.domain.models.DoubleRoom.class;
            default:
                throw new IllegalArgumentException("Unknown room type: " + typeName);
        }
    }
}
