package DAOTests;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import hotelmanagementsystem.infrastructure.persistence.dao.implementation.BookingDAOImpl;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import hotelmanagementsystem.infrastructure.persistence.entities.BookingEntity;

@ExtendWith(MockitoExtension.class)
public class BookingDAOTest {

    @InjectMocks
    private BookingDAOImpl bookingDAOImpl;

    @Mock
    private EntityManager em;

    @Mock
    private TypedQuery<BookingEntity> typedQuery;

    private BookingEntity dummyBookingEntity;

    private LocalDate dummyDate;

    @BeforeEach
    public void setUp() {
        dummyDate = LocalDate.of(2025, 1, 1);
        dummyBookingEntity = new BookingEntity(
                1L,
                null, // Hotel kann null sein für diesen Test
                dummyDate,
                dummyDate.plusDays(3),
                Arrays.asList(), // leere Listen für Rooms und Guests
                Arrays.asList(),
                true,
                null,
                null
        );
    }

    @Test
    public void testCreate_Success() {
        // Kein spezielles Verhalten für persist, da persist() void ist.
        // Wir gehen davon aus, dass create() das übergebene Objekt zurückliefert.
        BookingEntity result = bookingDAOImpl.create(dummyBookingEntity);
        assertEquals(dummyBookingEntity, result);
        verify(em).persist(dummyBookingEntity);
    }

    @Test
    public void testRead_Success() {
        when(em.find(BookingEntity.class, 1L)).thenReturn(dummyBookingEntity);
        BookingEntity result = bookingDAOImpl.read(1L);
        assertEquals(dummyBookingEntity, result);
        verify(em).find(BookingEntity.class, 1L);
    }

    @Test
    public void testUpdate_Success() {
        when(em.merge(dummyBookingEntity)).thenReturn(dummyBookingEntity);
        assertDoesNotThrow(() -> bookingDAOImpl.update(dummyBookingEntity));
        verify(em).merge(dummyBookingEntity);
    }

    @Test
    public void testFindBookingsByCheckInDate_Success() {
        LocalDate targetDate = dummyDate;
        when(em.createQuery(anyString(), eq(BookingEntity.class))).thenReturn(typedQuery);
        when(typedQuery.setParameter("checkInDate", targetDate)).thenReturn(typedQuery);
        when(typedQuery.getResultList()).thenReturn(Arrays.asList(dummyBookingEntity));

        List<BookingEntity> result = bookingDAOImpl.findBookingsByCheckInDate(targetDate);
        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(dummyBookingEntity, result.get(0));
        verify(typedQuery).setParameter("checkInDate", targetDate);
        verify(typedQuery).getResultList();
    }
}
