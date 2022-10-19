package sia.tacocloud.controllers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import sia.tacocloud.data.Ingredient;
import sia.tacocloud.data.Ingredient.Type;
import sia.tacocloud.data.Taco;
import sia.tacocloud.data.TacoOrder;
import sia.tacocloud.repository.IngredientRepository;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Hand off the request and the ingredient data to a view template to be rendered
 * as HTML and sent to the requesting web browser
 */
@Slf4j
@Controller
@RequestMapping("/design")
// TacoOrder object that is put into the model a little later in the class should be maintained in session -->
@SessionAttributes("tacoOrder")
public class DesignTacoController {

    private final IngredientRepository ingredientRepo;

    @Autowired
    public DesignTacoController(IngredientRepository ingredientRepo) {
        this.ingredientRepo = ingredientRepo;
    }

    /**
     * Creates the list of ingredients
     */
    @ModelAttribute
    public void addIngredientsToModel(Model model) {
        Iterable<Ingredient> ingredients = ingredientRepo.findAll();
        Type[] types = Ingredient.Type.values();
        for (Type type : types) {
            model.addAttribute(type.toString().toLowerCase(), filterByType((List<Ingredient>) ingredients, type));
        }
    }

    /**
     * TacoOrder object saves the state of order during assembling order while user selects ingredients for taco by several requests
     */
    @ModelAttribute(name = "tacoOrder")
    public TacoOrder order() {
        return new TacoOrder();
    }

    /**
     * In order View has an object for displaying
     */
    @ModelAttribute(name = "taco")
    public Taco taco() {
        return new Taco();
    }

    @GetMapping
    public String showDesignForm() {
        return "design";
    }

    // The @ModelAttribute applied to the Taco-Order parameter indicates that it should use the TacoOrder object
    // that was placed into the model via the @ModelAttribute-annotated order() method
    @PostMapping
    public String processTaco(@Valid Taco taco, Errors errors, @ModelAttribute TacoOrder tacoOrder) {
        if (errors.hasErrors()) {
            return "design";
        }
        tacoOrder.addTaco(taco);
        log.info("Processing taco: {}", taco);
        return "redirect:/orders/current";
    }

    private Iterable<Ingredient> filterByType(List<Ingredient> ingredients, Type type) {
        return ingredients
                .stream()
                .filter(x -> x.getType().equals(type))
                .collect(Collectors.toList());
    }
}
