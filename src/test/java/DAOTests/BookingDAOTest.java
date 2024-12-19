package DAOTests;
import de.thws.fiw.backendsystems.templates.jpatemplate.infrastructure.persistence.dao.implementation.BookingDAOImpl;
import de.thws.fiw.backendsystems.templates.jpatemplate.infrastructure.persistence.entities.BookingEntity;
import de.thws.fiw.backendsystems.templates.jpatemplate.infrastructure.persistence.entities.GuestEntity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.util.Collections;

    class BookingDAOImplTest {

        private BookingDAOImpl bookingDAO;
        private EntityManager mockEntityManager;
        private EntityTransaction mockTransaction;

        @BeforeEach
        void setUp() {
            mockEntityManager = mock(EntityManager.class);
            mockTransaction = mock(EntityTransaction.class);
            bookingDAO = new BookingDAOImpl() {
                @Override
                public EntityManager entityManager() {
                    return mockEntityManager;
                }
            };
        }

        @Test
        void testCreateBookingSuccess() {
            BookingEntity bookingEntity = new BookingEntity();
            bookingEntity.setCheckInDate(LocalDate.now());
            bookingEntity.setCheckOutDate(LocalDate.now().plusDays(1));
            bookingEntity.setGuests(Collections.singletonList(new GuestEntity()));

            when(mockEntityManager.getTransaction()).thenReturn(mockTransaction);

            // Execute the method
            BookingEntity result = bookingDAO.create(bookingEntity);

            // Verify the interactions
            verify(mockEntityManager).persist(bookingEntity);
            verify(mockTransaction).begin();
            verify(mockTransaction).commit();
            verify(mockEntityManager).close();

            // Validate result
            assertNotNull(result);
        }

        @Test
        void testCreateBookingFailure() {
            BookingEntity bookingEntity = new BookingEntity();
            bookingEntity.setCheckInDate(LocalDate.now());
            bookingEntity.setCheckOutDate(LocalDate.now().plusDays(1));
            bookingEntity.setGuests(Collections.singletonList(new GuestEntity()));

            when(mockEntityManager.getTransaction()).thenReturn(mockTransaction);
            doThrow(new RuntimeException("Database error")).when(mockEntityManager).persist(bookingEntity);

            // Execute and assert exception
            assertThrows(DataAccessException.class, () -> bookingDAO.create(bookingEntity));

            // Verify rollback and close
            verify(mockEntityManager).getTransaction();
            verify(mockTransaction).rollback();
            verify(mockEntityManager).close();
        }

        @Test
        void testReadBookingSuccess() {
            long bookingId = 1L;
            BookingEntity expectedBooking = new BookingEntity();
            when(mockEntityManager.find(BookingEntity.class, bookingId)).thenReturn(expectedBooking);

            // Execute the method
            BookingEntity result = bookingDAO.read(bookingId);

            // Verify the interaction
            verify(mockEntityManager).find(BookingEntity.class, bookingId);
            assertEquals(expectedBooking, result);
        }

        @Test
        void testReadBookingFailure() {
            long bookingId = 1L;
            when(mockEntityManager.find(BookingEntity.class, bookingId)).thenThrow(new RuntimeException("Database error"));

            // Execute and assert exception
            assertThrows(DataAccessException.class, () -> bookingDAO.read(bookingId));

            verify(mockEntityManager).find(BookingEntity.class, bookingId);
        }

        @Test
        void testUpdateBookingSuccess() {
            BookingEntity bookingEntity = new BookingEntity();
            bookingEntity.setCheckInDate(LocalDate.now());
            bookingEntity.setCheckOutDate(LocalDate.now().plusDays(1));

            when(mockEntityManager.getTransaction()).thenReturn(mockTransaction);

            // Execute the method
            bookingDAO.update(bookingEntity);

            // Verify interactions
            verify(mockEntityManager).merge(bookingEntity);
            verify(mockTransaction).begin();
            verify(mockTransaction).commit();
            verify(mockEntityManager).close();
        }

        @Test
        void testUpdateBookingFailure() {
            BookingEntity bookingEntity = new BookingEntity();
            bookingEntity.setCheckInDate(LocalDate.now());
            bookingEntity.setCheckOutDate(LocalDate.now().plusDays(1));

            when(mockEntityManager.getTransaction()).thenReturn(mockTransaction);
            doThrow(new RuntimeException("Database error")).when(mockEntityManager).merge(bookingEntity);

            // Execute and assert exception
            assertThrows(DataAccessException.class, () -> bookingDAO.update(bookingEntity));

            // Verify rollback and close
            verify(mockEntityManager).getTransaction();
            verify(mockTransaction).rollback();
            verify(mockEntityManager).close();
        }
    }

}
