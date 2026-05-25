package com.games.controller;

import com.games.model.Game;
import com.games.service.GameService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/data")
@CrossOrigin(origins = "*")
public class GameApiController {

    @Autowired
    private GameService gameService;

    @GetMapping
    public ResponseEntity<List<Game>> getAllGames(
            @RequestParam(required = false) String sort,
            @RequestParam(required = false) String order) {
        List<Game> games = gameService.getAllGames(sort, order);
        return ResponseEntity.ok(games);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getGameById(@PathVariable Long id) {
        Optional<Game> game = gameService.getGameById(id);
        if (game.isPresent()) {
            return ResponseEntity.ok(game.get());
        }
        Map<String, String> error = new HashMap<>();
        error.put("error", "Game with id " + id + " not found");
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
    }

    @PostMapping
    public ResponseEntity<?> createGame(@Valid @RequestBody Game game) {
        Game saved = gameService.saveGame(game);
        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateGame(@PathVariable Long id, @Valid @RequestBody Game game) {
        Game updated = gameService.updateGame(id, game);
        if (updated == null) {
            Map<String, String> error = new HashMap<>();
            error.put("error", "Game with id " + id + " not found");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
        }
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteGame(@PathVariable Long id) {
        boolean deleted = gameService.deleteGame(id);
        if (!deleted) {
            Map<String, String> error = new HashMap<>();
            error.put("error", "Game with id " + id + " not found");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
        }
        Map<String, String> response = new HashMap<>();
        response.put("message", "Game deleted successfully");
        return ResponseEntity.ok(response);
    }
}