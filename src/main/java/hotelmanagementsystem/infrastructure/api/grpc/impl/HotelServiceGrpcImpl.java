package hotelmanagementsystem.infrastructure.api.grpc.impl;

import hotelmanagementsystem.domain.models.Hotel;
import hotelmanagementsystem.domain.exceptions.HotelNotFoundException;
import hotelmanagementsystem.domain.models.HotelRating;
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
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.inject.Singleton;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Vollständige gRPC-Implementierung für den HotelService,
 * angelehnt an das Vorgehen in GuestServiceGrpcImpl.
 */
@GrpcService
@Singleton
@Blocking
public class HotelServiceGrpcImpl extends HotelServiceGrpc.HotelServiceImplBase {

    private final HotelService hotelService;

    @Inject
    public HotelServiceGrpcImpl(HotelService hotelService) {
        this.hotelService = hotelService;
    }

    /**
     * 1) Hotel anlegen
     */
    @Override
    public void createHotel(CreateHotelRequest request, StreamObserver<HotelResponse> responseObserver) {
        try {
            // Hier musst du selbst definieren, welche Felder aus dem Request
            // für dein Domain-Objekt relevant sind (z.B. Location, Ratings etc.).
            // Als Minimalbeispiel nehmen wir Name und Description.
            hotelmanagementsystem.domain.models.HotelLocation hotelLocation = new hotelmanagementsystem.domain.models.HotelLocation.HotelLocationBuilder(
                    request.getLocation().getId())
                    .withAddress(request.getLocation().getAddress())
                    .withCity(request.getLocation().getCity())
                    .withCountry(request.getLocation().getCountry())
                    .build();
            Hotel createdHotel = hotelService.createHotel(
                    request.getName(),
                    request.getDescription(),
                    hotelLocation
            );

            // In DTO konvertieren und dann in das passende Protobuf-Objekt
            HotelDTO hotelDTO = HotelMapper.toDTO(createdHotel);

            HotelResponse response = HotelResponse.newBuilder()
                    .setHotel(hotelDTO.toProtobuf())
                    .build();

            responseObserver.onNext(response);
            responseObserver.onCompleted();

        } catch (Exception e) {
            // Generischer Fehler
            responseObserver.onError(
                    Status.INTERNAL
                            .withDescription(e.getMessage())
                            .asRuntimeException()
            );
        }
    }

