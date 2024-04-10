package controller;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import dao.MovieDao;
import dto.Movie;
@WebServlet("/insert-movie")
@MultipartConfig
public class Insertmovie extends HttpServlet {
	
	
@Override
protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	
	req.getRequestDispatcher("insert.html").forward(req, resp);
}

@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	String name=req.getParameter("name");
	String language =req.getParameter("language");
	double rating = 0;
	Part part=req.getPart("picture");
	String genre=req.getParameter("genre");
	try {
		rating = Double.parseDouble(req.getParameter("rating"));
		Movie movie=new Movie();
		movie.setGenre(genre);
		movie.setLanguage(language);
		movie.setName(name);
		movie.setRating(rating);
		
		byte [] image=new byte[part.getInputStream().available()];
		part.getInputStream().read(image);
		movie.setPicture(image);

		MovieDao dao = new MovieDao();
		dao.save(movie);
		resp.getWriter().print("<h1 align='center'>Movie added sucessfully!!!</h1>");
		req.getRequestDispatcher("home.html").include(req, resp);
	} catch (NumberFormatException e) {
		// TODO Auto-generated catch block
		resp.getWriter().print("<h1 align='center'>Enter numbers only</h1>");
		req.getRequestDispatcher("insert.html").include(req, resp);
	}
	
	}
}