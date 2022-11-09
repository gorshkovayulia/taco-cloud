package sia.tacocloud.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import sia.tacocloud.data.Ingredient;
import sia.tacocloud.repository.IngredientRepository;

/**
 * Contains endpoints to the Taco Cloud API to support ingredient management
 */
@RestController
@RequestMapping(path="/api/ingredients", produces="application/json")
@CrossOrigin(origins="http:/ /localhost:8090") // // Allows clients from localhost, port 8090, to access the API
public class IngredientController {
    private IngredientRepository repo;

    @Autowired
    public IngredientController(IngredientRepository repo) {
        this.repo = repo;
    }

    @GetMapping
    public Iterable<Ingredient> allIngredients() {
        return repo.findAll();
    }

    @PostMapping
    @PreAuthorize("#{hasRole('ADMIN')}")
    @ResponseStatus(HttpStatus.CREATED)
    public Ingredient saveIngredient(@RequestBody Ingredient ingredient) {
        return repo.save(ingredient);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("#{hasRole('ADMIN')}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteIngredient(@PathVariable("id") String ingredientId) {
        repo.deleteById(ingredientId);
    }
}
