package hotelmanagementsystem.infrastructure.persistence.dao.implementation;

import hotelmanagementsystem.infrastructure.persistence.dao.interfaces.BookingDAO;
import hotelmanagementsystem.infrastructure.persistence.entities.BookingEntity;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;

import java.time.LocalDate;
import java.util.List;

@ApplicationScoped
public class BookingDAOImpl implements BookingDAO {

    @Inject
    EntityManager em;  // In Quarkus per @Inject statt selbst gebautes JpaUtil

    /**
     * Persistiert ein neues BookingEntity.
     * Dank @Transactional muss nicht manuell getTransaction().begin/commit aufgerufen werden.
     */
    @Override
    @Transactional
    public BookingEntity create(BookingEntity bookingEntity) {
        try {
            em.persist(bookingEntity);
            return bookingEntity;
        } catch (Exception e) {
            e.printStackTrace();
            throw new DataAccessException("Error while creating booking entity", e);
        }
    }

    /**
     * Liest ein BookingEntity per ID. Für einfache Lesevorgänge können wir
     * entweder @Transactional(Transactional.TxType.SUPPORTS) verwenden oder
     * (wie hier) transaktionslos belassen – Quarkus öffnet dann eine
     * Lese-Transaktion.
     */
    @Override
    @Transactional(Transactional.TxType.SUPPORTS)
    public BookingEntity read(long id) {
        try {
            return em.find(BookingEntity.class, id);
        } catch (Exception e) {
            e.printStackTrace();
            throw new DataAccessException("Error while reading booking entity!", e);
        }
    }

    /**
     * Updatet ein bestehendes BookingEntity.
     */
    @Override
    @Transactional
    public void update(BookingEntity bookingEntity) {
        try {
            em.merge(bookingEntity);
        } catch (Exception e) {
            e.printStackTrace();
            throw new DataAccessException("Error while updating booking entity", e);
        }
    }

    @Override
    @Transactional(Transactional.TxType.SUPPORTS)
    public List<BookingEntity> findBookingsByCheckInDate(LocalDate checkInDate) {
        try {
            TypedQuery<BookingEntity> query = em.createQuery(
                    "SELECT b FROM BookingEntity b WHERE b.checkInDate = :checkInDate AND b.status = true",
                    BookingEntity.class
            );
            query.setParameter("checkInDate", checkInDate);
            return query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Error retrieving bookings by check-in date", e);
        }
    }
}
