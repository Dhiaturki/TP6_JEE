package dao; 
 
import java.util.List; 
 
import metier.Film; 
 
public interface IFilmDao { 
public Film save(Film p); 
public List<Film> filmsParMC(String mc); 
public Film getFilm(Long id); 
public Film updateFilm(Film p); 
public void deleteFilm(Long id); 
} 