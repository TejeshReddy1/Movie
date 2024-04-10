package dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;

import dto.Movie;

public class MovieDao 
{
EntityManagerFactory factory=Persistence.createEntityManagerFactory("akshay");
EntityManager manager=factory.createEntityManager();
EntityTransaction transaction=manager.getTransaction();

public void Sava(Movie movie)

{
	transaction.begin();
	manager.persist(movie);
	transaction.commit();
}

public void Update(Movie movie)

{
	transaction.begin();
	manager.persist(movie);
	transaction.commit();
}
public List<Movie> fetchMovies ()

{
	return manager.createQuery("select x from Movie x").getResultList();
	}
}

 