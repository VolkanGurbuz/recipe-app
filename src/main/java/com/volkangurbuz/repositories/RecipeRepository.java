package com.volkangurbuz.repositories;

import com.volkangurbuz.domain.Recipe;
import org.springframework.data.repository.CrudRepository;

public interface RecipeRepository extends CrudRepository<Recipe, Long> {}
