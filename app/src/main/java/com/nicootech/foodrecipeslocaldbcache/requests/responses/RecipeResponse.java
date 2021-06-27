package com.nicootech.foodrecipeslocaldbcache.requests.responses;

import com.nicootech.foodrecipeslocaldbcache.models.Recipe;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import androidx.annotation.Nullable;

public class RecipeResponse {

    @SerializedName("recipe")
    @Expose()
    private Recipe recipe;

    @Nullable
    public Recipe getRecipe(){
        return recipe;
    }

    @Override
    public String toString() {
        return "RecipeResponse{" +
                "recipe=" + recipe +
                '}';
    }
}
