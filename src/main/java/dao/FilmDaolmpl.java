package dao;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import metier.Film;
import util.JPAutil;

public class FilmDaolmpl implements IFilmDao {

    private EntityManager entityManager = JPAutil.getEntityManager("TP5_JEE_my_project");

    @Override
    public Film save(Film f) {
        EntityTransaction tx = entityManager.getTransaction();
        tx.begin();
        entityManager.persist(f);
        tx.commit();
        return f;
    }

    @Override
    public List<Film> filmsParMC(String mc) {
        List<Film> films = entityManager.createQuery("SELECT f FROM Film f WHERE f.nomFilm LIKE :mc").setParameter("mc", "%" + mc + "%").getResultList();
        return films;
    }

    @Override
    public Film getFilm(Long id) {
        return entityManager.find(Film.class, id);
    }

    @Override
    public Film updateFilm(Film f) {
        EntityTransaction tx = entityManager.getTransaction();
        tx.begin();
        entityManager.merge(f);
        tx.commit();
        return f;
    }

    @Override
    public void deleteFilm(Long id) {
        Film film = entityManager.find(Film.class, id);
        EntityTransaction tx = entityManager.getTransaction();
        tx.begin();
        entityManager.remove(film);
        tx.commit();
    }
}
