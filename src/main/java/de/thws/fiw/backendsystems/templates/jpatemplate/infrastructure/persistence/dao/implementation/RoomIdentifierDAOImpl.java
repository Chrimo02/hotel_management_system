package de.thws.fiw.backendsystems.templates.jpatemplate.infrastructure.persistence.dao.implementation;

import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class RoomIdentifierDAOImpl implements RoomIdentifierDAO {
    EntityManagerFactory emf;
    public RoomIdentifierDAOImpl() {
        emf = Persistence.createEntityManagerFactory("my-persistence-unit");
    }
    @Override
    public void create(RoomIdentifierEntity entity) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(entity);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            e.printStackTrace();
        } finally {
            em.close();
        }
    }
}
