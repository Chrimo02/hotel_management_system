package DAOTests;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.Arrays;
import java.util.List;

import hotelmanagementsystem.infrastructure.persistence.dao.implementation.HotelLocationDAOImpl;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import hotelmanagementsystem.infrastructure.persistence.entities.HotelLocationEntity;

@ExtendWith(MockitoExtension.class)
public class HotelLocationDAOTest {

    @InjectMocks
    private HotelLocationDAOImpl hotelLocationDAOImpl;

    @Mock
    private EntityManager em;

    @Mock
    private TypedQuery<HotelLocationEntity> typedQuery;

    private HotelLocationEntity dummyLocation;

    @BeforeEach
    public void setUp() {
        dummyLocation = new HotelLocationEntity.HotelLocationBuilder()
                .withAddress("123 Main St")
                .withCity("TestCity")
                .withCountry("TestCountry")
                .build();
    }

    @Test
    public void testCreate_Success() {
        assertDoesNotThrow(() -> hotelLocationDAOImpl.create(dummyLocation));
        verify(em).persist(dummyLocation);
    }

    @Test
    public void testRead_Success() {
        when(em.find(HotelLocationEntity.class, 1L)).thenReturn(dummyLocation);
        HotelLocationEntity result = hotelLocationDAOImpl.read(1L);
        assertEquals(dummyLocation, result);
        verify(em).find(HotelLocationEntity.class, 1L);
    }

    @Test
    public void testReadAll_Success() {
        List<HotelLocationEntity> list = Arrays.asList(dummyLocation);
        when(em.createQuery(anyString(), eq(HotelLocationEntity.class))).thenReturn(typedQuery);
        when(typedQuery.getResultList()).thenReturn(list);
        List<HotelLocationEntity> result = hotelLocationDAOImpl.readAll();
        assertEquals(list, result);
        verify(typedQuery).getResultList();
    }

    @Test
    public void testUpdate_Success() {
        assertDoesNotThrow(() -> hotelLocationDAOImpl.update(dummyLocation));
        verify(em).merge(dummyLocation);
    }

    @Test
    public void testDelete_Success() {
        when(em.contains(dummyLocation)).thenReturn(true);
        assertDoesNotThrow(() -> hotelLocationDAOImpl.delete(dummyLocation));
        verify(em).remove(dummyLocation);
    }

    @Test
    public void testFindLocationByHotelId_Success() {
        when(em.createQuery(anyString(), eq(HotelLocationEntity.class))).thenReturn(typedQuery);
        when(typedQuery.setParameter("hotelId", 1L)).thenReturn(typedQuery);
        when(typedQuery.getSingleResult()).thenReturn(dummyLocation);
        HotelLocationEntity result = hotelLocationDAOImpl.findLocationByHotelId(1L);
        assertEquals(dummyLocation, result);
        verify(typedQuery).setParameter("hotelId", 1L);
        verify(typedQuery).getSingleResult();
    }

    @Test
    public void testFindLocationByHotelId_NoResult() {
        when(em.createQuery(anyString(), eq(HotelLocationEntity.class))).thenReturn(typedQuery);
        when(typedQuery.setParameter("hotelId", 2L)).thenReturn(typedQuery);
        when(typedQuery.getSingleResult()).thenThrow(new NoResultException());
        Exception ex = assertThrows(IllegalArgumentException.class, () -> hotelLocationDAOImpl.findLocationByHotelId(2L));
        assertTrue(ex.getMessage().contains("No location found for Hotel ID: 2"));
    }
}
