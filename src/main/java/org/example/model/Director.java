package org.example.model;


import javax.persistence.*;
import java.util.List;

@Entity // помечает класс связанный с БД
@Table(name = "Director") // сопоставляем класс с таблицей
public class Director {


    @Id // аннотация первичного ключа
    @Column(name ="id") // сопоставляем поле класса с колонкой в таблице
    @GeneratedValue(strategy = GenerationType.IDENTITY) // колонка генерируется автоматически на стороне базы данных
    private int id;

    @Column (name ="name")
    private String name;

    @Column (name ="age")
    private int age;

    @OneToMany  (mappedBy = "directorMovie")// ссылка на поле в классе movie
    private List<Movie> movies; // лист с заказами


    public Director() {
    }

    public Director(String name, int age) {
        this.name = name;
        this.age = age;
    }



    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<Movie> getMovies() {
        return movies;
    }

    public void setMovies(List<Movie> movies) {
        this.movies = movies;
    }

    @Override
    public String toString() {
        return  name + "," + age;
    }
}
