package apiTests;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import hotelmanagementsystem.domain.exceptions.HotelNotFoundException;
import hotelmanagementsystem.domain.exceptions.RoomNotFoundException;
import hotelmanagementsystem.domain.models.DoubleRoom;
import hotelmanagementsystem.domain.models.Hotel;
import hotelmanagementsystem.domain.models.Room;
import hotelmanagementsystem.domain.models.RoomIdentifier;
import hotelmanagementsystem.domain.models.SingleRoom;
import hotelmanagementsystem.domain.services.RoomService;
import hotelmanagementsystem.infrastructure.api.dto.RoomDTO;
import hotelmanagementsystem.infrastructure.api.grpc.generated.CreateRoomRequest;
import hotelmanagementsystem.infrastructure.api.grpc.generated.GetRoomByIdRequest;
import hotelmanagementsystem.infrastructure.api.grpc.generated.RemoveRoomRequest;
import hotelmanagementsystem.infrastructure.api.grpc.generated.RoomResponse;
import hotelmanagementsystem.infrastructure.api.grpc.generated.UpdateRoomPriceRequest;
import hotelmanagementsystem.infrastructure.api.grpc.generated.Empty;
import hotelmanagementsystem.infrastructure.api.grpc.impl.RoomServiceGrpcImpl;
import io.grpc.Status;
import io.grpc.StatusRuntimeException;
import io.grpc.stub.StreamObserver;
import java.time.LocalDate;
import java.util.Collections;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class RoomServiceGrpcImplTest {

    @Mock
    private RoomService roomService;

    @InjectMocks
    private RoomServiceGrpcImpl grpcImpl;

    @Mock
    private StreamObserver<RoomResponse> roomResponseObserver;

    @Mock
    private StreamObserver<Empty> emptyObserver;

    private Room dummySingleRoom;
    private Room dummyDoubleRoom;
    private RoomIdentifier dummyRoomIdentifier;
    private Hotel dummyHotel;

    @BeforeEach
    void setUp() {
        dummyRoomIdentifier = new RoomIdentifier("Building A", 1, "101");
        // Erstelle ein Dummy-Hotel mit gültiger ID
        dummyHotel = new Hotel.HotelBuilder()
                .withId(1L)
                .withName("Test Hotel")
                .build();

        // Erstelle ein Dummy-SingleRoom mit gültigem Hotel
        dummySingleRoom = new SingleRoom.Builder(100.0, dummyRoomIdentifier, dummyHotel)
                .withId(1L)
                .build();
        // Erstelle ein Dummy-DoubleRoom mit gültigem Hotel
        dummyDoubleRoom = new DoubleRoom.Builder(150.0, dummyRoomIdentifier, dummyHotel)
                .withId(2L)
                .build();
    }

    @Test
    void testCreateRoom_SingleRoom_Success() throws RoomNotFoundException, HotelNotFoundException {
        // Arrange
        CreateRoomRequest request = CreateRoomRequest.newBuilder()
                .setType("SINGLE")
                .setPricePerNight(100.0)
                .setHotelId(1L)
                .setRoomIdentifier(
                        hotelmanagementsystem.infrastructure.api.grpc.generated.RoomIdentifier.newBuilder()
                                .setBuilding("Building A")
                                .setFloor(1)
                                .setRoomNumber("101")
                                .build()
                )
                .build();

        // Erwartung: Der Service wird mit einem SingleRoom arbeiten
        when(roomService.createRoom(eq(100.0), any(RoomIdentifier.class), eq(1L), eq(SingleRoom.class)))
                .thenReturn(dummySingleRoom);

        // Act
        grpcImpl.createRoom(request, roomResponseObserver);

        // Assert
        ArgumentCaptor<RoomResponse> captor = ArgumentCaptor.forClass(RoomResponse.class);
        verify(roomResponseObserver, times(1)).onNext(captor.capture());
        verify(roomResponseObserver, times(1)).onCompleted();

        RoomResponse response = captor.getValue();
        assertNotNull(response);
        assertEquals(1L, response.getRoom().getId());
        assertEquals(100.0, response.getRoom().getPricePerNight());
        assertEquals("SingleRoom", response.getRoom().getType());
    }

    @Test
    void testCreateRoom_DoubleRoom_Success() throws RoomNotFoundException, HotelNotFoundException {
        // Arrange
        CreateRoomRequest request = CreateRoomRequest.newBuilder()
                .setType("DOUBLE")
                .setPricePerNight(150.0)
                .setHotelId(1L)
                .setRoomIdentifier(
                        hotelmanagementsystem.infrastructure.api.grpc.generated.RoomIdentifier.newBuilder()
                                .setBuilding("Building A")
                                .setFloor(1)
                                .setRoomNumber("101")
                                .build()
                )
                .build();

        when(roomService.createRoom(eq(150.0), any(RoomIdentifier.class), eq(1L), eq(DoubleRoom.class)))
                .thenReturn(dummyDoubleRoom);

        // Act
        grpcImpl.createRoom(request, roomResponseObserver);

        // Assert
        ArgumentCaptor<RoomResponse> captor = ArgumentCaptor.forClass(RoomResponse.class);
        verify(roomResponseObserver, times(1)).onNext(captor.capture());
        verify(roomResponseObserver, times(1)).onCompleted();

        RoomResponse response = captor.getValue();
        assertNotNull(response);
        assertEquals(2L, response.getRoom().getId());
        assertEquals(150.0, response.getRoom().getPricePerNight());
        assertEquals("DoubleRoom", response.getRoom().getType());
    }

    @Test
    void testGetRoomById_Success() throws RoomNotFoundException {
        // Arrange
        GetRoomByIdRequest request = GetRoomByIdRequest.newBuilder().setId(1L).build();
        when(roomService.getRoomById(1L)).thenReturn(dummySingleRoom);

        // Act
        grpcImpl.getRoomById(request, roomResponseObserver);

        // Assert
        ArgumentCaptor<RoomResponse> captor = ArgumentCaptor.forClass(RoomResponse.class);
        verify(roomResponseObserver, times(1)).onNext(captor.capture());
        verify(roomResponseObserver, times(1)).onCompleted();

        RoomResponse response = captor.getValue();
        assertNotNull(response);
        assertEquals(1L, response.getRoom().getId());
        assertEquals(100.0, response.getRoom().getPricePerNight());
    }

    @Test
    void testGetRoomById_NotFound() throws RoomNotFoundException {
        // Arrange
        GetRoomByIdRequest request = GetRoomByIdRequest.newBuilder().setId(999L).build();
        when(roomService.getRoomById(999L)).thenThrow(new RuntimeException("Room not found"));

        // Act
        grpcImpl.getRoomById(request, roomResponseObserver);

        // Assert
        verify(roomResponseObserver, never()).onNext(any(RoomResponse.class));
        verify(roomResponseObserver, never()).onCompleted();
        ArgumentCaptor<StatusRuntimeException> errorCaptor = ArgumentCaptor.forClass(StatusRuntimeException.class);
        verify(roomResponseObserver, times(1)).onError(errorCaptor.capture());
        StatusRuntimeException ex = errorCaptor.getValue();
        assertEquals(Status.NOT_FOUND.getCode(), ex.getStatus().getCode());
        assertTrue(ex.getStatus().getDescription().contains("Room not found"));
    }

    @Test
    void testUpdateRoomPrice_Success() throws RoomNotFoundException {
        // Arrange
        UpdateRoomPriceRequest request = UpdateRoomPriceRequest.newBuilder()
                .setRoomId(1L)
                .setNewPricePerNight(120.0)
                .build();

        // Erstelle ein aktualisiertes Room-Objekt mit dem neuen Preis
        Room updatedRoom = new SingleRoom.Builder(120.0, dummyRoomIdentifier, dummyHotel)
                .withId(1L)
                .build();

        // Simuliere, dass der Service updatePricePerNight das aktualisierte Room-Objekt zurückgibt
        when(roomService.updatePricePerNight(1L, 120.0)).thenReturn(updatedRoom);
        // Simuliere außerdem, dass getRoomById das aktualisierte Room-Objekt zurückgibt
        when(roomService.getRoomById(1L)).thenReturn(updatedRoom);

        // Act
        grpcImpl.updateRoomPrice(request, roomResponseObserver);

        // Assert
        ArgumentCaptor<RoomResponse> captor = ArgumentCaptor.forClass(RoomResponse.class);
        verify(roomResponseObserver, times(1)).onNext(captor.capture());
        verify(roomResponseObserver, times(1)).onCompleted();

        RoomResponse response = captor.getValue();
        assertNotNull(response);
        assertEquals(1L, response.getRoom().getId());
        assertEquals(120.0, response.getRoom().getPricePerNight());
    }


    @Test
    void testRemoveRoom_Success() throws RoomNotFoundException {
        // Arrange
        RemoveRoomRequest request = RemoveRoomRequest.newBuilder().setRoomId(1L).build();
        doNothing().when(roomService).removeRoom(1L);

        // Act
        grpcImpl.removeRoom(request, emptyObserver);

        // Assert
        verify(emptyObserver, times(1)).onNext(any(Empty.class));
        verify(emptyObserver, times(1)).onCompleted();
    }
}
