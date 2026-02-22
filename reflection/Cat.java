package reflection;

public class Cat {

    private final String name;
    private int age;

    public Cat(String name, int age) {
        this.name = name;
        this.age = age;
    }

    // Returns the cat's name
    public String getName() {
        return name;
    }

    // Returns the cat's age
    public int getAge() {
        return age;
    }

    // Sets/updates the cat's age
    public void setAge(int age) {
        this.age = age;
    }

    // Makes the cat meow
    public void meow() {
        System.out.println("Meow");
    }

    // Example of a private method (only callable inside this class)
    private void heyThisisPrivate() {
        System.out.println("How do you call this?");
    }

    // Public static method — callable without creating a Cat object
    public static void thisIsAPublicStaticMethod() {
        System.out.println("I'm public and static!");
    }

    // Private static method — only usable inside this class
    private static void thisIsAPrivateStaticMethod() {
        System.out.println("I'm private and static!");
    }
}