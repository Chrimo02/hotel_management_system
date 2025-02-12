/*
package DAOTests;

import hotelmanagementsystem.infrastructure.persistence.entities.RoomEntity;
import hotelmanagementsystem.infrastructure.persistence.entities.SingleRoomEntity;
import hotelmanagementsystem.infrastructure.persistence.dao.implementation.RoomDAOImpl;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;
public class RoomDAOTest {
    @Mock
    private EntityManagerFactory emf;

    @Mock
    private EntityManager em;

    @Mock
    private EntityTransaction transaction;

    @InjectMocks
    private RoomDAOImpl roomDAO;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        when(emf.createEntityManager()).thenReturn(em);
        when(em.getTransaction()).thenReturn(transaction);
    }

    @Test
    void testCreate() {
        SingleRoomEntity roomEntity = new SingleRoomEntity();
        roomEntity.setId(1L);

        doNothing().when(em).persist(roomEntity);

        long resultId = roomDAO.create(roomEntity);

        assertEquals(1L, resultId);
        verify(em).persist(roomEntity);
        verify(transaction).begin();
        verify(transaction).commit();
        verify(em).close();
    }

    @Test
    void testRead() {
        long roomId = 1L;
        SingleRoomEntity roomEntity = new SingleRoomEntity();
        roomEntity.setId(roomId);

        // Simuliere das Verhalten von Hibernate bei der Verwendung von em.find
        when(em.find(RoomEntity.class, roomId)).thenReturn(roomEntity);

        // Rufe die Methode auf
        RoomEntity result = roomDAO.read(roomId);

        // Überprüfungen
        assertNotNull(result); // Stelle sicher, dass ein Objekt zurückgegeben wird
        assertEquals(roomId, result.getId()); // Überprüfe, ob die ID korrekt ist
        assertTrue(result instanceof SingleRoomEntity); // Überprüfe, ob es ein SingleRoomEntity ist
        verify(em).find(RoomEntity.class, roomId); // Sicherstellen, dass em.find korrekt aufgerufen wurde
        verify(em).close(); // Sicherstellen, dass der EntityManager geschlossen wurde
    }

    @Test
    void testUpdate() {
        SingleRoomEntity roomEntity = new SingleRoomEntity();

        // Mock the merge method to return the same roomEntity
        when(em.merge(roomEntity)).thenReturn(roomEntity);

        roomDAO.update(roomEntity);

        // Verify the interactions
        verify(transaction).begin();
        verify(em).merge(roomEntity);
        verify(transaction).commit();
        verify(em).close();
    }

    @Test
    void testDelete() {
        SingleRoomEntity roomEntity = new SingleRoomEntity();

        doNothing().when(em).remove(roomEntity);

        roomDAO.delete(roomEntity);

        verify(em).remove(roomEntity);
        verify(transaction).begin();
        verify(transaction).commit();
        verify(em).close();
    }
}

 */
