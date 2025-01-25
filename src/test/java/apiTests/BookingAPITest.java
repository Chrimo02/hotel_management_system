package apiTests;

import hotelmanagementsystem.domain.exceptions.BookingNotFoundException;
import hotelmanagementsystem.domain.exceptions.RoomNotFoundException;
import hotelmanagementsystem.domain.models.Booking;
import hotelmanagementsystem.domain.models.Guest;
import hotelmanagementsystem.domain.models.Hotel;
import hotelmanagementsystem.domain.models.Room;
import hotelmanagementsystem.domain.services.BookingService;
import hotelmanagementsystem.infrastructure.api.dto.BookingDTO;
import hotelmanagementsystem.infrastructure.api.grpc.impl.BookingServiceGrpcImpl;
import hotelmanagementsystem.infrastructure.api.grpc.generated.*;
import hotelmanagementsystem.infrastructure.api.mapper.BookingMapper;
import io.grpc.Status;
import io.grpc.stub.StreamObserver;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

class BookingAPITest {

    @Mock
    private BookingService mockBookingService;

    @InjectMocks
    private BookingServiceGrpcImpl bookingServiceGrpcImpl;

    @Captor
    private ArgumentCaptor<BookingResponse> bookingResponseCaptor;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    /**
     * Helper method to create a sample domain Booking object.
     */
    private Booking createSampleDomainBooking() {
        Booking booking = mock(Booking.class);
        when(booking.getId()).thenReturn(1L);
        Hotel hotel = mock(Hotel.class);
        when(hotel.getId()).thenReturn(100L);
        when(booking.getHotel()).thenReturn(hotel);
        Guest guest = mock(Guest.class);
        when(guest.getId()).thenReturn(200L);
        when(booking.getGuests()).thenReturn(Arrays.asList(guest));
        Room room1 = mock(Room.class);
        when(room1.getId()).thenReturn(300L);
        Room room2 = mock(Room.class);
        when(room2.getId()).thenReturn(301L);
        when(booking.getRooms()).thenReturn(Arrays.asList(room1, room2));
        when(booking.getCheckInDate()).thenReturn(LocalDate.of(2025, 3, 1));
        when(booking.getCheckOutDate()).thenReturn(LocalDate.of(2025, 3, 5));
        when(booking.getStatus()).thenReturn(true);
        when(booking.getCheckInTime()).thenReturn(null);
        when(booking.getCheckOutTime()).thenReturn(null);
        return booking;
    }

    /**
     * Helper method to create a sample proto Booking message.
     */
    private hotelmanagementsystem.infrastructure.api.grpc.generated.Booking createSampleProtoBooking() {
        return hotelmanagementsystem.infrastructure.api.grpc.generated.Booking.newBuilder()
                .setId(1L)
                .setHotelId(100L)
                .setGuestId(200L)
                .addAllRoomIds(Arrays.asList(300L, 301L))
                .setCheckInDate("2025-03-01")
                .setCheckOutDate("2025-03-05")
                .build();
    }

    @Test
    void testCreateBooking_Success() throws BookingNotFoundException, RoomNotFoundException {
        // Arrange
        CreateBookingRequest request = CreateBookingRequest.newBuilder()
                .setHotelId(100L)
                .setGuestId(200L)
                .addAllRoomIds(Arrays.asList(300L, 301L))
                .setCheckInDate("2025-03-01")
                .setCheckOutDate("2025-03-05")
                .build();

        Booking mockDomainBooking = createSampleDomainBooking();
        when(mockBookingService.makeBooking(
                eq(100L),
                eq(LocalDate.parse("2025-03-01")),
                eq(LocalDate.parse("2025-03-05")),
                anyList(),
                anyList()
        )).thenReturn(mockDomainBooking);

        // Use real BookingMapper.toDTO
        BookingDTO bookingDTO = BookingMapper.toDTO(mockDomainBooking);

        // Create a spy for BookingDTO to mock toProtobuf()
        BookingDTO spyBookingDTO = spy(bookingDTO);
        hotelmanagementsystem.infrastructure.api.grpc.generated.Booking protoBooking = createSampleProtoBooking();
        doReturn(protoBooking).when(spyBookingDTO).toProtobuf();

        // Mock static BookingMapper.toDTO using Mockito's mockStatic
        try (MockedStatic<BookingMapper> mockedMapper = mockStatic(BookingMapper.class)) {
            mockedMapper.when(() -> BookingMapper.toDTO(mockDomainBooking)).thenReturn(spyBookingDTO);

            StreamObserver<BookingResponse> responseObserver = mock(StreamObserver.class);

            // Act
            bookingServiceGrpcImpl.createBooking(request, responseObserver);

            // Assert
            verify(mockBookingService).makeBooking(
                    eq(100L),
                    eq(LocalDate.parse("2025-03-01")),
                    eq(LocalDate.parse("2025-03-05")),
                    anyList(),
                    anyList()
            );

            ArgumentCaptor<BookingResponse> responseCaptor = ArgumentCaptor.forClass(BookingResponse.class);
            verify(responseObserver).onNext(responseCaptor.capture());
            verify(responseObserver).onCompleted();

            BookingResponse response = responseCaptor.getValue();
            assertNotNull(response);
            assertEquals(1L, response.getBooking().getId());
            assertEquals(100L, response.getBooking().getHotelId());
            assertEquals(200L, response.getBooking().getGuestId());
            assertEquals(Arrays.asList(300L, 301L), response.getBooking().getRoomIdsList());
            assertEquals("2025-03-01", response.getBooking().getCheckInDate());
            assertEquals("2025-03-05", response.getBooking().getCheckOutDate());
        }
    }