    /**
     * 2) Hotel anhand seiner ID abfragen
     */
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
            // Falls das Hotel nicht gefunden wird
            responseObserver.onError(
                    Status.NOT_FOUND
                            .withDescription(e.getMessage())
                            .asRuntimeException()
            );
        } catch (Exception e) {
            // Generischer Fehler
            responseObserver.onError(
                    Status.INTERNAL
                            .withDescription(e.getMessage())
                            .asRuntimeException()
            );
        }
    }

    /**
     * 3) Liste aller Hotels abfragen
     */
    @Override
    public void listHotels(ListHotelsRequest request, StreamObserver<ListHotelsResponse> responseObserver) {
        try {
            // Filter-Parameter ziehen:
            String city = request.getFilterCity();
            double minRating = request.getMinRating();
            int pageNumber = request.getPageNumber() == 0 ? 1 : request.getPageNumber();
            int pageSize = request.getPageSize() == 0 ? 10 : request.getPageSize();

            PagedHotels result = hotelService.listHotelsFilteredAndPaged(city,minRating,pageNumber,pageSize);


            // Mapping in DTOs
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
                    Status.INTERNAL
                            .withDescription(e.getMessage())
                            .asRuntimeException()
            );
        }
    }

    /**
     * 4) Hotel-Daten aktualisieren
     */
    @Override
    public void updateHotel(UpdateHotelRequest request, StreamObserver<HotelResponse> responseObserver) {
        try {
            // Beispielhafter Ansatz: Wir nehmen an, dass man den Namen und/oder
            // Beschreibung ändern kann. Entsprechend muss deine Service-Methode
            // aussehen. Falls du mehr Felder hast, einfach ergänzen.
            Map<String, String> updateMap = new HashMap<>();
            updateMap.put(request.getName(),request.getDescription());
            Hotel updatedHotel = hotelService.updateHotel(
                    request.getId(),
                    updateMap
            );

            HotelDTO hotelDTO = HotelMapper.toDTO(updatedHotel);

            HotelResponse response = HotelResponse.newBuilder()
                    .setHotel(hotelDTO.toProtobuf())
                    .build();

            responseObserver.onNext(response);
            responseObserver.onCompleted();

        } catch (HotelNotFoundException e) {
            responseObserver.onError(
                    Status.NOT_FOUND
                            .withDescription(e.getMessage())
                            .asRuntimeException()
            );
        } catch (Exception e) {
            responseObserver.onError(
                    Status.INTERNAL
                            .withDescription(e.getMessage())
                            .asRuntimeException()
            );
        }
    }

    /**
     * 5) Hotel löschen
     */
    @Override
    public void deleteHotel(DeleteHotelRequest request, StreamObserver<Empty> responseObserver) {
        try {
            hotelService.deleteHotel(request.getId());

            responseObserver.onNext(Empty.newBuilder().build());
            responseObserver.onCompleted();

        } catch (HotelNotFoundException e) {
            responseObserver.onError(
                    Status.NOT_FOUND
                            .withDescription(e.getMessage())
                            .asRuntimeException()
            );
        } catch (Exception e) {
            responseObserver.onError(
                    Status.INTERNAL
                            .withDescription(e.getMessage())
                            .asRuntimeException()
            );
        }
    }

    /**
     * 6) Hotel bewerten
     */
    @Override
    public void rateHotel(RateHotelRequest request, StreamObserver<Empty> responseObserver) {
        try {
            // Beispiel: Du könntest einen Service-Aufruf haben wie
            // hotelService.rateHotel(hotelId, guestId, ratingValue, comment)
            HotelRating hotelRating = new HotelRating.Builder().withRating(request.getRating()).withComment(request.getComment()).build();
            hotelService.rateHotel(
                    request.getHotelId(),
                    request.getGuestId(),
                    hotelRating
            );

            responseObserver.onNext(Empty.newBuilder().build());
            responseObserver.onCompleted();

        } catch (HotelNotFoundException e) {
            responseObserver.onError(
                    Status.NOT_FOUND
                            .withDescription(e.getMessage())
                            .asRuntimeException()
            );
        } catch (Exception e) {
            responseObserver.onError(
                    Status.INTERNAL
                            .withDescription(e.getMessage())
                            .asRuntimeException()
            );
        }
    }
/* --> findAvailableRooms ist im RoomService definiert

    @Override
    public void findAvailableRooms(FindAvailableRoomsRequest request, StreamObserver<ListRoomsResponse> responseObserver) {
        try {
            // Beispiel: hotelService.findAvailableRooms(hotelId, checkInDate, checkOutDate)
            LocalDate checkIn = LocalDate.parse(request.getCheckInDate());
            LocalDate checkOut = LocalDate.parse(request.getCheckOutDate());

            List<Room> availableRooms = hotelService.findAvailableRooms(
                    request.getHotelId(),
                    checkIn,
                    checkOut
            );

            // Rooms in Protobuf-Messages konvertieren
            List<hotelmanagementsystem.infrastructure.api.grpc.generated.Room> roomProtos = availableRooms.stream()
                    .map(RoomMapper::toDTO)       // in dein eigenes DTO
                    .map(RoomDTO::toProtobuf)     // dann in die Protobuf-Entität
                    .collect(Collectors.toList());

            ListRoomsResponse response = ListRoomsResponse.newBuilder()
                    .addAllRooms(roomProtos)
                    .build();

            responseObserver.onNext(response);
            responseObserver.onCompleted();

        } catch (HotelNotFoundException e) {
            responseObserver.onError(
                    Status.NOT_FOUND
                            .withDescription(e.getMessage())
                            .asRuntimeException()
            );
        } catch (Exception e) {
            responseObserver.onError(
                    Status.INTERNAL
                            .withDescription(e.getMessage())
                            .asRuntimeException()
            );
        }
    }

 */
}
