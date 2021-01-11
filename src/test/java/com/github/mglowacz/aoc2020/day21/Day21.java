package com.github.mglowacz.aoc2020.day21;

import com.github.mglowacz.aoc2020.FileInputSource;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

import static java.util.Arrays.asList;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class Day21 {
    static class FoodAllergens {
        List<String> ingredients;
        List<String> allergens;
    }

    @Test
    public void test() throws IOException {
        List<String> strings = FileInputSource.getStrings("/day21/test");
        List<FoodAllergens> foodAllergens = createFoodAllergensList(strings);

        assertEquals(5, getFreeCount(foodAllergens));
    }

    @Test
    public void data() throws IOException {
        List<String> strings = FileInputSource.getStrings("/day21/data");
        List<FoodAllergens> foodAllergens = createFoodAllergensList(strings);

        assertEquals(2203, getFreeCount(foodAllergens));
    }

    private int getFreeCount(List<FoodAllergens> foodAllergens) {
        Set<String> allAllergens = new HashSet<>();
        Set<String> allIngredients = new HashSet<>();
        foodAllergens.forEach(fa -> {
            allAllergens.addAll(fa.allergens);
            allIngredients.addAll(fa.ingredients);
        });

        Map<String, Set<String>> not = new HashMap<>();
        Map<String, Integer> ingredientsCount = new HashMap<>();
        for (FoodAllergens fa : foodAllergens) {

            for (String i : fa.ingredients) {
                ingredientsCount.put(i, ingredientsCount.getOrDefault(i, 0) + 1);
            }

            for (String a : fa.allergens)
                for (String i : allIngredients) {
                    if (!fa.ingredients.contains(i)) {
                        not.putIfAbsent(i, new HashSet<>());
                        not.get(i).add(a);
                    }
                }
        }

        AtomicInteger ans = new AtomicInteger(0);

        not.forEach((key, value) -> {
            if (value.size() == allAllergens.size()) {
                ans.addAndGet(ingredientsCount.get(key));
            }
        });

        return ans.get();
    }


    private List<FoodAllergens> createFoodAllergensList(List<String> strings) {
        List<FoodAllergens> foodAllergens = new LinkedList<>();

        for (String food : strings) {
            List<String> ingredients = new LinkedList<>(asList(food.split("\\(contains")[0].trim().split(" ")));
            List<String> allergens = Arrays.stream(food.split("\\(contains")[1].trim().replace(")", "").split(",")).map(String::trim).collect(Collectors.toList());
            FoodAllergens fa = new FoodAllergens();
            fa.ingredients = ingredients;
            fa.allergens = allergens;
            foodAllergens.add(fa);
        }
        return foodAllergens;
    }


    @Test
    public void test2() throws IOException {
        List<String> strings = FileInputSource.getStrings("/day21/test");
        List<FoodAllergens> foodAllergens = createFoodAllergensList(strings);
        assertEquals("mxmxvkd,sqjhc,fvjkl", getCanonicalDangerousIngredientList(foodAllergens));
    }


    @Test
    public void data2() throws IOException {
        List<String> strings = FileInputSource.getStrings("/day21/data");
        List<FoodAllergens> foodAllergens = createFoodAllergensList(strings);
        assertEquals("fqfm,kxjttzg,ldm,mnzbc,zjmdst,ndvrq,fkjmz,kjkrm", getCanonicalDangerousIngredientList(foodAllergens));
    }

    private String getCanonicalDangerousIngredientList(List<FoodAllergens> foodAllergens) {
        Set<String> allAllergens = new HashSet<>();
        Set<String> allIngredients = new HashSet<>();
        foodAllergens.forEach(fa -> {
            allAllergens.addAll(fa.allergens);
            allIngredients.addAll(fa.ingredients);
        });

        Map<String, Set<String>> not = new HashMap<>();
        Map<String, Integer> ingredientsCount = new HashMap<>();
        for (FoodAllergens fa : foodAllergens) {

            for (String i : fa.ingredients) {
                ingredientsCount.put(i, ingredientsCount.getOrDefault(i, 0) + 1);
            }

            for (String a : fa.allergens)
                for (String i : allIngredients) {
                    if (!fa.ingredients.contains(i)) {
                        not.putIfAbsent(i, new HashSet<>());
                        not.get(i).add(a);
                    }
                }
        }


        Map<String, String> allergens = new HashMap<>();
        Map<String, Set<String>> collect = not.entrySet().stream().filter(n -> n.getValue().size() < allAllergens.size()).collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));

        while (!collect.isEmpty()) {
            collect.entrySet().stream().filter(s -> allAllergens.size() - 1 == s.getValue().size()).forEach(s -> {
                String allergen = allAllergens.stream().filter(s2 -> !s.getValue().contains(s2)).collect(Collectors.toList()).get(0);
                allergens.put(allergen, s.getKey());
            });

            allergens.forEach((key, value) -> {
                collect.remove(value);
                collect.forEach((key1, value1) -> value1.add(key));
            });
        }
        List<String> allergenList = allergens.keySet().stream().sorted().collect(Collectors.toList());
        return allergenList.stream().map(allergens::get).collect(Collectors.joining(","));
    }
}

