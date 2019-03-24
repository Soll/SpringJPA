package com.art.tacocloud.controllers;

import com.art.tacocloud.data.IngredientRepository;
import com.art.tacocloud.data.TacoRepository;
import com.art.tacocloud.taco.Ingredient;
import com.art.tacocloud.taco.Ingredient.Type;
import com.art.tacocloud.taco.Order;
import com.art.tacocloud.taco.Taco;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Controller
@RequestMapping("/design")
@SessionAttributes("order")
public class DesignTacoController {

    private IngredientRepository ingredientRepo;
    private TacoRepository designRepo;

    @ModelAttribute(name = "order")
    public Order order() {
        return new Order();
    }

    @ModelAttribute(name = "taco")
    public Taco taco() {
        return new Taco();
    }

    public DesignTacoController(IngredientRepository ingredientRepo, TacoRepository designRepo) {
        this.ingredientRepo = ingredientRepo;
        this.designRepo = designRepo;
    }

    @GetMapping
    public String showDesignForm(Model model) {
        Iterable<Ingredient> ingredients = ingredientRepo.findAll();

                /*Arrays.asList(
                new Ingredient("FLTO", "Flour Tortilla", Type.WRAP),
                new Ingredient("COTO", "Corn Tortilla", Type.WRAP),
                new Ingredient("GRBF", "Ground Beef", Type.PROTEIN),
                new Ingredient("CARN", "Carnitas", Type.PROTEIN),
                new Ingredient("TMTO", "Diced Tomatoes", Type.VEGGIES),
                new Ingredient("LETC", "Lettuce", Type.VEGGIES),
                new Ingredient("CHED", "Cheddar", Type.CHEESE),
                new Ingredient("JACK", "Monterrey Jack", Type.CHEESE),
                new Ingredient("SLSA", "Salsa", Type.SAUCE),
                new Ingredient("SRCR", "Sour Cream", Type.SAUCE)
        );*/

        Type[] types = Ingredient.Type.values();

        for (Type type : types)
            model.addAttribute(type.toString().toLowerCase(), filterByType(ingredients, type));

        model.addAttribute("design", new Taco());
        return "design";
    }

    @PostMapping
    public String processDesign(Order order, @Valid Taco design, Errors errors) {

        if (errors.hasErrors())
            return "design";

        Taco saved = designRepo.save(design);
        order.addDesign(saved);
        log.info("Processing design:" + design);
        return "redirect:/orders/current";
    }

    private List<Ingredient> filterByType(Iterable<Ingredient> ingredients, Type type) {

        List<Ingredient> result = new ArrayList<>();

        for (Ingredient ingredient : ingredients)
            if (ingredient.getType() == type)
                result.add(ingredient);

        return result;

    }
}
