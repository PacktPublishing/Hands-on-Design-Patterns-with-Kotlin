package chapter1;

public class ImmutablePerson {
    private String name;

    public ImmutablePerson(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
