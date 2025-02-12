/*
package apiTests;

import hotelmanagementsystem.domain.exceptions.BookingNotFoundException;
import hotelmanagementsystem.domain.exceptions.RoomNotFoundException;
import hotelmanagementsystem.domain.models.Booking;
import hotelmanagementsystem.domain.models.Guest;
import hotelmanagementsystem.domain.models.Hotel;
import hotelmanagementsystem.domain.models.Room;
import hotelmanagementsystem.domain.services.BookingService;
import hotelmanagementsystem.infrastructure.api.dto.BookingDTO;
import hotelmanagementsystem.infrastructure.api.grpc.generated.*;
import hotelmanagementsystem.infrastructure.api.grpc.impl.BookingServiceGrpcImpl;
import hotelmanagementsystem.infrastructure.api.mapper.BookingMapper;
import io.grpc.Status;
import io.grpc.StatusRuntimeException;
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

    // Helper to create a domain booking with multiple guests
    private Booking createSampleDomainBooking() {
        Booking booking = mock(Booking.class);
        when(booking.getId()).thenReturn(1L);

        Hotel mockHotel = mock(Hotel.class);
        when(mockHotel.getId()).thenReturn(100L);
        when(booking.getHotel()).thenReturn(mockHotel);

        Guest guest1 = mock(Guest.class);
        when(guest1.getId()).thenReturn(200L);
        Guest guest2 = mock(Guest.class);
        when(guest2.getId()).thenReturn(201L);
        when(booking.getGuests()).thenReturn(Arrays.asList(guest1, guest2));

        Room room1 = mock(Room.class);
        when(room1.getId()).thenReturn(300L);
        Room room2 = mock(Room.class);
        when(room2.getId()).thenReturn(301L);
        when(booking.getRooms()).thenReturn(Arrays.asList(room1, room2));

        when(booking.getCheckInDate()).thenReturn(LocalDate.of(2025, 3, 1));
        when(booking.getCheckOutDate()).thenReturn(LocalDate.of(2025, 3, 5));
        when(booking.getStatus()).thenReturn(true);

        return booking;
    }

    @Test
    void testCreateBooking_Success() throws BookingNotFoundException, RoomNotFoundException {
        // Arrange
        // The client calls the API with repeated room types, repeated guest IDs
        CreateBookingRequest request = CreateBookingRequest.newBuilder()
                .setHotelId(100L)
                .addAllRoomTypes(Arrays.asList("SINGLE", "DOUBLE"))
                .addAllGuestIds(Arrays.asList(200L, 201L))
                .setCheckInDate("2025-03-01")
                .setCheckOutDate("2025-03-05")
                .build();

        Booking mockDomainBooking = createSampleDomainBooking();

        // Simulate domain service returning a booking
        when(mockBookingService.makeBooking(
                eq(100L),
                eq(LocalDate.of(2025, 3, 1)),
                eq(LocalDate.of(2025, 3, 5)),
                anyList(),   // roomTypes as classes
                eq(Arrays.asList(200L, 201L))
        )).thenReturn(mockDomainBooking);

        // Use real BookingMapper.toDTO on that sample booking
        BookingDTO realDTO = BookingMapper.toDTO(mockDomainBooking);

        // We create a spy for the returned DTO to override `toProtobuf()`
        BookingDTO spyDTO = spy(realDTO);

        // Build a proto booking that matches domain data
        hotelmanagementsystem.infrastructure.api.grpc.generated.Booking protoBooking =
                hotelmanagementsystem.infrastructure.api.grpc.generated.Booking.newBuilder()
                        .setId(1L)
                        .setHotelId(100L)
                        .addAllGuestIds(Arrays.asList(200L, 201L))
                        .addAllRoomIds(Arrays.asList(300L, 301L))
                        .setCheckInDate("2025-03-01")
                        .setCheckOutDate("2025-03-05")
                        .build();
        doReturn(protoBooking).when(spyDTO).toProtobuf();

        try (MockedStatic<BookingMapper> mapperStub = mockStatic(BookingMapper.class)) {
            // Whenever BookingMapper.toDTO(mockDomainBooking) is called, return our spy
            mapperStub.when(() -> BookingMapper.toDTO(mockDomainBooking))
                    .thenReturn(spyDTO);

            StreamObserver<BookingResponse> responseObserver = mock(StreamObserver.class);

            // Act
            bookingServiceGrpcImpl.createBooking(request, responseObserver);

            // Assert
            verify(mockBookingService).makeBooking(
                    eq(100L),
                    eq(LocalDate.parse("2025-03-01")),
                    eq(LocalDate.parse("2025-03-05")),
                    anyList(),  // you can further check the parseRoomType logic if you want
                    eq(Arrays.asList(200L, 201L))
            );

            ArgumentCaptor<BookingResponse> responseCaptor =
                    ArgumentCaptor.forClass(BookingResponse.class);
            verify(responseObserver).onNext(responseCaptor.capture());
            verify(responseObserver).onCompleted();

            BookingResponse actualResponse = responseCaptor.getValue();
            assertNotNull(actualResponse, "Response should not be null");
            hotelmanagementsystem.infrastructure.api.grpc.generated.Booking b = actualResponse.getBooking();
            assertEquals(1L, b.getId());
            assertEquals(100L, b.getHotelId());
            assertEquals(Arrays.asList(200L, 201L), b.getGuestIdsList());
            assertEquals(Arrays.asList(300L, 301L), b.getRoomIdsList());
            assertEquals("2025-03-01", b.getCheckInDate());
            assertEquals("2025-03-05", b.getCheckOutDate());
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

        BookingDTO realDTO = BookingMapper.toDTO(mockDomainBooking);
        BookingDTO spyDTO = spy(realDTO);

        hotelmanagementsystem.infrastructure.api.grpc.generated.Booking protoBooking =
                hotelmanagementsystem.infrastructure.api.grpc.generated.Booking.newBuilder()
                        .setId(1L)
                        .setHotelId(100L)
                        .addAllGuestIds(Arrays.asList(200L, 201L))
                        .addAllRoomIds(Arrays.asList(300L, 301L))
                        .setCheckInDate("2025-03-01")
                        .setCheckOutDate("2025-03-05")
                        .build();
        doReturn(protoBooking).when(spyDTO).toProtobuf();

        try (MockedStatic<BookingMapper> mapperStub = mockStatic(BookingMapper.class)) {
            mapperStub.when(() -> BookingMapper.toDTO(mockDomainBooking))
                    .thenReturn(spyDTO);

            StreamObserver<BookingResponse> responseObserver = mock(StreamObserver.class);

            // Act
            bookingServiceGrpcImpl.getBookingById(request, responseObserver);

            // Assert
            verify(mockBookingService).getBookingById(1L);
            ArgumentCaptor<BookingResponse> captor = ArgumentCaptor.forClass(BookingResponse.class);
            verify(responseObserver).onNext(captor.capture());
            verify(responseObserver).onCompleted();

            BookingResponse res = captor.getValue();
            assertNotNull(res, "Response should not be null");
            assertEquals(1L, res.getBooking().getId());
            assertEquals(100L, res.getBooking().getHotelId());
            assertEquals(Arrays.asList(200L, 201L), res.getBooking().getGuestIdsList());
            assertEquals(Arrays.asList(300L, 301L), res.getBooking().getRoomIdsList());
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
    void testCancelBooking_NotFound() throws BookingNotFoundException, RoomNotFoundException {
        // Arrange
        CancelBookingRequest request = CancelBookingRequest.newBuilder()
                .setId(999L)
                .build();

        doThrow(new BookingNotFoundException("Booking not found"))
                .when(mockBookingService).cancelBooking(999L);

        StreamObserver<Empty> responseObserver = mock(StreamObserver.class);

        // Act
        bookingServiceGrpcImpl.cancelBooking(request, responseObserver);

        // Assert
        verify(mockBookingService).cancelBooking(999L);
        verify(responseObserver).onError(argThat(argument ->
                argument instanceof StatusRuntimeException
                        && ((StatusRuntimeException) argument).getStatus().getCode() == Status.Code.NOT_FOUND
                        && ((StatusRuntimeException) argument).getStatus().getDescription().contains("Booking not found")
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
    void testGetBookingById_NotFound() throws BookingNotFoundException {
        // Arrange
        GetBookingByIdRequest request = GetBookingByIdRequest.newBuilder()
                .setId(999L)
                .build();

        when(mockBookingService.getBookingById(999L))
                .thenThrow(new BookingNotFoundException("Booking not found"));

        StreamObserver<BookingResponse> responseObserver = mock(StreamObserver.class);

        // Act
        bookingServiceGrpcImpl.getBookingById(request, responseObserver);

        // Assert
        verify(mockBookingService).getBookingById(999L);
        verify(responseObserver).onError(argThat(argument ->
                argument instanceof StatusRuntimeException
                        && ((StatusRuntimeException) argument).getStatus().getCode() == Status.Code.NOT_FOUND
                        && ((StatusRuntimeException) argument).getStatus().getDescription().contains("Booking not found")
        ));
    }

    @Test
    void testCreateBooking_InternalException() throws BookingNotFoundException, RoomNotFoundException {
        // Arrange
        CreateBookingRequest request = CreateBookingRequest.newBuilder()
                .setHotelId(100L)
                .addAllRoomTypes(Arrays.asList("SINGLE", "DOUBLE"))
                .addAllGuestIds(Arrays.asList(200L, 201L))
                .setCheckInDate("2025-03-01")
                .setCheckOutDate("2025-03-05")
                .build();

        // Domain service throws error
        when(mockBookingService.makeBooking(
                eq(100L),
                eq(LocalDate.of(2025, 3, 1)),
                eq(LocalDate.of(2025, 3, 5)),
                anyList(),
                eq(Arrays.asList(200L, 201L))
        )).thenThrow(new RuntimeException("Internal Server Error"));

        // We'll still stub out the mapper calls
        BookingDTO realDTO = BookingMapper.toDTO(createSampleDomainBooking());
        BookingDTO spyDTO = spy(realDTO);
        hotelmanagementsystem.infrastructure.api.grpc.generated.Booking protoBooking =
                hotelmanagementsystem.infrastructure.api.grpc.generated.Booking.newBuilder()
                        .setId(1L)
                        .setHotelId(100L)
                        .addAllGuestIds(Arrays.asList(200L, 201L))
                        .addAllRoomIds(Arrays.asList(300L, 301L))
                        .setCheckInDate("2025-03-01")
                        .setCheckOutDate("2025-03-05")
                        .build();
        doReturn(protoBooking).when(spyDTO).toProtobuf();

        try (MockedStatic<BookingMapper> mapperStub = mockStatic(BookingMapper.class)) {
            mapperStub.when(() -> BookingMapper.toDTO(any(Booking.class)))
                    .thenReturn(spyDTO);

            StreamObserver<BookingResponse> responseObserver = mock(StreamObserver.class);

            // Act
            bookingServiceGrpcImpl.createBooking(request, responseObserver);

            // Assert
            verify(mockBookingService).makeBooking(
                    eq(100L),
                    eq(LocalDate.parse("2025-03-01")),
                    eq(LocalDate.parse("2025-03-05")),
                    anyList(),   // room types
                    eq(Arrays.asList(200L, 201L))
            );
            verify(responseObserver).onError(argThat(argument ->
                    argument instanceof StatusRuntimeException
                            && ((StatusRuntimeException) argument).getStatus().getCode() == Status.Code.INTERNAL
                            && ((StatusRuntimeException) argument).getStatus().getDescription().equals("Internal Server Error")
            ));
        }
    }
}


 */