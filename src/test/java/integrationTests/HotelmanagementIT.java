package integrationTests;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mockStatic;

import hotelmanagementsystem.infrastructure.api.grpc.generated.*;
import io.grpc.StatusRuntimeException;
import io.quarkus.grpc.GrpcClient;
import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * An example integration test that covers basic flows across:
 *   - GuestService
 *   - HotelService
 *   - RoomService
 *   - BookingService
 */
@QuarkusTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class HotelmanagementIT {

    // Grpc clients are injected by Quarkus
    @GrpcClient
    GuestServiceGrpc.GuestServiceBlockingStub guestStub;

    @GrpcClient
    HotelServiceGrpc.HotelServiceBlockingStub hotelStub;

    @GrpcClient
    RoomServiceGrpc.RoomServiceBlockingStub roomStub;

    @GrpcClient
    BookingServiceGrpc.BookingServiceBlockingStub bookingStub;

    // Keep track of created entity IDs for use in subsequent tests
    private static long guestId;      // Gast, der eine Buchung bekommt
    private static long guestIdNoBooking;
    private static long hotelId;
    private static long roomId;
    private static long bookingId;

    // -------------------------
    //  GUEST SERVICE TESTS
    // -------------------------

    @Test
    @Order(1)
    public void testCreateGuest() {
        CreateGuestRequest request = CreateGuestRequest.newBuilder()
                .setFirstName("John")
                .setLastName("Doe")
                .setBirthday("1990-05-15")
                .setEmail("john.doe@example.com")
                .setPhoneNumber("+49 171 1234567")
                .build();

        GuestResponse response = guestStub.createGuest(request);
        assertNotNull(response);
        assertTrue(response.hasGuest());
        assertNotEquals(0, response.getGuest().getId());

        guestId = response.getGuest().getId();
        System.out.println("Created Guest ID: " + guestId);

        assertEquals("John", response.getGuest().getFirstName());
    }
    @Test
    @Order(2)
    public void testCreateGuestWithoutBooking() {
        CreateGuestRequest request = CreateGuestRequest.newBuilder()
                .setFirstName("Anne")
                .setLastName("Tester")
                .setBirthday("1995-08-25")
                .setEmail("anne.tester@example.com")
                .setPhoneNumber("+49 171 0000000")
                .build();

        GuestResponse response = guestStub.createGuest(request);
        assertNotNull(response);
        assertTrue(response.hasGuest());
        assertNotEquals(0, response.getGuest().getId());

        guestIdNoBooking = response.getGuest().getId();
        System.out.println("Created Guest (no booking) ID: " + guestIdNoBooking);

        assertEquals("Anne", response.getGuest().getFirstName());
    }

    @Test
    @Order(3)
    public void testGetGuestById() {
        GetGuestByIdRequest request = GetGuestByIdRequest.newBuilder()
                .setId(guestId)
                .build();

        GuestResponse response = guestStub.getGuestById(request);
        assertNotNull(response);
        assertTrue(response.hasGuest());
        assertEquals(guestId, response.getGuest().getId());
        assertEquals("John", response.getGuest().getFirstName());
    }

    @Test
    @Order(4)
    public void testUpdateGuestEmail() {
        UpdateGuestEmailRequest request = UpdateGuestEmailRequest.newBuilder()
                .setGuestId(guestId)
                .setNewEmail("new.johndoe@example.com")
                .build();

        GuestResponse response = guestStub.updateGuestEmail(request);
        assertNotNull(response);
        assertEquals("new.johndoe@example.com", response.getGuest().getEmail());
    }

    @Test
    @Order(5)
    public void testUpdateGuestPhone() {
        UpdateGuestPhoneRequest request = UpdateGuestPhoneRequest.newBuilder()
                .setGuestId(guestId)
                .setNewPhone("+49 171 9876543")
                .build();

        GuestResponse response = guestStub.updateGuestPhone(request);
        assertNotNull(response);
        assertEquals("+49 171 9876543", response.getGuest().getPhoneNumber());
    }

    @Test
    @Order(6)
    public void testUpdateGuestLastName() {
        UpdateGuestLastNameRequest request = UpdateGuestLastNameRequest.newBuilder()
                .setGuestId(guestId)
                .setNewLastName("Dowson")
                .build();

        GuestResponse response = guestStub.updateGuestLastName(request);
        assertNotNull(response);
        assertEquals("Dowson", response.getGuest().getLastName());
    }

    // -------------------------
    //  HOTEL SERVICE TESTS
    // -------------------------

    @Test
    @Order(7)
    public void testCreateHotel() {
        HotelLocation location = HotelLocation.newBuilder()
                // If your domain expects an "id", you can set zero or leave it
                .setId(0)
                .setAddress("Test Street 123")
                .setCity("TestCity")
                .setCountry("TestCountry")
                .build();

        CreateHotelRequest request = CreateHotelRequest.newBuilder()
                .setName("Test Hotel")
                .setDescription("A newly created test hotel.")
                .setLocation(location)
                .build();

        HotelResponse response = hotelStub.createHotel(request);
        assertNotNull(response);
        assertTrue(response.hasHotel());
        assertNotEquals(0, response.getHotel().getId());

        hotelId = response.getHotel().getId();
        System.out.println("Created Hotel ID: " + hotelId);
        assertEquals("Test Hotel", response.getHotel().getName());
        assertEquals("TestCountry", response.getHotel().getLocation().getCountry());
    }

    @Test
    @Order(8)
    public void testGetHotelById() {
        GetHotelByIdRequest request = GetHotelByIdRequest.newBuilder()
                .setId(hotelId)
                .build();

        HotelResponse response = hotelStub.getHotelById(request);
        assertNotNull(response);
        assertTrue(response.hasHotel());
        assertEquals(hotelId, response.getHotel().getId());
        assertEquals("Test Hotel", response.getHotel().getName());
    }

    @Test
    @Order(9)
    public void testListHotels() {
        ListHotelsRequest request = ListHotelsRequest.newBuilder()
                // Filter by city if you want, e.g. "TestCity"
                .setFilterCity("TestCity")
                .setMinRating(0.0)
                .setPageNumber(1)
                .setPageSize(10)
                .build();

        ListHotelsResponse response = hotelStub.listHotels(request);
        assertNotNull(response);
        assertTrue(response.getHotelsCount() > 0);

        boolean foundOurHotel = response.getHotelsList().stream()
                .anyMatch(h -> h.getId() == hotelId);
        assertTrue(foundOurHotel);
    }

    @Test
    @Order(10)
    public void testUpdateHotel() {
        UpdateHotelRequest request = UpdateHotelRequest.newBuilder()
                .setId(hotelId)
                .setName("Test Hotel Updated")
                .setDescription("An updated test hotel description.")
                .build();

        HotelResponse response = hotelStub.updateHotel(request);
        assertNotNull(response);
        assertEquals(hotelId, response.getHotel().getId());
        assertEquals("Test Hotel Updated", response.getHotel().getName());
        assertEquals("An updated test hotel description.", response.getHotel().getDescription());
    }

    @Test
    @Order(11)
    public void testRateHotel() {
        // Rate the hotel as the newly created guest
        RateHotelRequest request = RateHotelRequest.newBuilder()
                .setHotelId(hotelId)
                .setGuestId(guestId)
                .setRating(5)
                .setComment("Excellent stay!")
                .build();
        // The service returns an Empty on success
        hotelStub.rateHotel(request);

        // We won't assert a lot here except no error was thrown
        // (You could re-fetch the hotel to ensure rating was recorded)
        HotelResponse afterRating = hotelStub.getHotelById(
                GetHotelByIdRequest.newBuilder().setId(hotelId).build()
        );
        assertNotNull(afterRating);
        assertTrue(afterRating.hasHotel());
        // Possibly check averageRating or rating list if your domain sets that
    }

    // -------------------------
    //  ROOM SERVICE TESTS
    // -------------------------

    @Test
    @Order(12)
    public void testCreateRoom() {
        RoomIdentifier roomIdentifier = RoomIdentifier.newBuilder()
                .setBuilding("A")
                .setFloor(2)
                .setRoomNumber("202")
                .build();

        CreateRoomRequest request = CreateRoomRequest.newBuilder()
                .setHotelId(hotelId)
                .setType("SINGLE")
                .setPricePerNight(79.99)
                .setRoomIdentifier(roomIdentifier)
                .build();

        RoomResponse response = roomStub.createRoom(request);
        assertNotNull(response);
        assertTrue(response.hasRoom());
        roomId = response.getRoom().getId();
        System.out.println("Created Room ID: " + roomId);

        assertEquals("SingleRoom", response.getRoom().getType());
        assertEquals(79.99, response.getRoom().getPricePerNight());
        assertEquals(hotelId, response.getRoom().getHotelId());
        assertEquals("202", response.getRoom().getRoomIdentifier().getRoomNumber());
    }

    @Test
    @Order(13)
    public void testGetRoomById() {
        GetRoomByIdRequest request = GetRoomByIdRequest.newBuilder()
                .setId(roomId)
                .build();

        RoomResponse response = roomStub.getRoomById(request);
        assertNotNull(response);
        assertTrue(response.hasRoom());
        assertEquals(roomId, response.getRoom().getId());
    }

    @Test
    @Order(14)
    public void testUpdateRoomPrice() {
        UpdateRoomPriceRequest request = UpdateRoomPriceRequest.newBuilder()
                .setRoomId(roomId)
                .setNewPricePerNight(89.99)
                .build();

        RoomResponse response = roomStub.updateRoomPrice(request);
        assertNotNull(response);
        assertTrue(response.hasRoom());
        assertEquals(89.99, response.getRoom().getPricePerNight());
    }

    @Test
    @Order(15)
    public void testFindAvailableRooms() {
        // Provide some checkInDate and checkOutDate
        FindAvailableRoomsRequest request = FindAvailableRoomsRequest.newBuilder()
                .setHotelId(hotelId)
                .setRoomType("SINGLE")          // or "" for all
                .setCheckInDate("2025-03-01")
                .setCheckOutDate("2025-03-05")
                .build();

        ListRoomsResponse response = hotelStub.findAvailableRooms(request);
        assertNotNull(response);
        assertTrue(response.getRoomsCount() > 0);

        boolean foundOurRoom = response.getRoomsList().stream()
                .anyMatch(r -> r.getId() == roomId);
        assertTrue(foundOurRoom);
    }

    // -------------------------
    //  BOOKING SERVICE TESTS
    // -------------------------

    @Test
    @Order(16)
    public void testCreateBooking() {
        // For demonstration, let's pass in 1 guest and 1 room type
        List<String> roomTypes = new ArrayList<>();
        roomTypes.add("SINGLE");

        List<Long> guestIds = new ArrayList<>();
        guestIds.add(guestId);

        CreateBookingRequest request = CreateBookingRequest.newBuilder()
                .setHotelId(hotelId)
                .addAllRoomTypes(roomTypes)
                .addAllGuestIds(guestIds)
                .setCheckInDate(String.valueOf(LocalDate.now().plusDays(5)))
                .setCheckOutDate(String.valueOf(LocalDate.now().plusDays(10)))
                .build();

        BookingResponse response = bookingStub.createBooking(request);
        assertNotNull(response);
        assertTrue(response.hasBooking());
        bookingId = response.getBooking().getId();
        System.out.println("Created Booking ID: " + bookingId);

        assertEquals(hotelId, response.getBooking().getHotelId());
        assertEquals(String.valueOf(LocalDate.now().plusDays(5)), response.getBooking().getCheckInDate());
        assertEquals(String.valueOf(LocalDate.now().plusDays(10)), response.getBooking().getCheckOutDate());
        assertTrue(response.getBooking().getGuestIdsList().contains(guestId));
    }

    @Test
    @Order(17)
    public void testGetBookingById() {
        GetBookingByIdRequest request = GetBookingByIdRequest.newBuilder()
                .setId(bookingId)
                .build();

        BookingResponse response = bookingStub.getBookingById(request);
        assertNotNull(response);
        assertTrue(response.hasBooking());
        assertEquals(bookingId, response.getBooking().getId());
    }

    @Test
    @Order(18)
    public void testCheckInGuest() {
        CheckInGuestRequest request = CheckInGuestRequest.newBuilder()
                .setBookingId(bookingId)
                .build();

        assertThrows(StatusRuntimeException.class, () -> {
            bookingStub.checkInGuest(request);
        });

    }

    @Test
    @Order(19)
    public void testCheckOutGuest() {
        CheckOutGuestRequest request = CheckOutGuestRequest.newBuilder()
                .setBookingId(bookingId)
                .build();

        assertThrows(StatusRuntimeException.class, () -> {
            bookingStub.checkOutGuest(request);
        });
    }

    @Test
    @Order(20)
    public void testGetBookingsByGuestId() {
        GetBookingsByGuestIdRequest request = GetBookingsByGuestIdRequest.newBuilder()
                .setGuestId(guestId)
                .build();

        ListBookingsResponse response = guestStub.getBookingsByGuestId(request);
        assertNotNull(response);
        assertTrue(response.getBookingsCount() > 0);

        boolean foundBooking = response.getBookingsList().stream()
                .anyMatch(b -> b.getId() == bookingId);
        assertTrue(foundBooking);
    }

    @Test
    @Order(21)
    public void testCancelBooking() {
        CancelBookingRequest request = CancelBookingRequest.newBuilder()
                .setId(bookingId)
                .build();

        bookingStub.cancelBooking(request);


        GetBookingByIdRequest request2 = GetBookingByIdRequest.newBuilder()
                .setId(bookingId)
                .build();

        BookingResponse response = bookingStub.getBookingById(request2);
        assertNotNull(response);
        assertEquals("CANCELED", response.getBooking().getStatus().toString());
        assertTrue(response.hasBooking());
        assertEquals(bookingId, response.getBooking().getId());
    }


    // -------------------------
    //  CLEANUP TESTS
    // -------------------------

    @Test
    @Order(22)
    public void testRemoveRoom() {
        RemoveRoomRequest request = RemoveRoomRequest.newBuilder()
                .setRoomId(roomId)
                .build();

        roomStub.removeRoom(request);
        // Expect no exception

        // Optional: confirm it was removed
        GetRoomByIdRequest getReq = GetRoomByIdRequest.newBuilder()
                .setId(roomId)
                .build();
        assertThrows(StatusRuntimeException.class, () -> {
            roomStub.getRoomById(getReq);
        });
    }

    @Test
    @Order(23)
    public void testDeleteGuest_ShouldThrowExceptionForGuestWithActiveBooking() {
        DeleteGuestRequest request = DeleteGuestRequest.newBuilder()
                .setId(guestId)
                .build();

        // Wir erwarten, dass das Backend hier eine Exception wirft,
        // weil ein Gast mit aktiver Buchung nicht gelöscht werden kann.
        assertThrows(StatusRuntimeException.class, () -> {
            guestStub.deleteGuest(request);
        });
    }
    @Test
    @Order(24)
    public void testDeleteSecondGuest_ShouldSucceed() {
        // Dieser zweite Gast hat keine Buchung -> sollte klappen.
        DeleteGuestRequest request = DeleteGuestRequest.newBuilder()
                .setId(guestIdNoBooking)
                .build();

        // Erwartet, dass KEINE Exception fliegt
        assertDoesNotThrow(() -> guestStub.deleteGuest(request));

        // ggf. nochmal checken, ob er weg ist:
        GetGuestByIdRequest getReq = GetGuestByIdRequest.newBuilder()
                .setId(guestIdNoBooking)
                .build();
        // Hier sollte eine NOT_FOUND-Exception kommen, da der Gast weg ist
        assertThrows(StatusRuntimeException.class, () -> {
            guestStub.getGuestById(getReq);
        });
    }

    @Test
    @Order(25)
    public void testDeleteHotel() {
        DeleteHotelRequest request = DeleteHotelRequest.newBuilder()
                .setId(hotelId)
                .build();

        hotelStub.deleteHotel(request);
        // Confirm it was deleted
        GetHotelByIdRequest getReq = GetHotelByIdRequest.newBuilder()
                .setId(hotelId)
                .build();
        assertThrows(StatusRuntimeException.class, () -> {
            hotelStub.getHotelById(getReq);
        });
    }

}
