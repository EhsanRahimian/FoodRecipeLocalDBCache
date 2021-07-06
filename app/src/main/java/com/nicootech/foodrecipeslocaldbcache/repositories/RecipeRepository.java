package com.nicootech.foodrecipeslocaldbcache.repositories;

import android.content.Context;

import com.nicootech.foodrecipeslocaldbcache.AppExecutors;
import com.nicootech.foodrecipeslocaldbcache.models.Recipe;
import com.nicootech.foodrecipeslocaldbcache.persistence.RecipeDao;
import com.nicootech.foodrecipeslocaldbcache.persistence.RecipeDatabase;
import com.nicootech.foodrecipeslocaldbcache.requests.responses.ApiResponse;
import com.nicootech.foodrecipeslocaldbcache.requests.responses.RecipeSearchResponse;
import com.nicootech.foodrecipeslocaldbcache.util.NetworkBoundResource;
import com.nicootech.foodrecipeslocaldbcache.util.Resource;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.LiveData;

public class RecipeRepository {

    private static RecipeRepository instance;
    private RecipeDao recipeDao;

    public static RecipeRepository getInstance(Context context){
        if(instance == null){
            instance = new RecipeRepository(context);
        }
        return instance;
    }


    private RecipeRepository(Context context) {
        recipeDao = RecipeDatabase.getInstance(context).getRecipeDao();
    }

    public LiveData<Resource<List<Recipe>>> searchRecipesApi(final String query, final int pageNumber){
        return new NetworkBoundResource<List<Recipe>, RecipeSearchResponse>(AppExecutors.getInstance() ){

            @Override
            public void saveCallResult(@NonNull RecipeSearchResponse item) {

            }

            @Override
            public boolean shouldFetch(@Nullable List<Recipe> data) {
                return true; // always query the network since the queries can be anything
            }

            @NonNull
            @Override
            public LiveData<List<Recipe>> loadFromDb() {
                return recipeDao.searchRecipes(query, pageNumber);
            }

            @NonNull
            @Override
            public LiveData<ApiResponse<RecipeSearchResponse>> createCall() {
                return null;
            }

        }.getAsLiveData();
    }
}
