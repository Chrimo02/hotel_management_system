package DAOTests;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.Arrays;
import java.util.List;

import hotelmanagementsystem.infrastructure.persistence.dao.implementation.GuestDAOImpl;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import hotelmanagementsystem.infrastructure.persistence.entities.BookingEntity;
import hotelmanagementsystem.infrastructure.persistence.entities.GuestEntity;

@ExtendWith(MockitoExtension.class)
public class GuestDAOTest {

    @InjectMocks
    private GuestDAOImpl guestDAOImpl;

    @Mock
    private EntityManager em;

    @Mock
    private TypedQuery<BookingEntity> typedQuery;

    private GuestEntity dummyGuestEntity;

    @BeforeEach
    public void setUp() {
        dummyGuestEntity = new GuestEntity("John", "Doe", 1990, 1, 1, "john@example.com", "123456789");
    }

    @Test
    public void testCreate_Success() {
        GuestEntity result = guestDAOImpl.create(dummyGuestEntity);
        assertEquals(dummyGuestEntity, result);
        verify(em).persist(dummyGuestEntity);
    }

    @Test
    public void testRead_Success() {
        when(em.find(GuestEntity.class, 1L)).thenReturn(dummyGuestEntity);
        GuestEntity result = guestDAOImpl.read(1L);
        assertEquals(dummyGuestEntity, result);
        verify(em).find(GuestEntity.class, 1L);
    }

    @Test
    public void testUpdate_Success() {
        when(em.merge(dummyGuestEntity)).thenReturn(dummyGuestEntity);
        GuestEntity result = guestDAOImpl.update(dummyGuestEntity);
        assertEquals(dummyGuestEntity, result);
        verify(em).merge(dummyGuestEntity);
    }

    @Test
    public void testDelete_Success() {
        when(em.contains(dummyGuestEntity)).thenReturn(true);
        assertDoesNotThrow(() -> guestDAOImpl.delete(dummyGuestEntity));
        verify(em).remove(dummyGuestEntity);
    }

    @Test
    public void testFindBookingsByGuestId_Success() {
        long guestId = 1L;
        BookingEntity booking1 = mock(BookingEntity.class);
        BookingEntity booking2 = mock(BookingEntity.class);
        List<BookingEntity> dummyList = Arrays.asList(booking1, booking2);

        String jpql = "SELECT b FROM BookingEntity b JOIN b.guests g WHERE g.id = :guestId";
        when(em.createQuery(jpql, BookingEntity.class)).thenReturn(typedQuery);
        when(typedQuery.setParameter("guestId", guestId)).thenReturn(typedQuery);
        when(typedQuery.getResultList()).thenReturn(dummyList);

        List<BookingEntity> result = guestDAOImpl.findBookingsByGuestId(guestId);
        assertNotNull(result);
        assertEquals(2, result.size());
        verify(typedQuery).setParameter("guestId", guestId);
        verify(typedQuery).getResultList();
    }
}
