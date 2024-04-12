package web;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.catalina.connector.Response;
import dao.GenreDaoimp;
import dao.GenreDao;
import metier.Genre;

@WebServlet(name = "catServ", urlPatterns = { "/Genre", "/saisieGenre", "/saveGenre", "/supprimerGenre","/editerGenre", "/updateGenre" })

public class GenreServlet extends HttpServlet {
	GenreDao metier;

	@Override
	public void init() throws ServletException {
		metier = new GenreDaoimp();
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String path = request.getServletPath();
		System.out.println("PATH " + path);
		if (path.equals("/Genre")) {
			GenreModel model = new GenreModel();
			List<Genre> genre = metier.getAllGenres();
			model.setGenre(genre);
			request.setAttribute("model", model);
			request.getRequestDispatcher("Genre.jsp").forward(request, response);
		} else if (path.equals("/saisieGenre")) {
			request.getRequestDispatcher("saisieGenre.jsp").forward(request, response);
		} else if (path.equals("/saveGenre") &&

				request.getMethod().equals("POST"))

		{
			String nom = request.getParameter("nom");
			Genre cat = metier.save(new Genre(nom));
			request.setAttribute("Genre", cat);
			response.sendRedirect("Genre");
		} else if (path.equals("/supprimerGenre")) {
			Long id = Long.parseLong(request.getParameter("id"));
			metier.deleteGenre(id);
			response.sendRedirect("Genre");
		} else if (path.equals("/editerGenre")) {
			Long id = Long.parseLong(request.getParameter("id"));
			Genre cat = metier.getGenre(id);
			request.setAttribute("categorie", cat);
			request.getRequestDispatcher("editerGenre.jsp").forward(request, response);
		} else if (path.equals("/updateGenre")) {
			Long id = Long.parseLong(request.getParameter("id"));
			String nom = request.getParameter("nom");
			Genre cat = new Genre();
			cat.setIdCat(id);
			cat.setNomCat(nom);
			metier.updateGenre(cat);
			response.sendRedirect("Genre");
		} else {
			response.sendError(Response.SC_NOT_FOUND);
		}
	}

	@Override
	protected void doPost(HttpServletRequest request,

			HttpServletResponse response) throws

	ServletException, IOException {
		doGet(request, response);
	}
}