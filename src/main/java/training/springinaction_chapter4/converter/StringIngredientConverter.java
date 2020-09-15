package training.springinaction_chapter4.converter;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import training.springinaction_chapter4.tacos.Ingredient;
import training.springinaction_chapter4.tacos.data.IngredientRepository;

@Component
public class StringIngredientConverter implements Converter<String, Ingredient> {

    private IngredientRepository ingredientRepository;

    public StringIngredientConverter(IngredientRepository ingredientRepository) {
        this.ingredientRepository = ingredientRepository;
    }

    @Override
    public Ingredient convert(String s) {
        return ingredientRepository.findById(s).orElse(null);
    }
}
