package dao;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import metier.Genre;
import util.JPAutil;

public class GenreDaoimp implements GenreDao{
	private EntityManager entityManager=JPAutil.getEntityManager("TP5_JEE_my_project");
	public Genre save(Genre gen) {
	EntityTransaction tx = entityManager.getTransaction();
	tx.begin();
	entityManager.persist(gen);
	tx.commit();
	return gen;
	}
	@Override
	public Genre getGenre(Long id) {
	return entityManager.find(Genre.class, id);
	}
	@Override
	public Genre updateGenre(Genre gen) {
	EntityTransaction tx = entityManager.getTransaction();
	tx.begin();
	entityManager.merge(gen);
	tx.commit();
	return gen;
	}
	@Override
	public void deleteGenre(Long id) {
		Genre genre = entityManager.find(Genre.class, id);
	entityManager.getTransaction().begin();
	entityManager.remove(genre);
	entityManager.getTransaction().commit();
	}
	@Override
	public List<Genre> getAllGenres() {
	    List<Genre> genres = entityManager.createQuery("SELECT g FROM Genre g", Genre.class).getResultList();
	    return genres;
	}


	}

