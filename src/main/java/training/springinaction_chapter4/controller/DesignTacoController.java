package training.springinaction_chapter4.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import training.springinaction_chapter4.tacos.Ingredient;
import training.springinaction_chapter4.tacos.Order;
import training.springinaction_chapter4.tacos.Taco;
import training.springinaction_chapter4.tacos.data.IngredientRepository;
import training.springinaction_chapter4.tacos.data.TacoRepository;

import javax.validation.Valid;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Controller
@RequestMapping("/design")
@SessionAttributes("order")
public class DesignTacoController {
    private final IngredientRepository ingredientRepository;
    private final TacoRepository tacoRepository;

    @Autowired
    public DesignTacoController(IngredientRepository ingredientRepository, TacoRepository tacoRepository) {
        this.ingredientRepository = ingredientRepository;
        this.tacoRepository = tacoRepository;
    }

    @ModelAttribute(name = "order")
    public Order order(){
        return new Order();
    }

    @GetMapping()
    public String showDesignForm(Model model) {
        List<Ingredient> ingredients = Arrays.asList(
                new Ingredient("FLTO", "pszenna", Ingredient.Type.WRAP),
                new Ingredient("COTO", "kukurydziana", Ingredient.Type.WRAP),
                new Ingredient("GRBF", "mielona wołowina", Ingredient.Type.PROTEIN),
                new Ingredient("CARN", "kawałki mięsa", Ingredient.Type.PROTEIN),
                new Ingredient("TMTO", "pomidory pokrojone w kostkę", Ingredient.Type.VEGGIES),
                new Ingredient("LETC", "sałata", Ingredient.Type.VEGGIES),
                new Ingredient("CHED", "cheddar", Ingredient.Type.CHEESE),
                new Ingredient("JACK", "Monterey Jack", Ingredient.Type.CHEESE),
                new Ingredient("SLSA", "pikantny sos pomidorowy", Ingredient.Type.SAUCE),
                new Ingredient("SRCR", "śmietana", Ingredient.Type.SAUCE)
        );

        Ingredient.Type[] types = Ingredient.Type.values();
        for (Ingredient.Type type : types) {
            model.addAttribute(type.toString().toLowerCase(), filterByType(ingredients, type));
        }
        model.addAttribute("taco", new Taco());
        return "design";
    }

    @PostMapping
    public String processTaco(@Valid Taco taco, Errors errors, @ModelAttribute Order order) {
        if (errors.hasErrors()){
            return "design";
        }
        Taco savedTaco = tacoRepository.save(taco);
        order.addTaco(savedTaco);

        log.info("Processing taco project: " + taco);
        return "redirect:/orders/current";
    }

    private List<Ingredient> filterByType(List<Ingredient> ingredients, Ingredient.Type type) {
        return ingredients.stream().filter(ingredient -> ingredient.getType().equals(type)).collect(Collectors.toList());
    }
}
