package DAOTests;
import de.thws.fiw.backendsystems.templates.jpatemplate.infrastructure.persistence.entities.RoomEntity;
import de.thws.fiw.backendsystems.templates.jpatemplate.infrastructure.persistence.entities.SingleRoomEntity;
import de.thws.fiw.backendsystems.templates.jpatemplate.infrastructure.persistence.entities.DoubleRoomEntity;
import de.thws.fiw.backendsystems.templates.jpatemplate.infrastructure.persistence.dao.implementation.RoomDAOImpl;
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

        // Verwende ArgumentMatchers für flexiblere Matching-Logik
        when(em.find(eq(SingleRoomEntity.class), eq(roomId))).thenReturn(roomEntity);

        RoomEntity result = roomDAO.read(roomId);

        assertNotNull(result); // Sicherstellen, dass ein Objekt zurückgegeben wird
        assertEquals(roomId, result.getId()); // Überprüfen, dass die ID übereinstimmt
        verify(em).find(SingleRoomEntity.class, roomId);
        verify(em).close();
    }

    @Test
    void testUpdate() {
        SingleRoomEntity roomEntity = new SingleRoomEntity();

        doNothing().when(em).merge(roomEntity);

        roomDAO.update(roomEntity);

        verify(em).merge(roomEntity);
        verify(transaction).begin();
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
