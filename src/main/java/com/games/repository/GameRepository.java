package com.games.repository;

import com.games.model.Game;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GameRepository extends JpaRepository<Game, Long> {

    List<Game> findAllByOrderByTitleAsc();
    List<Game> findAllByOrderByTitleDesc();

    List<Game> findAllByOrderByDeveloperAsc();
    List<Game> findAllByOrderByDeveloperDesc();

    List<Game> findAllByOrderByYearAsc();
    List<Game> findAllByOrderByYearDesc();

    List<Game> findAllByOrderByGenreAsc();
    List<Game> findAllByOrderByGenreDesc();

    List<Game> findAllByOrderByRatingAsc();
    List<Game> findAllByOrderByRatingDesc();
}