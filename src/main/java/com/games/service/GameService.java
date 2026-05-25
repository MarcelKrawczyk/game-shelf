package com.games.service;

import com.games.model.Game;
import com.games.repository.GameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class GameService {

    @Autowired
    private GameRepository gameRepository;

    public GameService(GameRepository gameRepository) {
        this.gameRepository = gameRepository;
        loadSampleData();
    }

    public List<Game> getAllGames(String sortBy, String order) {
        boolean ascending = !"desc".equalsIgnoreCase(order);

        if (sortBy == null) {
            return gameRepository.findAll();
        }

        if (sortBy.equals("title")) {
            if (ascending) return gameRepository.findAllByOrderByTitleAsc();
            else return gameRepository.findAllByOrderByTitleDesc();
        } else if (sortBy.equals("developer")) {
            if (ascending) return gameRepository.findAllByOrderByDeveloperAsc();
            else return gameRepository.findAllByOrderByDeveloperDesc();
        } else if (sortBy.equals("year")) {
            if (ascending) return gameRepository.findAllByOrderByYearAsc();
            else return gameRepository.findAllByOrderByYearDesc();
        } else if (sortBy.equals("genre")) {
            if (ascending) return gameRepository.findAllByOrderByGenreAsc();
            else return gameRepository.findAllByOrderByGenreDesc();
        } else if (sortBy.equals("rating")) {
            if (ascending) return gameRepository.findAllByOrderByRatingAsc();
            else return gameRepository.findAllByOrderByRatingDesc();
        }

        return gameRepository.findAll();
    }

    public Optional<Game> getGameById(Long id) {
        return gameRepository.findById(id);
    }

    public Game saveGame(Game game) {
        return gameRepository.save(game);
    }

    public Game updateGame(Long id, Game updatedGame) {
        Game game = gameRepository.findById(id).orElse(null);
        if (game == null) {
            return null;
        }
        game.setTitle(updatedGame.getTitle());
        game.setDeveloper(updatedGame.getDeveloper());
        game.setYear(updatedGame.getYear());
        game.setGenre(updatedGame.getGenre());
        game.setRating(updatedGame.getRating());
        return gameRepository.save(game);
    }

    public boolean deleteGame(Long id) {
        if (!gameRepository.existsById(id)) {
            return false;
        }
        gameRepository.deleteById(id);
        return true;
    }

    private void loadSampleData() {
        if (gameRepository.count() == 0) {
            gameRepository.save(new Game("The Witcher 3: Wild Hunt", "CD Projekt Red", 2015, "RPG", 9.8));
            gameRepository.save(new Game("Grand Theft Auto V", "Rockstar Games", 2013, "Action", 9.5));
            gameRepository.save(new Game("Minecraft", "Mojang", 2011, "Sandbox", 9.0));
            gameRepository.save(new Game("Red Dead Redemption 2", "Rockstar Games", 2018, "Action", 9.7));
            gameRepository.save(new Game("Cyberpunk 2077", "CD Projekt Red", 2020, "RPG", 8.5));
            gameRepository.save(new Game("Dark Souls III", "FromSoftware", 2016, "Action RPG", 9.1));
            gameRepository.save(new Game("Portal 2", "Valve", 2011, "Puzzle", 9.6));
        }
    }
}