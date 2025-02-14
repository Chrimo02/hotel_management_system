package apiTests;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import hotelmanagementsystem.domain.exceptions.BookingNotFoundException;
import hotelmanagementsystem.domain.models.Booking;
import hotelmanagementsystem.domain.models.Guest;
import hotelmanagementsystem.domain.models.Hotel;
import hotelmanagementsystem.domain.models.Room;
import hotelmanagementsystem.domain.services.BookingService;
import hotelmanagementsystem.infrastructure.api.grpc.generated.BookingResponse;
import hotelmanagementsystem.infrastructure.api.grpc.generated.CancelBookingRequest;
import hotelmanagementsystem.infrastructure.api.grpc.generated.CheckInGuestRequest;
import hotelmanagementsystem.infrastructure.api.grpc.generated.CheckOutGuestRequest;
import hotelmanagementsystem.infrastructure.api.grpc.generated.CreateBookingRequest;
import hotelmanagementsystem.infrastructure.api.grpc.generated.Empty;
import hotelmanagementsystem.infrastructure.api.grpc.generated.GetBookingByIdRequest;
import hotelmanagementsystem.infrastructure.api.grpc.impl.BookingServiceGrpcImpl;
import io.grpc.Status;
import io.grpc.StatusRuntimeException;
import io.grpc.stub.StreamObserver;
import java.time.LocalDate;
import java.util.Arrays;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class BookingServiceGrpcImplTest {

    @Mock
    private BookingService bookingService;

    @InjectMocks
    private BookingServiceGrpcImpl grpcImpl;

    @Mock
    private StreamObserver<BookingResponse> responseObserver;

    @Mock
    private StreamObserver<Empty> emptyResponseObserver;

    private Booking dummyBooking;

    @BeforeEach
    void setUp() {
        Hotel dummyHotel = new Hotel.HotelBuilder()
                .withId(1L)
                .withName("TestHotel")
                .withDescription("Desc")
                .withLocation(null)
                .withRoomsList(Arrays.asList(mock(Room.class)))
                .withBookingList(Arrays.asList())
                .withRatingMap(Arrays.asList())
                .withAverageRating(0.0)
                .build();

        dummyBooking = new Booking(
                100L,
                dummyHotel,
                LocalDate.now().plusDays(5),
                LocalDate.now().plusDays(7),
                Arrays.asList(mock(Room.class)),
                Arrays.asList(mock(Guest.class)),
                true,
                null,
                null
        );
    }

    @Test
    void testCreateBooking_Success() throws Exception {
        CreateBookingRequest request = CreateBookingRequest.newBuilder()
                .setHotelId(1L)
                .addAllRoomTypes(Arrays.asList("SINGLE"))
                .addAllGuestIds(Arrays.asList(10L, 20L))
                .setCheckInDate(LocalDate.now().plusDays(5).toString())
                .setCheckOutDate(LocalDate.now().plusDays(7).toString())
                .build();

        when(bookingService.makeBooking(eq(1L),
                any(LocalDate.class),
                any(LocalDate.class),
                anyList(),
                anyList())).thenReturn(dummyBooking);

        grpcImpl.createBooking(request, responseObserver);

        ArgumentCaptor<BookingResponse> captor = ArgumentCaptor.forClass(BookingResponse.class);
        verify(responseObserver, times(1)).onNext(captor.capture());
        verify(responseObserver, times(1)).onCompleted();

        BookingResponse response = captor.getValue();
        assertNotNull(response);
        assertEquals(100L, response.getBooking().getId());
    }

    @Test
    void testGetBookingById_Success() throws Exception {
        GetBookingByIdRequest request = GetBookingByIdRequest.newBuilder().setId(100L).build();
        when(bookingService.getBookingById(100L)).thenReturn(dummyBooking);

        grpcImpl.getBookingById(request, responseObserver);

        ArgumentCaptor<BookingResponse> captor = ArgumentCaptor.forClass(BookingResponse.class);
        verify(responseObserver, times(1)).onNext(captor.capture());
        verify(responseObserver, times(1)).onCompleted();

        BookingResponse response = captor.getValue();
        assertNotNull(response);
        assertEquals(100L, response.getBooking().getId());
    }

    @Test
    void testGetBookingById_NotFound() throws Exception {
        GetBookingByIdRequest request = GetBookingByIdRequest.newBuilder().setId(999L).build();
        when(bookingService.getBookingById(999L))
                .thenThrow(new BookingNotFoundException("Booking not found"));

        grpcImpl.getBookingById(request, responseObserver);

        verify(responseObserver, never()).onNext(any());
        verify(responseObserver, never()).onCompleted();
        ArgumentCaptor<StatusRuntimeException> errorCaptor = ArgumentCaptor.forClass(StatusRuntimeException.class);
        verify(responseObserver, times(1)).onError(errorCaptor.capture());

        StatusRuntimeException exception = errorCaptor.getValue();
        assertEquals(Status.NOT_FOUND.getCode(), exception.getStatus().getCode());
        assertTrue(exception.getStatus().getDescription().contains("Booking not found"));
    }

    @Test
    void testCancelBooking_Success() throws Exception {
        CancelBookingRequest request = CancelBookingRequest.newBuilder().setId(100L).build();
        doNothing().when(bookingService).cancelBooking(100L);

        grpcImpl.cancelBooking(request, emptyResponseObserver);

        verify(emptyResponseObserver, times(1)).onNext(any(Empty.class));
        verify(emptyResponseObserver, times(1)).onCompleted();
    }

    @Test
    void testCancelBooking_NotFound() throws Exception {
        CancelBookingRequest request = CancelBookingRequest.newBuilder().setId(999L).build();
        doThrow(new BookingNotFoundException("Booking not found")).when(bookingService).cancelBooking(999L);

        grpcImpl.cancelBooking(request, emptyResponseObserver);

        verify(emptyResponseObserver, never()).onNext(any());
        verify(emptyResponseObserver, never()).onCompleted();
        ArgumentCaptor<StatusRuntimeException> errorCaptor = ArgumentCaptor.forClass(StatusRuntimeException.class);
        verify(emptyResponseObserver, times(1)).onError(errorCaptor.capture());

        StatusRuntimeException exception = errorCaptor.getValue();
        assertEquals(Status.NOT_FOUND.getCode(), exception.getStatus().getCode());
    }

    @Test
    void testCheckInGuest_Success() throws Exception {
        CheckInGuestRequest request = CheckInGuestRequest.newBuilder().setBookingId(100L).build();
        doNothing().when(bookingService).guestCheckIn(100L);

        grpcImpl.checkInGuest(request, emptyResponseObserver);

        verify(emptyResponseObserver, times(1)).onNext(any(Empty.class));
        verify(emptyResponseObserver, times(1)).onCompleted();
    }

    @Test
    void testCheckInGuest_NotFound() throws Exception {
        CheckInGuestRequest request = CheckInGuestRequest.newBuilder().setBookingId(999L).build();
        doThrow(new BookingNotFoundException("Booking not found")).when(bookingService).guestCheckIn(999L);

        grpcImpl.checkInGuest(request, emptyResponseObserver);

        verify(emptyResponseObserver, never()).onNext(any());
        verify(emptyResponseObserver, never()).onCompleted();
        ArgumentCaptor<StatusRuntimeException> errorCaptor = ArgumentCaptor.forClass(StatusRuntimeException.class);
        verify(emptyResponseObserver, times(1)).onError(errorCaptor.capture());
        StatusRuntimeException exception = errorCaptor.getValue();
        assertEquals(Status.NOT_FOUND.getCode(), exception.getStatus().getCode());
    }

    @Test
    void testCheckOutGuest_Success() throws Exception {
        CheckOutGuestRequest request = CheckOutGuestRequest.newBuilder().setBookingId(100L).build();
        doNothing().when(bookingService).guestCheckOut(100L);

        grpcImpl.checkOutGuest(request, emptyResponseObserver);

        verify(emptyResponseObserver, times(1)).onNext(any(Empty.class));
        verify(emptyResponseObserver, times(1)).onCompleted();
    }

    @Test
    void testCheckOutGuest_NotFound() throws Exception {
        CheckOutGuestRequest request = CheckOutGuestRequest.newBuilder().setBookingId(999L).build();
        doThrow(new BookingNotFoundException("Booking not found")).when(bookingService).guestCheckOut(999L);

        grpcImpl.checkOutGuest(request, emptyResponseObserver);

        verify(emptyResponseObserver, never()).onNext(any());
        verify(emptyResponseObserver, never()).onCompleted();
        ArgumentCaptor<StatusRuntimeException> errorCaptor = ArgumentCaptor.forClass(StatusRuntimeException.class);
        verify(emptyResponseObserver, times(1)).onError(errorCaptor.capture());
        StatusRuntimeException exception = errorCaptor.getValue();
        assertEquals(Status.NOT_FOUND.getCode(), exception.getStatus().getCode());
    }
}
