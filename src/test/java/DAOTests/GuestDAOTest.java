    package DAOTests;

    import de.thws.fiw.backendsystems.templates.jpatemplate.infrastructure.persistence.dao.implementation.DataAccessException;
    import de.thws.fiw.backendsystems.templates.jpatemplate.infrastructure.persistence.dao.interfaces.GuestDAO;
    import de.thws.fiw.backendsystems.templates.jpatemplate.infrastructure.persistence.entities.GuestEntity;
    import de.thws.fiw.backendsystems.templates.jpatemplate.infrastructure.persistence.dao.implementation.GuestDAOImpl;
    import jakarta.persistence.EntityManager;
    import jakarta.persistence.EntityTransaction;
    import org.junit.jupiter.api.BeforeEach;
    import org.junit.jupiter.api.Test;
    import org.mockito.InjectMocks;
    import org.mockito.Mock;
    import org.mockito.MockitoAnnotations;

    import static org.junit.jupiter.api.Assertions.*;
    import static org.mockito.Mockito.*;

    public class GuestDAOTest {

        @Mock
        private EntityManager entityManager;

        @Mock
        private EntityTransaction transaction;

        @InjectMocks
        private GuestDAOImpl guestDAOImpl;

        private GuestEntity mockGuestEntity;

        @BeforeEach
        public void setUp() {
            MockitoAnnotations.openMocks(this);

            // Initialisiere Mock GuestEntity
            mockGuestEntity = new GuestEntity();
            mockGuestEntity.setId(1L);
            mockGuestEntity.setFirstName("John");
            mockGuestEntity.setLastName("Doe");
            mockGuestEntity.setTitle("Mr.");
            mockGuestEntity.seteMail("john.doe@example.com");
            mockGuestEntity.setPhoneNumber("1234567890");

            // EntityManager simulieren
            when(entityManager.getTransaction()).thenReturn(transaction);
        }

        @Test
        public void testCreate_Success() {
            doNothing().when(entityManager).persist(mockGuestEntity);
            doNothing().when(transaction).begin();
            doNothing().when(transaction).commit();

            guestDAOImpl.create(mockGuestEntity);

            verify(transaction, times(1)).begin();
            verify(entityManager, times(1)).persist(mockGuestEntity);
            verify(transaction, times(1)).commit();
        }

        @Test
        public void testCreate_ExceptionRollback() {
            doThrow(new RuntimeException("Persistence error")).when(entityManager).persist(mockGuestEntity);
            when(transaction.isActive()).thenReturn(true);

            Exception exception = assertThrows(DataAccessException.class, () -> {
                guestDAOImpl.create(mockGuestEntity);
            });

            assertTrue(exception.getMessage().contains("Error while creating guest entity"));
            verify(transaction, times(1)).begin();
            verify(transaction, times(1)).rollback();
        }

        @Test
        public void testRead_Success() {
            when(entityManager.find(GuestEntity.class, 1L)).thenReturn(mockGuestEntity);

            GuestEntity result = guestDAOImpl.read(1L);

            assertNotNull(result);
            assertEquals(1L, result.getId());
            verify(entityManager, times(1)).find(GuestEntity.class, 1L);
        }

        @Test
        public void testRead_Exception() {
            when(entityManager.find(GuestEntity.class, 1L)).thenThrow(new RuntimeException("Read error"));

            Exception exception = assertThrows(DataAccessException.class, () -> {
                guestDAOImpl.read(1L);
            });

            assertTrue(exception.getMessage().contains("Error while reading guest entity"));
        }

        @Test
        public void testUpdate_Success() {
            doNothing().when(entityManager).merge(mockGuestEntity);
            doNothing().when(transaction).begin();
            doNothing().when(transaction).commit();

            guestDAOImpl.update(mockGuestEntity);

            verify(transaction, times(1)).begin();
            verify(entityManager, times(1)).merge(mockGuestEntity);
            verify(transaction, times(1)).commit();
        }

        @Test
        public void testUpdate_ExceptionRollback() {
            doThrow(new RuntimeException("Update error")).when(entityManager).merge(mockGuestEntity);
            when(transaction.isActive()).thenReturn(true);

            Exception exception = assertThrows(DataAccessException.class, () -> {
                guestDAOImpl.update(mockGuestEntity);
            });

            assertTrue(exception.getMessage().contains("Error while updating guest entity"));
            verify(transaction, times(1)).begin();
            verify(transaction, times(1)).rollback();
        }

        @Test
        public void testDelete_Success() {
            doNothing().when(entityManager).remove(mockGuestEntity);
            doNothing().when(transaction).begin();
            doNothing().when(transaction).commit();

            guestDAOImpl.delete(mockGuestEntity);

            verify(transaction, times(1)).begin();
            verify(entityManager, times(1)).remove(mockGuestEntity);
            verify(transaction, times(1)).commit();
        }

        @Test
        public void testDelete_ExceptionRollback() {
            doThrow(new RuntimeException("Delete error")).when(entityManager).remove(mockGuestEntity);
            when(transaction.isActive()).thenReturn(true);

            Exception exception = assertThrows(DataAccessException.class, () -> {
                guestDAOImpl.delete(mockGuestEntity);
            });

            assertTrue(exception.getMessage().contains("Error while deleting guest entity"));
            verify(transaction, times(1)).begin();
            verify(transaction, times(1)).rollback();
        }
    }

