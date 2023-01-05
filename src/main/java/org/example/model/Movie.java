package org.example.model;


import javax.persistence.*;

@Entity // помечает класс связанный с БД
@Table(name = "Movie") // сопоставляем класс с таблицей
public class Movie {

    @Id // аннотация первичного ключа
    @Column(name ="id") // сопоставляем поле класса с колонкой в таблице
    @GeneratedValue(strategy = GenerationType.IDENTITY) // колонка генерируется автоматически на стороне базы данных
    private int id;

    @Column (name ="name")
    private String name;

    @Column (name ="year")
    private int year;

    @ManyToOne // отношение много к одному - у одного владельца мб много товаров
    @JoinColumn (name = "dir_id", referencedColumnName = "id") // для объединения таблиц по внешнему ключу
    private Director directorMovie; // поле - объект класса Person - владелец товара

    public Movie() {
    }

    public Movie(String name, int year, Director directorMovie) {
        this.name = name;
        this.year = year;
        this.directorMovie = directorMovie;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public Director getDirectorMovie() {
        return directorMovie;
    }

    public void setDirectorMovie(Director directorMovie) {
        this.directorMovie = directorMovie;
    }

    @Override
    public String toString() {
        return name + "'" + year;
    }

}
