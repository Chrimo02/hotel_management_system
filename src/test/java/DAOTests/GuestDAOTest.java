package DAOTests;

import de.thws.fiw.backendsystems.templates.jpatemplate.infrastructure.persistence.dao.implementation.DataAccessException;
import de.thws.fiw.backendsystems.templates.jpatemplate.infrastructure.persistence.dao.implementation.GuestDAOImpl;
import de.thws.fiw.backendsystems.templates.jpatemplate.infrastructure.persistence.dao.interfaces.GuestDAO;
import de.thws.fiw.backendsystems.templates.jpatemplate.infrastructure.persistence.entities.GuestEntity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class GuestDAOTest {

    @Mock
    private EntityManager entityManager;

    @Mock
    private EntityTransaction transaction;

    private GuestDAOImpl guestDAO;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        guestDAO = new GuestDAOImpl() {
            @Override
            public EntityManager entityManager() {
                return entityManager;
            }
        };
    }

    @Test
    public void testCreateGuest_Success() {
        GuestEntity guestEntity = new GuestEntity();
        when(entityManager.getTransaction()).thenReturn(transaction);

        guestDAO.create(guestEntity);

        verify(entityManager, times(1)).persist(guestEntity);
        verify(transaction, times(1)).begin();
        verify(transaction, times(1)).commit();
    }

    @Test
    public void testCreateGuest_Exception_Rollback() {
        GuestEntity guestEntity = new GuestEntity();
        when(entityManager.getTransaction()).thenReturn(transaction);
        doThrow(new RuntimeException("Persistence Error")).when(entityManager).persist(guestEntity);

        Exception exception = assertThrows(DataAccessException.class, () -> guestDAO.create(guestEntity));

        assertTrue(exception.getMessage().contains("rolled back"));
        verify(transaction, times(1)).rollback();
        verify(entityManager, times(1)).close();
    }

    @Test
    public void testReadGuest_Success() {
        GuestEntity guestEntity = new GuestEntity();
        when(entityManager.find(GuestEntity.class, 1L)).thenReturn(guestEntity);

        GuestEntity result = guestDAO.read(1L);

        assertNotNull(result);
        verify(entityManager, times(1)).find(GuestEntity.class, 1L);
    }

    @Test
    public void testReadGuest_Exception() {
        when(entityManager.find(GuestEntity.class, 1L)).thenThrow(new RuntimeException("Persistence Error"));

        Exception exception = assertThrows(DataAccessException.class, () -> guestDAO.read(1L));

        assertTrue(exception.getMessage().contains("Error while reading"));
    }

    @Test
    public void testUpdateGuest_Success() {
        GuestEntity guestEntity = new GuestEntity();
        when(entityManager.getTransaction()).thenReturn(transaction);

        guestDAO.update(guestEntity);

        verify(entityManager, times(1)).merge(guestEntity);
        verify(transaction, times(1)).begin();
        verify(transaction, times(1)).commit();
    }

    @Test
    public void testUpdateGuest_Exception_Rollback() {
        GuestEntity guestEntity = new GuestEntity();
        when(entityManager.getTransaction()).thenReturn(transaction);
        doThrow(new RuntimeException("Persistence Error")).when(entityManager).merge(guestEntity);

        Exception exception = assertThrows(DataAccessException.class, () -> guestDAO.update(guestEntity));

        assertTrue(exception.getMessage().contains("rolled back"));
        verify(transaction, times(1)).rollback();
        verify(entityManager, times(1)).close();
    }

    @Test
    public void testDeleteGuest_Success() {
        GuestEntity guestEntity = new GuestEntity();
        when(entityManager.getTransaction()).thenReturn(transaction);
        guestDAO.delete(guestEntity);
        verify(entityManager, times(1)).remove(guestEntity);
        verify(transaction, times(1)).begin();
        verify(transaction, times(1)).commit();
    }

    @Test
    public void testDeleteGuest_Exception_Rollback() {
        GuestEntity guestEntity = new GuestEntity();
        when(entityManager.getTransaction()).thenReturn(transaction);
        doThrow(new RuntimeException("Persistence Error")).when(entityManager).remove(guestEntity);

        Exception exception = assertThrows(DataAccessException.class, () -> guestDAO.delete(guestEntity));

        assertTrue(exception.getMessage().contains("rolled back"));
        verify(transaction, times(1)).rollback();
        verify(entityManager, times(1)).close();
    }
}

