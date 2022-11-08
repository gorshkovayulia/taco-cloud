package sia.tacocloud.controllers;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import sia.tacocloud.data.Ingredient;
import sia.tacocloud.data.Taco;
import sia.tacocloud.data.TacoOrder;
import sia.tacocloud.repository.IngredientRepository;
import sia.tacocloud.repository.TacoRepository;
import sia.tacocloud.repository.UserRepository;

import java.io.IOException;
import java.util.Arrays;
import java.util.Optional;

/**
 * Displays a list of the most recently created tacos on the website
 */
@RestController
@RequestMapping(path="/api/tacos", produces="application/json")
@CrossOrigin(origins="http://tacocloud:8080")
public class TacoController {
    private TacoRepository tacoRepo;

    public TacoController(TacoRepository tacoRepo) {
        this.tacoRepo = tacoRepo;
    }

    @GetMapping(params="recent")
    public Iterable<Taco> recentTacos() {
        PageRequest page = PageRequest.of(0, 12, Sort.by("createdAt").descending());
        return tacoRepo.findAll(page).getContent();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Taco> tacoById(@PathVariable("id") Long id) {
        Optional<Taco> optTaco = tacoRepo.findById(id);
        return optTaco.map(taco -> new ResponseEntity<>(taco, HttpStatus.OK)).
                orElseGet(() -> new ResponseEntity<>(null, HttpStatus.NOT_FOUND));
    }

    @PostMapping(consumes="application/json")
    @ResponseStatus(HttpStatus.CREATED)
    public Taco postTaco(@RequestBody Taco taco) {
        return tacoRepo.save(taco);
    }
}
