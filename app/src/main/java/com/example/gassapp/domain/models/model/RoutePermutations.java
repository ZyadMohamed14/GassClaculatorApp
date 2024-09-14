package com.example.gassapp.domain.models.model;

import java.util.ArrayList;
import java.util.List;

public class RoutePermutations {

    public static List<List<String>> generateRoutes(List<String> locations, String startLocation) {
        List<List<String>> result = new ArrayList<>();
        List<String> tempList = new ArrayList<>(locations);
        tempList.remove(startLocation);  // Start location will be fixed at the beginning
        generatePermutations(tempList, 0, result, startLocation);
        return result;
    }

    private static void generatePermutations(List<String> list, int index, List<List<String>> result, String startLocation) {
        if (index == list.size()) {
            List<String> route = new ArrayList<>();
            route.add(startLocation);
            route.addAll(list);
            result.add(route);
        } else {
            for (int i = index; i < list.size(); i++) {
                swap(list, index, i);
                generatePermutations(list, index + 1, result, startLocation);
                swap(list, index, i);  // backtrack
            }
        }
    }

    private static void swap(List<String> list, int i, int j) {
        String temp = list.get(i);
        list.set(i, list.get(j));
        list.set(j, temp);
    }
}