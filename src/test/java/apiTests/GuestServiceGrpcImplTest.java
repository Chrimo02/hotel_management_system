package apiTests;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import hotelmanagementsystem.domain.exceptions.GuestNotFoundException;
import hotelmanagementsystem.domain.models.Booking;
import hotelmanagementsystem.domain.models.Guest;
import hotelmanagementsystem.domain.services.GuestService;
import hotelmanagementsystem.infrastructure.api.grpc.generated.CreateGuestRequest;
import hotelmanagementsystem.infrastructure.api.grpc.generated.DeleteGuestRequest;
import hotelmanagementsystem.infrastructure.api.grpc.generated.GetBookingsByGuestIdRequest;
import hotelmanagementsystem.infrastructure.api.grpc.generated.GetGuestByIdRequest;
import hotelmanagementsystem.infrastructure.api.grpc.generated.GuestResponse;
import hotelmanagementsystem.infrastructure.api.grpc.generated.ListBookingsResponse;
import hotelmanagementsystem.infrastructure.api.grpc.generated.UpdateGuestEmailRequest;
import hotelmanagementsystem.infrastructure.api.grpc.generated.UpdateGuestLastNameRequest;
import hotelmanagementsystem.infrastructure.api.grpc.generated.UpdateGuestPhoneRequest;
import hotelmanagementsystem.infrastructure.api.grpc.generated.Empty;
import hotelmanagementsystem.infrastructure.api.grpc.impl.GuestServiceGrpcImpl;
import io.grpc.Status;
import io.grpc.StatusRuntimeException;
import io.grpc.stub.StreamObserver;
import java.util.Collections;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class GuestServiceGrpcImplTest {

    @Mock
    private GuestService guestService;

    @InjectMocks
    private GuestServiceGrpcImpl grpcImpl;

    @Mock
    private StreamObserver<GuestResponse> guestResponseObserver;

    @Mock
    private StreamObserver<Empty> emptyObserver;

    private Guest dummyGuest;

    @BeforeEach
    void setUp() {
        dummyGuest = new Guest.GuestBuilder()
                .withId(1L)
                .withFirstName("John")
                .withLastName("Doe")
                .withBirthday(1990, 5, 15)
                .withEMail("john.doe@example.com")
                .withPhoneNumber("1234567890")
                .build();
    }

    @Test
    void testCreateGuest_Success() {
        CreateGuestRequest request = CreateGuestRequest.newBuilder()
                .setFirstName("John")
                .setLastName("Doe")
                .setBirthday("1990-05-15")
                .setEmail("john.doe@example.com")
                .setPhoneNumber("1234567890")
                .build();

        when(guestService.createGuest("John", "Doe", 1990, 5, 15, "john.doe@example.com", "1234567890"))
                .thenReturn(dummyGuest);

        grpcImpl.createGuest(request, guestResponseObserver);

        ArgumentCaptor<GuestResponse> responseCaptor = ArgumentCaptor.forClass(GuestResponse.class);
        verify(guestResponseObserver, times(1)).onNext(responseCaptor.capture());
        verify(guestResponseObserver, times(1)).onCompleted();

        GuestResponse response = responseCaptor.getValue();
        assertNotNull(response);
        assertEquals(dummyGuest.getId(), response.getGuest().getId());
    }

    @Test
    void testGetGuestById_Success() throws Exception {
        GetGuestByIdRequest request = GetGuestByIdRequest.newBuilder().setId(1L).build();
        when(guestService.getGuestById(1L)).thenReturn(dummyGuest);

        grpcImpl.getGuestById(request, guestResponseObserver);

        ArgumentCaptor<GuestResponse> captor = ArgumentCaptor.forClass(GuestResponse.class);
        verify(guestResponseObserver, times(1)).onNext(captor.capture());
        verify(guestResponseObserver, times(1)).onCompleted();

        GuestResponse response = captor.getValue();
        assertNotNull(response);
        assertEquals(dummyGuest.getId(), response.getGuest().getId());
    }

    @Test
    void testGetGuestById_NotFound() throws Exception {
        GetGuestByIdRequest request = GetGuestByIdRequest.newBuilder().setId(999L).build();
        when(guestService.getGuestById(999L))
                .thenThrow(new GuestNotFoundException("Guest not found"));

        grpcImpl.getGuestById(request, guestResponseObserver);

        verify(guestResponseObserver, never()).onNext(any());
        verify(guestResponseObserver, never()).onCompleted();
        ArgumentCaptor<StatusRuntimeException> errorCaptor = ArgumentCaptor.forClass(StatusRuntimeException.class);
        verify(guestResponseObserver, times(1)).onError(errorCaptor.capture());

        StatusRuntimeException exception = errorCaptor.getValue();
        assertEquals(Status.NOT_FOUND.getCode(), exception.getStatus().getCode());
        assertTrue(exception.getStatus().getDescription().contains("Guest not found"));
    }

    @Test
    void testUpdateGuestEmail_Success() throws Exception {
        UpdateGuestEmailRequest request = UpdateGuestEmailRequest.newBuilder()
                .setGuestId(1L)
                .setNewEmail("new.email@example.com")
                .build();

        Guest updatedGuest = new Guest.GuestBuilder()
                .withId(1L)
                .withFirstName("John")
                .withLastName("Doe")
                .withBirthday(1990, 5, 15)
                .withEMail("new.email@example.com")
                .withPhoneNumber("1234567890")
                .build();

        when(guestService.updateEMail(1L, "new.email@example.com")).thenReturn(updatedGuest);

        grpcImpl.updateGuestEmail(request, guestResponseObserver);

        ArgumentCaptor<GuestResponse> captor = ArgumentCaptor.forClass(GuestResponse.class);
        verify(guestResponseObserver, times(1)).onNext(captor.capture());
        verify(guestResponseObserver, times(1)).onCompleted();

        GuestResponse response = captor.getValue();
        assertNotNull(response);
        assertEquals("new.email@example.com", response.getGuest().getEmail());
    }

    @Test
    void testUpdateGuestPhone_Success() throws Exception {
        UpdateGuestPhoneRequest request = UpdateGuestPhoneRequest.newBuilder()
                .setGuestId(1L)
                .setNewPhone("0987654321")
                .build();

        Guest updatedGuest = new Guest.GuestBuilder()
                .withId(1L)
                .withFirstName("John")
                .withLastName("Doe")
                .withBirthday(1990, 5, 15)
                .withEMail("john.doe@example.com")
                .withPhoneNumber("0987654321")
                .build();

        when(guestService.updatePhone(1L, "0987654321")).thenReturn(updatedGuest);

        grpcImpl.updateGuestPhone(request, guestResponseObserver);

        ArgumentCaptor<GuestResponse> captor = ArgumentCaptor.forClass(GuestResponse.class);
        verify(guestResponseObserver, times(1)).onNext(captor.capture());
        verify(guestResponseObserver, times(1)).onCompleted();

        GuestResponse response = captor.getValue();
        assertNotNull(response);
        assertEquals("0987654321", response.getGuest().getPhoneNumber());
    }

    @Test
    void testUpdateGuestLastName_Success() throws Exception {
        UpdateGuestLastNameRequest request = UpdateGuestLastNameRequest.newBuilder()
                .setGuestId(1L)
                .setNewLastName("Smith")
                .build();

        Guest updatedGuest = new Guest.GuestBuilder()
                .withId(1L)
                .withFirstName("John")
                .withLastName("Smith")
                .withBirthday(1990, 5, 15)
                .withEMail("john.doe@example.com")
                .withPhoneNumber("1234567890")
                .build();

        when(guestService.updateLastName(1L, "Smith")).thenReturn(updatedGuest);

        grpcImpl.updateGuestLastName(request, guestResponseObserver);

        ArgumentCaptor<GuestResponse> captor = ArgumentCaptor.forClass(GuestResponse.class);
        verify(guestResponseObserver, times(1)).onNext(captor.capture());
        verify(guestResponseObserver, times(1)).onCompleted();

        GuestResponse response = captor.getValue();
        assertNotNull(response);
        assertEquals("Smith", response.getGuest().getLastName());
    }

    @Test
    void testDeleteGuest_Success() throws Exception {
        DeleteGuestRequest request = DeleteGuestRequest.newBuilder().setId(1L).build();
        doNothing().when(guestService).deleteGuest(1L);

        grpcImpl.deleteGuest(request, emptyObserver);

        verify(emptyObserver, times(1)).onNext(any(Empty.class));
        verify(emptyObserver, times(1)).onCompleted();
    }

    @Test
    void testGetBookingsByGuestId_Success() {
        GetBookingsByGuestIdRequest request = GetBookingsByGuestIdRequest.newBuilder().setGuestId(1L).build();
        when(guestService.getAllBookingsFromGuest(1L)).thenReturn(Collections.emptyList());

        grpcImpl.getBookingsByGuestId(request, new StreamObserver<>() {
            @Override
            public void onNext(ListBookingsResponse value) {
                assertNotNull(value);
                assertEquals(0, value.getBookingsCount());
            }
            @Override
            public void onError(Throwable t) {
                fail("Should not have thrown error: " + t.getMessage());
            }
            @Override
            public void onCompleted() {
            }
        });
    }
}
