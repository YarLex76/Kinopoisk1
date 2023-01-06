package org.example.app;

import org.example.model.Director;
import org.example.model.Movie;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CrudApp {

    public static void viewTheDirector(int id) {
        new HibernateContext().runUnContext((session) -> {
            Director director = session.get(Director.class, id); // получаем режиссера по id
            List<Movie> movies = director.getMovies(); // кладем его фильмы в список
            System.out.println("Режиссер: " + director); // выводим на экран директора
            System.out.println("Список фильмов: " + movies); // выводим на экран список заказов
            System.out.println();

        });
    }

    public static void viewTheMovie(int id) {

        new HibernateContext().runUnContext((session) -> {
            Movie movie = session.get(Movie.class, id); // получаем фильм по id

            System.out.println("Фильм: " + movie); // выводим фильм на экран
            Director director = movie.getDirectorMovie(); // получаем режиссера
            System.out.println("Режиссер: " + director);
            System.out.println();
        });
    }

    public static void addMovie(int id, String movieName, int age) {

        new HibernateContext().runUnContext((session) -> {

            Director director = session.get(Director.class, id); // получили режиссера нового фильма по id
            Movie movie = new Movie(movieName, age, director);


            System.out.println("Фильм: " + movie); // выводим фильм на экран
            System.out.println("Режиссер: " + director);
            System.out.println();

            session.save(movie); // сохраняем в таблицу
            director.getMovies().add(movie); // для кеша, можно не делать
        });
    }

    public static void addNewDirectorAndAddNewMovie(String name, int age, String movieName, int year) {

        new HibernateContext().runUnContext((session) -> {

            Director director = new Director(name, age);
            Movie movie = new Movie(movieName, year, director);

            director.setMovies(new ArrayList<>(Collections.singletonList(movie)));  // для кеша, можно не делать

            List<Movie> movies = director.getMovies();

            session.save(director);
            session.save(movie); // сохраняем в таблицу

            //System.out.println("Фильм: " + movies); // выводим фильм на экран
            System.out.println("Режиссер: " + director);
            System.out.println();
        });
    }

    public static void changeTheDirectorOfMovie(int idMovie, int idDirector) {

        new HibernateContext().runUnContext((session) -> {

            Movie movie = session.get(Movie.class, idMovie); // получаем фильм по id
            Director director = session.get(Director.class, idDirector); // получаем фильм по id

            System.out.println("Фильм: " + movie); // выводим фильм на экран
            movie.setDirectorMovie(director);

            movie.getDirectorMovie().getMovies().remove(movie); // удаляем фильм у старого режиссера
            director.getMovies().add(movie);                    // для кеша

            System.out.println("Режиссер: " + director);
            System.out.println();

            session.save(movie);
        });
    }

    public static void removeMovie(int idMovie) {

        new HibernateContext().runUnContext((session) -> {
            Movie movie = session.get(Movie.class, idMovie); // получаем фильм по id
            System.out.println("Фильм: " + movie); // выводим фильм на экран

            Director director = session.get(Director.class, idMovie); // получаем режиссера по id
            List<Movie> movies = director.getMovies();

            String filmNane = movie.getName();

         /*   for (Movie movie1: movies){
                if (movie1.getName().equals(movie.getName())){
                    session.remove(movie1);
                }
            }*/

            session.remove(movie);
        });
    }
}
