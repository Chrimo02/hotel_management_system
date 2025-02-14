package DAOTests;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import hotelmanagementsystem.infrastructure.persistence.dao.implementation.HotelDAOImpl;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import hotelmanagementsystem.infrastructure.persistence.entities.HotelEntity;

@ExtendWith(MockitoExtension.class)
public class HotelDAOTest {

    @InjectMocks
    private HotelDAOImpl hotelDAOImpl;

    @Mock
    private EntityManager em;

    @Mock
    private TypedQuery<Long> countQuery;

    @Mock
    private TypedQuery<HotelEntity> hotelTypedQuery;

    private HotelEntity dummyHotelEntity;

    @BeforeEach
    public void setUp() {
        dummyHotelEntity = new HotelEntity.HotelBuilder()
                .withId(1L)
                .withName("Test Hotel")
                .withDescription("Test Description")
                .build();
    }

    @Test
    public void testCreateHotel_Success() {
        HotelEntity result = hotelDAOImpl.createHotel(dummyHotelEntity);
        assertEquals(dummyHotelEntity, result);
        verify(em).persist(dummyHotelEntity);
    }

    @Test
    public void testFindById_Found() {
        when(em.find(HotelEntity.class, 1L)).thenReturn(dummyHotelEntity);
        HotelEntity result = hotelDAOImpl.findById(1L);
        assertNotNull(result);
        assertEquals(dummyHotelEntity, result);
        verify(em).find(HotelEntity.class, 1L);
    }

    @Test
    public void testFindById_NotFound() {
        when(em.find(HotelEntity.class, 2L)).thenReturn(null);
        HotelEntity result = hotelDAOImpl.findById(2L);
        assertNull(result);
        verify(em).find(HotelEntity.class, 2L);
    }

    @Test
    public void testUpdateHotel_Success() {
        when(em.merge(dummyHotelEntity)).thenReturn(dummyHotelEntity);
        HotelEntity result = hotelDAOImpl.updateHotel(dummyHotelEntity);
        assertEquals(dummyHotelEntity, result);
        verify(em).merge(dummyHotelEntity);
    }

    @Test
    public void testDeleteById_Success() {
        when(em.find(HotelEntity.class, 1L)).thenReturn(dummyHotelEntity);
        assertDoesNotThrow(() -> hotelDAOImpl.deleteById(1L));
        verify(em).find(HotelEntity.class, 1L);
        verify(em).remove(dummyHotelEntity);
    }

    @Test
    public void testDeleteById_NotFound() {
        when(em.find(HotelEntity.class, 2L)).thenReturn(null);
        assertDoesNotThrow(() -> hotelDAOImpl.deleteById(2L));
        verify(em).find(HotelEntity.class, 2L);
        verify(em, never()).remove(any());
    }

    @Test
    public void testCountByFilter_Success() {
        String city = "TestCity";
        double minRating = 3.5;
        when(em.createQuery(anyString(), eq(Long.class))).thenReturn(countQuery);
        when(countQuery.setParameter(eq("city"), any())).thenReturn(countQuery);
        when(countQuery.setParameter(eq("minRating"), eq(minRating))).thenReturn(countQuery);
        when(countQuery.getSingleResult()).thenReturn(5L);

        long count = hotelDAOImpl.countByFilter(city, minRating);
        assertEquals(5L, count);
        verify(countQuery).setParameter(eq("city"), any());
        verify(countQuery).setParameter("minRating", minRating);
        verify(countQuery).getSingleResult();
    }

    @Test
    public void testFindByFilter_Success() {
        String city = "TestCity";
        double minRating = 4.0;
        int offset = 0;
        int limit = 10;
        List<HotelEntity> entities = Arrays.asList(dummyHotelEntity);
        when(em.createQuery(anyString(), eq(HotelEntity.class))).thenReturn(hotelTypedQuery);
        when(hotelTypedQuery.setParameter(eq("city"), any())).thenReturn(hotelTypedQuery);
        when(hotelTypedQuery.setParameter("minRating", minRating)).thenReturn(hotelTypedQuery);
        when(hotelTypedQuery.setFirstResult(offset)).thenReturn(hotelTypedQuery);
        when(hotelTypedQuery.setMaxResults(limit)).thenReturn(hotelTypedQuery);
        when(hotelTypedQuery.getResultList()).thenReturn(entities);

        List<HotelEntity> result = hotelDAOImpl.findByFilter(city, minRating, offset, limit);
        assertEquals(entities, result);
        verify(hotelTypedQuery).getResultList();
    }
}
