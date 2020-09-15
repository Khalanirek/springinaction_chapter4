package training.springinaction_chapter4.tacos.data;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import training.springinaction_chapter4.tacos.Ingredient;

@Repository
public interface IngredientRepository extends CrudRepository<Ingredient, String> {

}
