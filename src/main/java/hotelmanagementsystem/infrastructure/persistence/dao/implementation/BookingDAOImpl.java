package hotelmanagementsystem.infrastructure.persistence.dao.implementation;

import hotelmanagementsystem.infrastructure.persistence.dao.interfaces.BookingDAO;
import hotelmanagementsystem.infrastructure.persistence.entities.BookingEntity;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;

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
            throw new DataAccessException("Error while updating booking entity", e);
        }
    }
}
