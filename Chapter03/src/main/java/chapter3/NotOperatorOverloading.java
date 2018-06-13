package chapter3;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class NotOperatorOverloading {

    public static void main(String[] args) {
        List<String> a = Arrays.asList("a"); // In Java 9 there's also List.of()
        List<String> b = Collections.singletonList("b"); // Same for one argument
        //List<String> c = a + b;
    }
}
