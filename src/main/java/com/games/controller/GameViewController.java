package com.games.controller;

import com.games.model.Game;
import com.games.service.GameService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/games")
public class GameViewController {

    @Autowired
    private GameService gameService;

    @GetMapping
    public String listGames(@RequestParam(required = false) String sort, @RequestParam(required = false) String order, Model model) {
        List<Game> games = gameService.getAllGames(sort, order);
        model.addAttribute("games", games);
        model.addAttribute("sort", sort);
        model.addAttribute("order", order);
        return "index";
    }

    @GetMapping("/new")
    public String showAddForm(Model model) {
        model.addAttribute("game", new Game());model.addAttribute("formTitle", "Add Game");model.addAttribute("action", "/games");
        return "form";
    }

    @PostMapping
    public String addGame(@Valid @ModelAttribute Game game, BindingResult result, Model model, RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            model.addAttribute("formTitle", "Add Game");
            model.addAttribute("action", "/games");
            return "form";
        }
        gameService.saveGame(game);
        redirectAttributes.addFlashAttribute("success", "Game \"" + game.getTitle() + "\" has been added!");
        return "redirect:/games";
    }

    @GetMapping("/{id}/edit")
    public String showEditForm(@PathVariable Long id, Model model) {
        Optional<Game> game = gameService.getGameById(id);
        if (game.isEmpty()) {
            return "redirect:/games";
        }
        model.addAttribute("game", game.get());
        model.addAttribute("formTitle", "Edit Game");
        model.addAttribute("action", "/games/" + id + "/update");
        return "form";
    }

    @PostMapping("/{id}/update")
    public String updateGame(@PathVariable Long id, @Valid @ModelAttribute Game game, BindingResult result, Model model, RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            model.addAttribute("formTitle", "Edit Game");
            model.addAttribute("action", "/games/" + id + "/update");
            return "form";
        }
        gameService.updateGame(id, game);
        redirectAttributes.addFlashAttribute("success", "Game has been updated!");
        return "redirect:/games";
    }

    @GetMapping("/{id}/delete")
    public String deleteGame(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        Optional<Game> game = gameService.getGameById(id);
        if (game.isPresent()) {
            gameService.deleteGame(id);
            redirectAttributes.addFlashAttribute("success", "Game \"" + game.get().getTitle() + "\" has been deleted.");
        }
        return "redirect:/games";
    }
}