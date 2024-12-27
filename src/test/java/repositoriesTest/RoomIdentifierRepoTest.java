package repositoriesTest;
import de.thws.fiw.backendsystems.templates.jpatemplate.domain.models.RoomIdentifier;
import de.thws.fiw.backendsystems.templates.jpatemplate.infrastructure.persistence.dao.interfaces.RoomIdentifierDAO;
import de.thws.fiw.backendsystems.templates.jpatemplate.infrastructure.persistence.entities.RoomIdentifierEntity;
import de.thws.fiw.backendsystems.templates.jpatemplate.infrastructure.persistence.mapper.RoomIdentifierMapper;
import de.thws.fiw.backendsystems.templates.jpatemplate.infrastructure.persistence.repositories.adapters.RoomIdentifierDatabaseAdapter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.*;
public class RoomIdentifierRepoTest {
    @Mock
    private RoomIdentifierDAO roomIdentifierDAO;

    @Mock
    private RoomIdentifierMapper roomIdentifierMapper;

    @InjectMocks
    private RoomIdentifierDatabaseAdapter roomIdentifierDatabaseAdapter;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testSaveRoomIdentifier() {
        RoomIdentifier roomIdentifier = new RoomIdentifier("BuildingA", 1, "101A");
        RoomIdentifierEntity roomIdentifierEntity = new RoomIdentifierEntity();

        when(roomIdentifierMapper.domainToEntity(roomIdentifier)).thenReturn(roomIdentifierEntity);

        roomIdentifierDatabaseAdapter.saveRoomIdentifier(roomIdentifier);

        verify(roomIdentifierMapper).domainToEntity(roomIdentifier);
        verify(roomIdentifierDAO).create(roomIdentifierEntity);
    }
}