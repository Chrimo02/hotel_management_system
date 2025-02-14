package DTOTests;
import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import hotelmanagementsystem.infrastructure.api.dto.BookingDTO;
import hotelmanagementsystem.infrastructure.api.grpc.generated.Booking;
import hotelmanagementsystem.infrastructure.api.grpc.generated.BookingStatus;
import org.junit.jupiter.api.Test;

public class BookingDTOTest {

    @Test
    public void testToProtobuf_AllFieldsSet() {
        BookingDTO dto = new BookingDTO();
        dto.setId(100L);
        dto.setHotelId(200L);
        List<Long> guestIds = Arrays.asList(1L, 2L, 3L);
        List<Long> roomIds = Arrays.asList(10L, 20L);
        dto.setGuestIds(guestIds);
        dto.setRoomIds(roomIds);
        LocalDate checkIn = LocalDate.of(2024, 12, 1);
        LocalDate checkOut = LocalDate.of(2024, 12, 5);
        dto.setCheckInDate(checkIn);
        dto.setCheckOutDate(checkOut);
        dto.setStatus(true);
        dto.setTotalPrice(1000.0);
        LocalDateTime checkInTime = LocalDateTime.of(2024, 12, 1, 14, 0);
        LocalDateTime checkOutTime = LocalDateTime.of(2024, 12, 5, 11, 0);
        dto.setCheckInTime(checkInTime);
        dto.setCheckOutTime(checkOutTime);

        Booking proto = dto.toProtobuf();
        assertEquals(100L, proto.getId());
        assertEquals(200L, proto.getHotelId());
        assertEquals(guestIds, proto.getGuestIdsList());
        assertEquals(roomIds, proto.getRoomIdsList());
        assertEquals(checkIn.toString(), proto.getCheckInDate());
        assertEquals(checkOut.toString(), proto.getCheckOutDate());
        assertEquals(BookingStatus.ACTIVE, proto.getStatus());
        assertEquals(1000.0, proto.getTotalPrice(), 0.001);
        assertEquals(checkInTime.toString(), proto.getCheckInTime());
        assertEquals(checkOutTime.toString(), proto.getCheckOutTime());
    }

    @Test
    public void testToProtobuf_NullCheckInOutTimes() {
        BookingDTO dto = new BookingDTO();
        dto.setId(101L);
        dto.setHotelId(201L);
        dto.setGuestIds(Arrays.asList(4L, 5L));
        dto.setRoomIds(Arrays.asList(30L));
        LocalDate checkIn = LocalDate.of(2025, 1, 1);
        LocalDate checkOut = LocalDate.of(2025, 1, 3);
        dto.setCheckInDate(checkIn);
        dto.setCheckOutDate(checkOut);
        dto.setStatus(false);
        dto.setTotalPrice(300.0);
        // Keine CheckIn/Out-Zeiten setzen
        dto.setCheckInTime(null);
        dto.setCheckOutTime(null);

        Booking proto = dto.toProtobuf();
        assertEquals(101L, proto.getId());
        assertEquals(201L, proto.getHotelId());
        assertEquals(Arrays.asList(4L, 5L), proto.getGuestIdsList());
        assertEquals(Arrays.asList(30L), proto.getRoomIdsList());
        assertEquals(checkIn.toString(), proto.getCheckInDate());
        assertEquals(checkOut.toString(), proto.getCheckOutDate());
        assertEquals(BookingStatus.CANCELED, proto.getStatus());
        assertEquals(300.0, proto.getTotalPrice(), 0.001);
        // Da checkInTime/checkOutTime null sind, sollten wir leere Strings erhalten
        assertEquals("", proto.getCheckInTime());
        assertEquals("", proto.getCheckOutTime());
    }
}