    @Test
    void testGetBookingById_Success() throws BookingNotFoundException {
        // Arrange
        GetBookingByIdRequest request = GetBookingByIdRequest.newBuilder()
                .setId(1L)
                .build();

        Booking mockDomainBooking = createSampleDomainBooking();
        when(mockBookingService.getBookingById(1L)).thenReturn(mockDomainBooking);

        BookingDTO bookingDTO = BookingMapper.toDTO(mockDomainBooking);
        hotelmanagementsystem.infrastructure.api.grpc.generated.Booking protoBooking = createSampleProtoBooking();

        // Create a spy for BookingDTO to mock toProtobuf()
        BookingDTO spyBookingDTO = spy(bookingDTO);
        doReturn(protoBooking).when(spyBookingDTO).toProtobuf();

        // Mock static BookingMapper.toDTO using Mockito's mockStatic
        try (MockedStatic<BookingMapper> mockedMapper = mockStatic(BookingMapper.class)) {
            mockedMapper.when(() -> BookingMapper.toDTO(mockDomainBooking)).thenReturn(spyBookingDTO);

            StreamObserver<BookingResponse> responseObserver = mock(StreamObserver.class);

            // Act
            bookingServiceGrpcImpl.getBookingById(request, responseObserver);

            // Assert
            verify(mockBookingService).getBookingById(1L);

            ArgumentCaptor<BookingResponse> responseCaptor = ArgumentCaptor.forClass(BookingResponse.class);
            verify(responseObserver).onNext(responseCaptor.capture());
            verify(responseObserver).onCompleted();

            BookingResponse response = responseCaptor.getValue();
            assertNotNull(response);
            assertEquals(1L, response.getBooking().getId());
            assertEquals(100L, response.getBooking().getHotelId());
            assertEquals(200L, response.getBooking().getGuestId());
            assertEquals(Arrays.asList(300L, 301L), response.getBooking().getRoomIdsList());
            assertEquals("2025-03-01", response.getBooking().getCheckInDate());
            assertEquals("2025-03-05", response.getBooking().getCheckOutDate());
        }
    }

    @Test
    void testCancelBooking_Success() throws BookingNotFoundException, RoomNotFoundException {
        // Arrange
        CancelBookingRequest request = CancelBookingRequest.newBuilder()
                .setId(1L)
                .build();

        StreamObserver<Empty> responseObserver = mock(StreamObserver.class);

        // Act
        bookingServiceGrpcImpl.cancelBooking(request, responseObserver);

        // Assert
        verify(mockBookingService).cancelBooking(1L);
        verify(responseObserver).onNext(any(Empty.class));
        verify(responseObserver).onCompleted();
    }

    @Test
    void testCancelBooking_NotFoundException() throws BookingNotFoundException, RoomNotFoundException {
        // Arrange
        CancelBookingRequest request = CancelBookingRequest.newBuilder()
                .setId(999L)
                .build();

        doThrow(new BookingNotFoundException("Booking not found")).when(mockBookingService).cancelBooking(999L);

        StreamObserver<Empty> responseObserver = mock(StreamObserver.class);

        // Act
        bookingServiceGrpcImpl.cancelBooking(request, responseObserver);

        // Assert
        verify(mockBookingService).cancelBooking(999L);
        verify(responseObserver).onError(argThat(argument ->
                argument instanceof io.grpc.StatusRuntimeException &&
                        ((io.grpc.StatusRuntimeException) argument).getStatus().getCode() == Status.Code.NOT_FOUND &&
                        ((io.grpc.StatusRuntimeException) argument).getStatus().getDescription().equals("Booking not found")
        ));
    }

