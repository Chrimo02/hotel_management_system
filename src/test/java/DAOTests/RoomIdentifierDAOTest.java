package DAOTests;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import hotelmanagementsystem.infrastructure.persistence.dao.implementation.RoomIdentifierDAOImpl;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import hotelmanagementsystem.infrastructure.persistence.entities.RoomIdentifierEntity;

@ExtendWith(MockitoExtension.class)
public class RoomIdentifierDAOTest {

    @InjectMocks
    private RoomIdentifierDAOImpl roomIdentifierDAOImpl;

    @Mock
    private EntityManager em;

    private RoomIdentifierEntity dummyEntity;

    @BeforeEach
    public void setUp() {
        dummyEntity = new RoomIdentifierEntity("BuildingA", 1, "101A");
    }

    @Test
    public void testCreate_Success() {
        assertDoesNotThrow(() -> roomIdentifierDAOImpl.create(dummyEntity));
        verify(em).persist(dummyEntity);
    }
}
