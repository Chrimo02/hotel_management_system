package hotelmanagementsystem.infrastructure.persistence.dao.implementation;

import hotelmanagementsystem.infrastructure.persistence.dao.interfaces.HotelLocationDAO;
import hotelmanagementsystem.infrastructure.persistence.entities.HotelLocationEntity;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;

import java.util.List;

@ApplicationScoped
public class HotelLocationDAOImpl implements HotelLocationDAO {

    @Inject
    EntityManager em;

    @Override
    @Transactional
    public void create(HotelLocationEntity hotelLocation) {
        try {
            em.persist(hotelLocation);
        } catch (Exception e) {
            throw new DataAccessException("Error while creating HotelLocation entity", e);
        }
    }

    @Override
    @Transactional(Transactional.TxType.SUPPORTS)
    public HotelLocationEntity read(long id) {
        try {
            return em.find(HotelLocationEntity.class, id);
        } catch (Exception e) {
            throw new DataAccessException("Error while reading HotelLocation entity!", e);
        }
    }

    @Override
    @Transactional(Transactional.TxType.SUPPORTS)
    public List<HotelLocationEntity> readAll() {
        try {
            return em.createQuery("SELECT h FROM HotelLocationEntity h", HotelLocationEntity.class)
                    .getResultList();
        } catch (Exception e) {
            throw new DataAccessException("Error while reading all HotelLocation entities!", e);
        }
    }

    @Override
    @Transactional
    public void update(HotelLocationEntity hotelLocation) {
        try {
            em.merge(hotelLocation);
        } catch (Exception e) {
            throw new DataAccessException("Error while updating HotelLocation entity", e);
        }
    }

    @Override
    @Transactional
    public void delete(HotelLocationEntity hotelLocation) {
        try {
            em.remove(em.contains(hotelLocation) ? hotelLocation : em.merge(hotelLocation));
        } catch (Exception e) {
            throw new DataAccessException("Error while deleting HotelLocation entity", e);
        }
    }

    @Override
    @Transactional(Transactional.TxType.SUPPORTS)
    public HotelLocationEntity findLocationByHotelId(long hotelId) {
        try {
            TypedQuery<HotelLocationEntity> query = em.createQuery(
                    "SELECT h.location FROM HotelEntity h WHERE h.id = :hotelId",
                    HotelLocationEntity.class
            );
            query.setParameter("hotelId", hotelId);
            return query.getSingleResult();
        } catch (NoResultException e) {
            throw new IllegalArgumentException("No location found for Hotel ID: " + hotelId, e);
        } catch (Exception e) {
            throw new DataAccessException("Error while finding location by Hotel ID", e);
        }
    }
}
