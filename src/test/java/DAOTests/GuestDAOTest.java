package DAOTests;

import hotelmanagementsystem.infrastructure.persistence.dao.implementation.DataAccessException;
import hotelmanagementsystem.infrastructure.persistence.dao.implementation.GuestDAOImpl;
import hotelmanagementsystem.infrastructure.persistence.entities.BookingEntity;
import hotelmanagementsystem.infrastructure.persistence.entities.GuestEntity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.TypedQuery;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Spy;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.lenient;

/**
 * Vollständige Testklasse für GuestDAOImpl mit Spy-Ansatz und Lenient-Stubbing,
 * sodass u. a. das Rollback in Ausnahmefällen funktioniert.
 */
@ExtendWith(MockitoExtension.class)
class GuestDAOTest {

    /**
     * Wir verwenden ein Spy, damit wir den echten Code von GuestDAOImpl
     * ausführen, jedoch .entityManager() überschreiben können.
     */
    @Spy
    private GuestDAOImpl guestDAO;

    @Mock
    private EntityManager mockEntityManager;

    @Mock
    private EntityTransaction mockTransaction;

    @Mock
    private TypedQuery<BookingEntity> mockTypedQuery;

    @BeforeEach
    void setUp() {
        // Wir benutzen lenient() für die Stubbings, damit Mockito nicht meckert,
        // falls einzelne Tests die Stub-Aufrufe nicht nutzen.
        lenient().doReturn(mockEntityManager).when(guestDAO).entityManager();
        lenient().when(mockEntityManager.getTransaction()).thenReturn(mockTransaction);

        // Damit ein Rollback wirklich aufgerufen werden kann, stubben wir isActive() auf true
        lenient().when(mockTransaction.isActive()).thenReturn(true);
    }

    @Test
    void testCreate_Success() {
        GuestEntity guest = new GuestEntity();
        guest.setId(1L);

        // Kein Fehler erwartet -> normaler Durchlauf
        guestDAO.create(guest);

        // Überprüfen, dass die Transaktion gestartet, persist aufgerufen und committet wurde
        verify(mockTransaction).begin();
        verify(mockEntityManager).persist(guest);
        verify(mockTransaction).commit();
        verify(mockEntityManager).close();
    }

    @Test
    void testCreate_Exception() {
        GuestEntity guest = new GuestEntity();
        // Wir simulieren, dass persist(...) eine Exception wirft
        doThrow(new RuntimeException("Simulated persist error"))
                .when(mockEntityManager).persist(any(GuestEntity.class));

        // Die DAO sollte daraufhin eine DataAccessException werfen
        assertThrows(DataAccessException.class, () -> guestDAO.create(guest));

        // Da ein Fehler aufgetreten ist, wird rollback() erwartet
        verify(mockTransaction).begin();
        verify(mockTransaction).rollback();
        verify(mockEntityManager).close();
    }

    @Test
    void testRead_Success() {
        GuestEntity dummy = new GuestEntity();
        dummy.setId(10L);

        // Wir simulieren, dass find(...) unser Dummy-GuestEntity zurückgibt
        when(mockEntityManager.find(GuestEntity.class, 10L)).thenReturn(dummy);

        GuestEntity result = guestDAO.read(10L);

        assertNotNull(result);
        assertEquals(10L, result.getId());
        // find(...) muss aufgerufen werden
        verify(mockEntityManager).find(GuestEntity.class, 10L);
        // close() wird am Ende aufgerufen
        verify(mockEntityManager).close();
    }

    @Test
    void testRead_Exception() {
        // Wenn find(...) eine Exception wirft, sollte unsere DAO eine DataAccessException werfen
        when(mockEntityManager.find(any(Class.class), anyLong()))
                .thenThrow(new RuntimeException("Simulated find error"));

        assertThrows(DataAccessException.class, () -> guestDAO.read(999L));
        verify(mockEntityManager).close();
    }

    @Test
    void testUpdate_Success() {
        GuestEntity guest = new GuestEntity();
        guest.setId(5L);

        // Normaler Durchlauf
        guestDAO.update(guest);

        verify(mockTransaction).begin();
        verify(mockEntityManager).merge(guest);
        verify(mockTransaction).commit();
        verify(mockEntityManager).close();
    }

    @Test
    void testUpdate_Exception() {
        doThrow(new RuntimeException("Simulated merge error"))
                .when(mockEntityManager).merge(any(GuestEntity.class));

        GuestEntity guest = new GuestEntity();
        guest.setId(5L);

        assertThrows(DataAccessException.class, () -> guestDAO.update(guest));

        verify(mockTransaction).begin();
        verify(mockTransaction).rollback();
        verify(mockEntityManager).close();
    }

    @Test
    void testDelete_Success() {
        GuestEntity guest = new GuestEntity();
        guest.setId(99L);

        // Normaler Durchlauf
        guestDAO.delete(guest);

        verify(mockTransaction).begin();
        verify(mockEntityManager).remove(guest);
        verify(mockTransaction).commit();
        verify(mockEntityManager).close();
    }

    @Test
    void testDelete_Exception() {
        doThrow(new RuntimeException("Simulated remove error"))
                .when(mockEntityManager).remove(any(GuestEntity.class));

        GuestEntity guest = new GuestEntity();
        guest.setId(99L);

        assertThrows(DataAccessException.class, () -> guestDAO.delete(guest));

        verify(mockTransaction).begin();
        verify(mockTransaction).rollback();
        verify(mockEntityManager).close();
    }

    @Test
    void testFindBookingsByGuestId_Success() {
        BookingEntity booking1 = new BookingEntity();
        BookingEntity booking2 = new BookingEntity();
        List<BookingEntity> dummyList = List.of(booking1, booking2);

        when(mockEntityManager.createQuery(anyString(), eq(BookingEntity.class)))
                .thenReturn(mockTypedQuery);
        when(mockTypedQuery.setParameter(eq("guestId"), anyLong()))
                .thenReturn(mockTypedQuery);
        when(mockTypedQuery.getResultList()).thenReturn(dummyList);

        List<BookingEntity> result = guestDAO.findBookingsByGuestId(123L);

        assertNotNull(result);
        assertEquals(2, result.size());
        verify(mockEntityManager).close();
    }

    @Test
    void testFindBookingsByGuestId_Exception() {
        when(mockEntityManager.createQuery(anyString(), eq(BookingEntity.class)))
                .thenThrow(new RuntimeException("Simulated query error"));

        assertThrows(DataAccessException.class, () -> guestDAO.findBookingsByGuestId(123L));
        verify(mockEntityManager).close();
    }
}
