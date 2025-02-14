package DTOTests;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import hotelmanagementsystem.infrastructure.api.dto.BookingDTO;
import hotelmanagementsystem.infrastructure.api.dto.RoomDTO;
import hotelmanagementsystem.infrastructure.api.dto.RoomIdentifierDTO;
import hotelmanagementsystem.infrastructure.api.grpc.generated.Room;
import org.junit.jupiter.api.Test;

public class RoomDTOTest {

    @Test
    public void testGettersAndSetters() {
        RoomIdentifierDTO identifierDTO = new RoomIdentifierDTO("BuildingA", 1, "101A");
        // Erstelle zwei BookingDTOs (ohne Pflichtfelder zu setzen – hier testen wir nur Getter/Setter)
        List<BookingDTO> bookings = Arrays.asList(new BookingDTO(), new BookingDTO());
        RoomDTO dto = new RoomDTO(10L, 150.0, identifierDTO, 1L, bookings, "SingleRoom");

        assertEquals(10L, dto.getId());
        assertEquals(150.0, dto.getPricePerNight(), 0.001);
        assertEquals(identifierDTO, dto.getRoomIdentifierDTO());
        assertEquals(1L, dto.getHotelId());
        assertEquals(bookings, dto.getBookingDTOs());
        assertEquals("SingleRoom", dto.getType());
    }

    @Test
    public void testToProtobuf() {
        // Arrange
        RoomIdentifierDTO identifierDTO = new RoomIdentifierDTO("BuildingB", 2, "202B");

        // Erstelle zwei BookingDTOs mit gültigen (nicht-null) Werten für die Pflichtfelder:
        BookingDTO bookingDTO1 = new BookingDTO();
        bookingDTO1.setId(100L);
        bookingDTO1.setGuestIds(Collections.emptyList());
        bookingDTO1.setRoomIds(Collections.emptyList());
        bookingDTO1.setCheckInDate(LocalDate.now());
        bookingDTO1.setCheckOutDate(LocalDate.now().plusDays(1));
        bookingDTO1.setStatus(true);
        bookingDTO1.setTotalPrice(0.0);

        BookingDTO bookingDTO2 = new BookingDTO();
        bookingDTO2.setId(101L);
        bookingDTO2.setGuestIds(Collections.emptyList());
        bookingDTO2.setRoomIds(Collections.emptyList());
        bookingDTO2.setCheckInDate(LocalDate.now());
        bookingDTO2.setCheckOutDate(LocalDate.now().plusDays(1));
        bookingDTO2.setStatus(true);
        bookingDTO2.setTotalPrice(0.0);

        List<BookingDTO> bookingDTOs = Arrays.asList(bookingDTO1, bookingDTO2);
        RoomDTO dto = new RoomDTO(20L, 200.0, identifierDTO, 2L, bookingDTOs, "DoubleRoom");

        // Act
        Room proto = dto.toProtobuf();

        // Assert
        assertEquals(20L, proto.getId());
        assertEquals(200.0, proto.getPricePerNight(), 0.001);
        // Überprüfe, ob RoomIdentifier korrekt übertragen wird
        assertEquals(identifierDTO.toProtobuf(), proto.getRoomIdentifier());
        assertEquals(2L, proto.getHotelId());
        // Da in unseren BookingDTOs keine Guest- oder Room-IDs gesetzt sind,
        // prüfen wir hier lediglich, dass die Liste nicht null ist.
        assertNotNull(proto.getBookingsList());
        assertEquals("DoubleRoom", proto.getType());
    }
}
