package chapter1;

public class ForEach {

    public static void main(String[] args) {
        final String word = "Word";

        for (Character c : word.toCharArray()) {
            System.out.println(c);
        }
    }
}
