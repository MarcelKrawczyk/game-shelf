package com.games.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;

@Entity
@Table(name = "games")
public class Game {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Title is required")
    @Column(nullable = false)
    private String title;

    @NotBlank(message = "Developer is required")
    @Column(nullable = false)
    private String developer;

    @Min(value = 1970, message = "Year must be greater than 1970")
    @Max(value = 2100, message = "Year must be less than 2100")
    @Column(name = "release_year")
    private int year;

    private String genre;

    @DecimalMin(value = "0.0", message = "Rating cannot be negative")
    @DecimalMax(value = "10.0", message = "Rating cannot exceed 10")
    private double rating;

    public Game() {}

    public Game(String title, String developer, int year, String genre, double rating) {
        this.title = title;
        this.developer = developer;
        this.year = year;
        this.genre = genre;
        this.rating = rating;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getDeveloper() { return developer; }
    public void setDeveloper(String developer) { this.developer = developer; }

    public int getYear() { return year; }
    public void setYear(int year) { this.year = year; }

    public String getGenre() { return genre; }
    public void setGenre(String genre) { this.genre = genre; }

    public double getRating() { return rating; }
    public void setRating(double rating) { this.rating = rating; }
}
