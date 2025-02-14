package apiTests;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

import hotelmanagementsystem.domain.exceptions.HotelNotFoundException;
import hotelmanagementsystem.domain.models.*;
import hotelmanagementsystem.domain.services.HotelService;
import hotelmanagementsystem.infrastructure.api.grpc.generated.CreateHotelRequest;
import hotelmanagementsystem.infrastructure.api.grpc.generated.DeleteHotelRequest;
import hotelmanagementsystem.infrastructure.api.grpc.generated.GetHotelByIdRequest;
import hotelmanagementsystem.infrastructure.api.grpc.generated.HotelResponse;
import hotelmanagementsystem.infrastructure.api.grpc.generated.ListHotelsRequest;
import hotelmanagementsystem.infrastructure.api.grpc.generated.ListHotelsResponse;
import hotelmanagementsystem.infrastructure.api.grpc.generated.Empty;
import hotelmanagementsystem.infrastructure.api.grpc.generated.RateHotelRequest;
import hotelmanagementsystem.infrastructure.api.grpc.generated.UpdateHotelRequest;
import hotelmanagementsystem.infrastructure.api.grpc.generated.FindAvailableRoomsRequest;
import hotelmanagementsystem.infrastructure.api.grpc.generated.ListRoomsResponse;
import hotelmanagementsystem.infrastructure.api.grpc.impl.HotelServiceGrpcImpl;
import io.grpc.Status;
import io.grpc.StatusRuntimeException;
import io.grpc.stub.StreamObserver;
import java.time.LocalDate;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class HotelServiceGrpcImplTest {

    @Mock
    private HotelService hotelService;

    @InjectMocks
    private HotelServiceGrpcImpl grpcImpl;

    @Mock
    private StreamObserver<HotelResponse> hotelResponseObserver;

    @Mock
    private StreamObserver<Empty> emptyObserver;

    @Mock
    private StreamObserver<ListHotelsResponse> listHotelsResponseObserver;

    @Mock
    private StreamObserver<ListRoomsResponse> listRoomsResponseObserver;

    private Hotel dummyHotel;
    private HotelLocation dummyLocation;
    private PagedHotels dummyPagedHotels;

    @BeforeEach
    void setUp() {
        dummyLocation = new HotelLocation.HotelLocationBuilder()
                .withId(1L)
                .withAddress("123 Main St")
                .withCity("TestCity")
                .withCountry("TestCountry")
                .build();
        dummyHotel = new Hotel.HotelBuilder()
                .withId(1L)
                .withName("Test Hotel")
                .withDescription("Test Description")
                .withLocation(dummyLocation)
                .build();
        dummyPagedHotels = new PagedHotels(Collections.singletonList(dummyHotel), 1);
    }

    @Test
    void testCreateHotel_Success() {
        // Arrange
        hotelmanagementsystem.infrastructure.api.grpc.generated.HotelLocation grpcLocation =
                hotelmanagementsystem.infrastructure.api.grpc.generated.HotelLocation.newBuilder()
                        .setId(1L)
                        .setAddress("123 Main St")
                        .setCity("TestCity")
                        .setCountry("TestCountry")
                        .build();

        CreateHotelRequest request = CreateHotelRequest.newBuilder()
                .setName("Test Hotel")
                .setDescription("Test Description")
                .setLocation(grpcLocation)
                .build();

        when(hotelService.createHotel(eq("Test Hotel"), eq("Test Description"), any(HotelLocation.class)))
                .thenReturn(dummyHotel);

        // Act
        grpcImpl.createHotel(request, hotelResponseObserver);

        // Assert
        ArgumentCaptor<HotelResponse> captor = ArgumentCaptor.forClass(HotelResponse.class);
        verify(hotelResponseObserver, times(1)).onNext(captor.capture());
        verify(hotelResponseObserver, times(1)).onCompleted();

        HotelResponse response = captor.getValue();
        assertNotNull(response);
        assertEquals(dummyHotel.getId(), response.getHotel().getId());
        assertEquals("Test Hotel", response.getHotel().getName());
    }

    @Test
    void testGetHotelById_Success() throws Exception {
        // Arrange
        GetHotelByIdRequest request = GetHotelByIdRequest.newBuilder().setId(1L).build();
        when(hotelService.getHotelByHotelId(1L)).thenReturn(dummyHotel);

        // Act
        grpcImpl.getHotelById(request, hotelResponseObserver);

        // Assert
        ArgumentCaptor<HotelResponse> captor = ArgumentCaptor.forClass(HotelResponse.class);
        verify(hotelResponseObserver, times(1)).onNext(captor.capture());
        verify(hotelResponseObserver, times(1)).onCompleted();

        HotelResponse response = captor.getValue();
        assertNotNull(response);
        assertEquals(dummyHotel.getId(), response.getHotel().getId());
    }

    @Test
    void testGetHotelById_NotFound() throws Exception {
        // Arrange
        long invalidHotelId = 999L;
        GetHotelByIdRequest request = GetHotelByIdRequest.newBuilder()
                .setId(invalidHotelId)
                .build();

        // Mock Service exception
        when(hotelService.getHotelByHotelId(invalidHotelId))
                .thenThrow(new HotelNotFoundException("Hotel not found: " + invalidHotelId));

        // Act
        grpcImpl.getHotelById(request, hotelResponseObserver);

        // Assert
        verify(hotelService).getHotelByHotelId(invalidHotelId);

        ArgumentCaptor<StatusRuntimeException> errorCaptor = ArgumentCaptor.forClass(StatusRuntimeException.class);
        verify(hotelResponseObserver).onError(errorCaptor.capture());
        verify(hotelResponseObserver, never()).onNext(any());
        verify(hotelResponseObserver, never()).onCompleted();

        StatusRuntimeException exception = errorCaptor.getValue();
        assertEquals(Status.NOT_FOUND.getCode(), exception.getStatus().getCode());
        assertEquals("Hotel not found: 999", exception.getStatus().getDescription());
    }

    @Test
    void testListHotels_Success() {
        // Arrange
        ListHotelsRequest request = ListHotelsRequest.newBuilder()
                .setFilterCity("TestCity")
                .setMinRating(0.0)
                .setPageNumber(1)
                .setPageSize(10)
                .build();

        when(hotelService.listHotelsFilteredAndPaged("TestCity", 0.0, 1, 10))
                .thenReturn(dummyPagedHotels);

        // Act
        grpcImpl.listHotels(request, listHotelsResponseObserver);

        // Assert
        ArgumentCaptor<ListHotelsResponse> captor = ArgumentCaptor.forClass(ListHotelsResponse.class);
        verify(listHotelsResponseObserver, times(1)).onNext(captor.capture());
        verify(listHotelsResponseObserver, times(1)).onCompleted();

        ListHotelsResponse response = captor.getValue();
        assertNotNull(response);
        assertEquals(1, response.getHotelsCount());
        assertEquals(dummyPagedHotels.getTotalCount(), response.getTotalCount());
    }

    @Test
    void testUpdateHotel_Success() throws Exception {
        // Arrange
        UpdateHotelRequest request = UpdateHotelRequest.newBuilder()
                .setId(1L)
                .setName("Updated Hotel")
                .setDescription("Updated Description")
                .build();

        Map<String, String> updateMap = new HashMap<>();
        updateMap.put("name", "Updated Hotel");
        updateMap.put("description", "Updated Description");

        Hotel updatedHotel = new Hotel.HotelBuilder()
                .withId(1L)
                .withName("Updated Hotel")
                .withDescription("Updated Description")
                .withLocation(dummyLocation)
                .build();

        when(hotelService.updateHotel(1L, updateMap)).thenReturn(updatedHotel);

        // Act
        grpcImpl.updateHotel(request, hotelResponseObserver);

        // Assert
        ArgumentCaptor<HotelResponse> captor = ArgumentCaptor.forClass(HotelResponse.class);
        verify(hotelResponseObserver, times(1)).onNext(captor.capture());
        verify(hotelResponseObserver, times(1)).onCompleted();

        HotelResponse response = captor.getValue();
        assertNotNull(response);
        assertEquals("Updated Hotel", response.getHotel().getName());
    }

    @Test
    void testDeleteHotel_Success() throws Exception {
        // Arrange
        DeleteHotelRequest request = DeleteHotelRequest.newBuilder().setId(1L).build();
        // Statt doNothing() stubben wir den Rückgabewert (angenommen, deleteHotel gibt boolean zurück)
        when(hotelService.deleteHotel(1L)).thenReturn(true);

        // Act
        grpcImpl.deleteHotel(request, emptyObserver);

        // Assert
        verify(emptyObserver, times(1)).onNext(any(Empty.class));
        verify(emptyObserver, times(1)).onCompleted();
    }

    @Test
    void testRateHotel_Success() throws Exception {
        // Arrange
        RateHotelRequest request = RateHotelRequest.newBuilder()
                .setGuestId(1L)
                .setHotelId(1L)
                .setRating(5)
                .setComment("Excellent!")
                .build();

        doNothing().when(hotelService).rateHotel(1L, 1L, 5, "Excellent!");

        // Act
        grpcImpl.rateHotel(request, emptyObserver);

        // Assert
        verify(emptyObserver, times(1)).onNext(any(Empty.class));
        verify(emptyObserver, times(1)).onCompleted();
    }

    @Test
    void testFindAvailableRooms_Success() throws Exception {
        // Arrange
        // Dummy room – hier verwenden wir als Beispiel ein SingleRoom
        Room dummyRoom = new SingleRoom.Builder(100.0,
                new hotelmanagementsystem.domain.models.RoomIdentifier("A", 1, "101"),
                dummyHotel)
                .withId(1L)
                .build();

        FindAvailableRoomsRequest request = FindAvailableRoomsRequest.newBuilder()
                .setHotelId(1L)
                .setCheckInDate("2025-01-01")
                .setCheckOutDate("2025-01-02")
                .setRoomType("SINGLE")
                .build();

        when(hotelService.findAvailableRooms(1L, hotelmanagementsystem.domain.models.SingleRoom.class))
                .thenReturn(Collections.singletonList(dummyRoom));

        // Act
        grpcImpl.findAvailableRooms(request, listRoomsResponseObserver);

        // Assert
        ArgumentCaptor<ListRoomsResponse> captor = ArgumentCaptor.forClass(ListRoomsResponse.class);
        verify(listRoomsResponseObserver, times(1)).onNext(captor.capture());
        verify(listRoomsResponseObserver, times(1)).onCompleted();

        ListRoomsResponse response = captor.getValue();
        assertNotNull(response);
        // Wir erwarten genau ein Zimmer – anhand der ID
        assertEquals(1, response.getRoomsCount());
        assertEquals(1L, response.getRooms(0).getId());
    }
}
