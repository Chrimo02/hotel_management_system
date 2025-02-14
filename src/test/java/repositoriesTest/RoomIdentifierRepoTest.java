package repositoriesTest;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import hotelmanagementsystem.domain.models.RoomIdentifier;
import hotelmanagementsystem.infrastructure.persistence.dao.interfaces.RoomIdentifierDAO;
import hotelmanagementsystem.infrastructure.persistence.entities.RoomIdentifierEntity;
import hotelmanagementsystem.infrastructure.persistence.mapper.RoomIdentifierMapper;
import hotelmanagementsystem.infrastructure.persistence.repositories.adapters.RoomIdentifierDatabaseAdapter;
import hotelmanagementsystem.infrastructure.persistence.repositories.interfaces.RoomIdentifierRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

class RoomIdentifierRepoTest {

    @Mock
    private RoomIdentifierDAO roomIdentifierDAO;
    @Mock
    private RoomIdentifierMapper roomIdentifierMapper;

    @InjectMocks
    private RoomIdentifierDatabaseAdapter adapter;

    private RoomIdentifier dummyIdentifier;
    private RoomIdentifierEntity dummyIdentifierEntity;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        dummyIdentifier = new RoomIdentifier("BuildingA", 1, "101A");
        dummyIdentifierEntity = new RoomIdentifierEntity("BuildingA", 1, "101A");
    }

    @Test
    public void testSaveRoomIdentifier() {
        when(roomIdentifierMapper.domainToEntity(dummyIdentifier)).thenReturn(dummyIdentifierEntity);
        doNothing().when(roomIdentifierDAO).create(dummyIdentifierEntity);
        assertDoesNotThrow(() -> adapter.saveRoomIdentifier(dummyIdentifier));
        verify(roomIdentifierDAO, times(1)).create(dummyIdentifierEntity);
    }
}