    @Test
    void testCheckInGuest_Success() throws BookingNotFoundException {
        // Arrange
        CheckInGuestRequest request = CheckInGuestRequest.newBuilder()
                .setBookingId(1L)
                .build();

        StreamObserver<Empty> responseObserver = mock(StreamObserver.class);

        // Act
        bookingServiceGrpcImpl.checkInGuest(request, responseObserver);

        // Assert
        verify(mockBookingService).guestCheckIn(1L);
        verify(responseObserver).onNext(any(Empty.class));
        verify(responseObserver).onCompleted();
    }

    @Test
    void testCheckOutGuest_Success() throws BookingNotFoundException {
        // Arrange
        CheckOutGuestRequest request = CheckOutGuestRequest.newBuilder()
                .setBookingId(1L)
                .build();

        StreamObserver<Empty> responseObserver = mock(StreamObserver.class);

        // Act
        bookingServiceGrpcImpl.checkOutGuest(request, responseObserver);

        // Assert
        verify(mockBookingService).guestCheckOut(1L);
        verify(responseObserver).onNext(any(Empty.class));
        verify(responseObserver).onCompleted();
    }

    @Test
    void testGetBookingById_NotFoundException() throws BookingNotFoundException {
        // Arrange
        GetBookingByIdRequest request = GetBookingByIdRequest.newBuilder()
                .setId(999L)
                .build();

        when(mockBookingService.getBookingById(999L)).thenThrow(new BookingNotFoundException("Booking not found"));

        StreamObserver<BookingResponse> responseObserver = mock(StreamObserver.class);

        // Act
        bookingServiceGrpcImpl.getBookingById(request, responseObserver);

        // Assert
        verify(mockBookingService).getBookingById(999L);
        verify(responseObserver).onError(argThat(argument ->
                argument instanceof io.grpc.StatusRuntimeException &&
                        ((io.grpc.StatusRuntimeException) argument).getStatus().getCode() == Status.Code.NOT_FOUND &&
                        ((io.grpc.StatusRuntimeException) argument).getStatus().getDescription().equals("Booking not found")
        ));
    }

    @Test
    void testCreateBooking_InternalException() throws BookingNotFoundException, RoomNotFoundException {
        // Arrange
        CreateBookingRequest request = CreateBookingRequest.newBuilder()
                .setHotelId(100L)
                .setGuestId(200L)
                .addAllRoomIds(Arrays.asList(300L, 301L))
                .setCheckInDate("2025-03-01")
                .setCheckOutDate("2025-03-05")
                .build();

        when(mockBookingService.makeBooking(
                eq(100L),
                eq(LocalDate.parse("2025-03-01")),
                eq(LocalDate.parse("2025-03-05")),
                anyList(),
                anyList()
        )).thenThrow(new RuntimeException("Internal Server Error"));

        // Use real BookingMapper.toDTO
        BookingDTO bookingDTO = BookingMapper.toDTO(createSampleDomainBooking());

        // Create a spy for BookingDTO to mock toProtobuf()
        BookingDTO spyBookingDTO = spy(bookingDTO);
        hotelmanagementsystem.infrastructure.api.grpc.generated.Booking protoBooking = createSampleProtoBooking();
        doReturn(protoBooking).when(spyBookingDTO).toProtobuf();

        // Mock static BookingMapper.toDTO using Mockito's mockStatic
        try (MockedStatic<BookingMapper> mockedMapper = mockStatic(BookingMapper.class)) {
            mockedMapper.when(() -> BookingMapper.toDTO(any(Booking.class))).thenReturn(spyBookingDTO);

            StreamObserver<BookingResponse> responseObserver = mock(StreamObserver.class);

            // Act
            bookingServiceGrpcImpl.createBooking(request, responseObserver);

            // Assert
            verify(mockBookingService).makeBooking(
                    eq(100L),
                    eq(LocalDate.parse("2025-03-01")),
                    eq(LocalDate.parse("2025-03-05")),
                    anyList(),
                    anyList()
            );

            verify(responseObserver).onError(argThat(argument ->
                    argument instanceof io.grpc.StatusRuntimeException &&
                            ((io.grpc.StatusRuntimeException) argument).getStatus().getCode() == Status.Code.INTERNAL &&
                            ((io.grpc.StatusRuntimeException) argument).getStatus().getDescription().equals("Internal Server Error")
            ));
        }
    }
}
