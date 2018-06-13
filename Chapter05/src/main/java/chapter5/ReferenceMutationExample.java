package chapter5;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ReferenceMutationExample {

    public static void main(String[] args) {

        List<String> fruits = new ArrayList<>(Arrays.asList("apple", "banana"));

        int expected = fruits.size();

        maliciousCheck(fruits);

        System.out.println(String.format("I expected %s fruits, and got %s", expected, fruits.size()));

    }

    private static void maliciousCheck(List<String> fruits) {
        // Stole all your fruits to myself
        fruits = new ArrayList<>();
    }
}
