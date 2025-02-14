package DAOTests;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import hotelmanagementsystem.infrastructure.persistence.dao.implementation.RoomDAOImpl;
import jakarta.persistence.EntityManager;
import hotelmanagementsystem.infrastructure.persistence.entities.RoomEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class RoomDAOTest {

    @InjectMocks
    private RoomDAOImpl roomDAOImpl;

    @Mock
    private EntityManager em;

    private RoomEntity dummyRoomEntity;

    @BeforeEach
    public void setUp() {
        dummyRoomEntity = mock(RoomEntity.class);
        lenient().when(dummyRoomEntity.getId()).thenReturn(10L);
    }

    @Test
    public void testCreate_Success() {
        RoomEntity result = roomDAOImpl.create(dummyRoomEntity);
        assertEquals(dummyRoomEntity, result);
        verify(em).persist(dummyRoomEntity);
    }

    @Test
    public void testRead_Success() {
        when(em.find(RoomEntity.class, 10L)).thenReturn(dummyRoomEntity);
        RoomEntity result = roomDAOImpl.read(10L);
        assertEquals(dummyRoomEntity, result);
        verify(em).find(RoomEntity.class, 10L);
    }

    @Test
    public void testUpdate_Success() {
        when(em.merge(dummyRoomEntity)).thenReturn(dummyRoomEntity);
        assertDoesNotThrow(() -> roomDAOImpl.update(dummyRoomEntity));
        verify(em).merge(dummyRoomEntity);
    }

    @Test
    public void testDelete_Success() {
        when(em.contains(dummyRoomEntity)).thenReturn(true);
        assertDoesNotThrow(() -> roomDAOImpl.delete(dummyRoomEntity));
        verify(em).remove(dummyRoomEntity);
    }
}
