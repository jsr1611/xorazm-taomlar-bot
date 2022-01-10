package service;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import model.Category;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class StoreDataToDbFromJson {
    public static void store(){
        Gson gson = new Gson();

        List<Category> categoryList = new ArrayList<>();
        try(BufferedReader bufferedReader = new BufferedReader(new FileReader(new File("src/main/resources/categories.json")))){
            categoryList = gson.fromJson(bufferedReader, new TypeToken<List<Category>>(){
            }.getType());
        }catch (IOException e){
            e.printStackTrace();
        }
        System.out.println(categoryList);
    }
}
