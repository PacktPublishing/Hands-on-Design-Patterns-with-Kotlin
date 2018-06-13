package chapter5;

public class NoPatternMatching {


    public static void main(String[] args) {


        Animal a1 = new Cat();
        Animal a2 = new Dog();

        getSound(a1);
        getSound(a2);
    }

    public static String getSound(Animal animal) {
        String sound = null;
        if (animal instanceof Cat) {
            sound = ((Cat)animal).purr();
        }
        else if (animal instanceof Dog) {
            sound = ((Dog)animal).bark();
        }

        if (sound == null) {
            throw new RuntimeException();
        }
        return sound;
    }
}

class Cat implements Animal {
    public String purr() {
        return "Purr-purr";
    }
}

class Dog implements Animal {
    public String bark() {
        return "Bark-bark";
    }
}

interface Animal {}
