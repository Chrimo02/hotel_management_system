package mapperTestsPersistenceDomain;

import static org.junit.jupiter.api.Assertions.*;

import hotelmanagementsystem.domain.models.HotelLocation;
import hotelmanagementsystem.infrastructure.persistence.entities.HotelLocationEntity;
import hotelmanagementsystem.infrastructure.persistence.mapper.HotelLocationMapper;
import org.junit.jupiter.api.Test;

public class HotelLocationMapperTest {

    private HotelLocationMapper mapper = new HotelLocationMapper();

    @Test
    public void testMapToEntity() {
        HotelLocation location = new HotelLocation.HotelLocationBuilder()
                .withId(1L)
                .withAddress("123 Main St")
                .withCity("TestCity")
                .withCountry("TestCountry")
                .build();
        HotelLocationEntity entity = mapper.mapToEntity(location);
        assertNotNull(entity);
        assertEquals("123 Main St", entity.getAddress());
        assertEquals("TestCity", entity.getCity());
        assertEquals("TestCountry", entity.getCountry());
    }

    @Test
    public void testMapToDomain() {
        HotelLocationEntity entity = new HotelLocationEntity.HotelLocationBuilder()
                .withAddress("456 Elm St")
                .withCity("SampleCity")
                .withCountry("SampleCountry")
                .build();
        HotelLocation location = mapper.mapToDomain(entity);
        assertNotNull(location);
        assertEquals(entity.getAddress(), location.getAddress());
        assertEquals(entity.getCity(), location.getCity());
        assertEquals(entity.getCountry(), location.getCountry());
    }
}
