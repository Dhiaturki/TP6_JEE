package web;

import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import dao.IFilmDao;
import dao.FilmDaolmpl;
import dao.GenreDaoimp;
import metier.Film;
import metier.Genre;

@WebServlet(name = "cs", urlPatterns = { "/controleur", "*.do" })
public class ControleurServlet extends HttpServlet {

	IFilmDao metier;
	GenreDaoimp metierGenre;

	@Override
	public void init() throws ServletException {
		metier = new FilmDaolmpl();
		metierGenre = new GenreDaoimp();
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String path = request.getServletPath();
		if (path.equals("/index.do")) {
			request.getRequestDispatcher("Films.jsp").forward(request, response);
		} else if (path.equals("/chercher.do")) {
			String motCle = request.getParameter("motCle");
			FilmModele model = new FilmModele();
			model.setMotCle(motCle);
			List<Film> films = metier.filmsParMC(motCle);
			model.setFilm(films);
			request.setAttribute("model", model);
			request.getRequestDispatcher("Films.jsp").forward(request, response);
		} else if (path.equals("/saisie.do")) {
			List<Genre> cats = metierGenre.getAllGenres();
			GenreModel model = new GenreModel();
			model.setGenre(cats);
			request.setAttribute("catModel", model);
			request.getRequestDispatcher("saisieFilm.jsp").forward(request, response);
		} else if (path.equals("/save.do") && request.getMethod().equals("POST")) {
			String nom = request.getParameter("nom");
			Long categorieId=Long.parseLong(request.getParameter("genre"));
			double prix = Double.parseDouble(request.getParameter("prix"));
			Genre cat = metierGenre.getGenre(categorieId);
			Film f = metier.save(new Film(nom, prix,cat));
			request.setAttribute("film", f);
			request.getRequestDispatcher("confirmation.jsp").forward(request, response);
		} else if (path.equals("/supprimer.do")) {
			Long id = Long.parseLong(request.getParameter("id"));
			metier.deleteFilm(id);
			response.sendRedirect("chercher.do?motCle=");
		} else if (path.equals("/editer.do")) {
			Long id = Long.parseLong(request.getParameter("id"));
			Film f = metier.getFilm(id);
			request.setAttribute("film", f);
			List<Genre> cats = metierGenre.getAllGenres();
			GenreModel model = new GenreModel();
			model.setGenre(cats);
			request.setAttribute("catModel", model);
			request.getRequestDispatcher("editerFilm.jsp").forward(request, response);
		} else if (path.equals("/update.do")) {
			Long id = Long.parseLong(request.getParameter("id"));
			String nom = request.getParameter("nom");
			double prix = Double.parseDouble(request.getParameter("prix"));
			Long categorieId=Long.parseLong(request.getParameter("genre"));
			Film f = new Film();
			f.setIdFilm(id);
			f.setNomFilm(nom);
			f.setPrix(prix);
			Genre cat = metierGenre.getGenre(categorieId);
			f.setGenre(cat);
			metier.updateFilm(f);
			request.setAttribute("film", f);
			request.getRequestDispatcher("confirmation.jsp").forward(request, response);
		} else {
			response.sendError(HttpServletResponse.SC_NOT_FOUND);
		}
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}
}
