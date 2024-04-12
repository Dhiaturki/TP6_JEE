package test;
import java.util.List;

import dao.FilmDaolmpl;
import metier.Film; 

public class testdao {

    public static void main(String[] args) {
    	FilmDaolmpl fdao = new FilmDaolmpl();

        Film film = fdao.save(new Film("Interstellar", 60));
        System.out.println(film);

        List<Film> films = fdao.filmsParMC("Shawshank"); 
        for (Film f : films)
            System.out.println(f);
    }
}
