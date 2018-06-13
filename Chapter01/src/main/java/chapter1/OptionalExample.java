package chapter1;

import java.util.Optional;

public class OptionalExample {

    public static void main(String[] args) {
        printLength(Optional.empty());
    }

    public static void printLength(final Optional<String> optional) {
        optional.ifPresent(System.out::println);
    }
}
