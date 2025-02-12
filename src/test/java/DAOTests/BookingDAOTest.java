/*
package DAOTests;

import hotelmanagementsystem.infrastructure.persistence.dao.implementation.BookingDAOImpl;
import hotelmanagementsystem.infrastructure.persistence.dao.implementation.DataAccessException;
import hotelmanagementsystem.infrastructure.persistence.entities.BookingEntity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.lenient;

@ExtendWith(MockitoExtension.class)
class BookingDAOTest {

    @Spy
    private BookingDAOImpl bookingDAO;

    @Mock
    private EntityManager mockEntityManager;

    @Mock
    private EntityTransaction mockTransaction;

    @BeforeEach
    void setUp() {
        lenient().doReturn(mockEntityManager).when(bookingDAO).entityManager();
        lenient().when(mockEntityManager.getTransaction()).thenReturn(mockTransaction);
        lenient().when(mockTransaction.isActive()).thenReturn(true);
    }

    @Test
    void testCreate_Success() {
        BookingEntity booking = new BookingEntity();

        // Normal case
        bookingDAO.create(booking);

        verify(mockTransaction).begin();
        verify(mockEntityManager).persist(booking);
        verify(mockTransaction).commit();
        verify(mockEntityManager).close();
    }

    @Test
    void testCreate_Exception() {
        BookingEntity booking = new BookingEntity();
        doThrow(new RuntimeException("Simulated persist error"))
                .when(mockEntityManager).persist(any(BookingEntity.class));

        assertThrows(DataAccessException.class, () -> bookingDAO.create(booking));

        verify(mockTransaction).begin();
        verify(mockTransaction).rollback();
        verify(mockEntityManager).close();
    }

    @Test
    void testRead_Success() {
        long bookingId = 10L;
        BookingEntity dummy = new BookingEntity();

        when(mockEntityManager.find(BookingEntity.class, bookingId)).thenReturn(dummy);

        BookingEntity result = bookingDAO.read(bookingId);

        assertNotNull(result);
        verify(mockEntityManager).find(BookingEntity.class, bookingId);
        verify(mockEntityManager).close();
    }

    @Test
    void testRead_Exception() {
        long bookingId = 999L;

        when(mockEntityManager.find(any(Class.class), eq(bookingId)))
                .thenThrow(new RuntimeException("Simulated find error"));

        assertThrows(DataAccessException.class, () -> bookingDAO.read(bookingId));
        verify(mockEntityManager).close();
    }

    @Test
    void testUpdate_Success() {
        BookingEntity booking = new BookingEntity();

        bookingDAO.update(booking);

        verify(mockTransaction).begin();
        verify(mockEntityManager).merge(booking);
        verify(mockTransaction).commit();
        verify(mockEntityManager).close();
    }

    @Test
    void testUpdate_Exception() {
        BookingEntity booking = new BookingEntity();
        doThrow(new RuntimeException("Simulated merge error"))
                .when(mockEntityManager).merge(any(BookingEntity.class));

        assertThrows(DataAccessException.class, () -> bookingDAO.update(booking));

        verify(mockTransaction).begin();
        verify(mockTransaction).rollback();
        verify(mockEntityManager).close();
    }
}


 */