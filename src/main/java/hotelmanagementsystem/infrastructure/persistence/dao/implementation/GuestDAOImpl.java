package hotelmanagementsystem.infrastructure.persistence.dao.implementation;

import hotelmanagementsystem.infrastructure.persistence.dao.interfaces.GuestDAO;
import hotelmanagementsystem.infrastructure.persistence.entities.BookingEntity;
import hotelmanagementsystem.infrastructure.persistence.entities.GuestEntity;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;

import java.util.List;

@ApplicationScoped
public class GuestDAOImpl implements GuestDAO {

    @Inject
    EntityManager em;

    @Override
    @Transactional
    public void create(GuestEntity guestEntity) {
        try {
            em.persist(guestEntity);
        } catch (Exception e) {
            e.printStackTrace();
            throw new DataAccessException("Error while creating guest entity", e);
        }
    }

    @Override
    @Transactional(Transactional.TxType.SUPPORTS)
    public GuestEntity read(long id) {
        try {
            return em.find(GuestEntity.class, id);
        } catch (Exception e) {
            e.printStackTrace();
            throw new DataAccessException("Error while reading guest entity!", e);
        }
    }

    @Override
    @Transactional
    public void update(GuestEntity guestEntity) {
        try {
            em.merge(guestEntity);
        } catch (Exception e) {
            e.printStackTrace();
            throw new DataAccessException("Error while updating guest entity", e);
        }
    }

    @Override
    @Transactional
    public void delete(GuestEntity guestEntity) {
        try {
            em.remove(em.contains(guestEntity) ? guestEntity : em.merge(guestEntity));
        } catch (Exception e) {
            e.printStackTrace();
            throw new DataAccessException("Error while deleting guest entity", e);
        }
    }

    @Override
    @Transactional(Transactional.TxType.SUPPORTS)
    public List<BookingEntity> findBookingsByGuestId(long guestId) {
        try {
            String jpql = "SELECT b FROM BookingEntity b JOIN b.guests g WHERE g.id = :guestId";
            TypedQuery<BookingEntity> query = em.createQuery(jpql, BookingEntity.class);
            query.setParameter("guestId", guestId);
            return query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
            throw new DataAccessException("Error retrieving bookings for guest ID: " + guestId, e);
        }
    }
}
