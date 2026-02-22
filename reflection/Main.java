package reflection;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Arrays;

public class Main {

    public static void main(String[] args) throws Exception {

        Cat myCat = new Cat("Stella", 6);

        // fields declared in Cat class.
        Field[] catFields = myCat.getClass().getDeclaredFields();

        for (Field field : catFields) {
            // System.out.println(field.getName());

            if (field.getName().equals("name")) {
                field.setAccessible(true); // setting the accessibility of the private and final field to true so that
                                           // we can access it.
                field.set(myCat, "Jimmy McGill");
            }
        }

        System.out.println(myCat.getName());

        // since there is no setter methods defined
        // for name field which private and final
        // thus cannot be set from outside of the class
        // and only can be set during constructor call
        // during object creation
        // but we can set it using reflection magic ;)
        // thats what we did with the cat's name and set it to `Jimmy McGill`

        // similar to fields we can also access the methods declared in a class

        Method[] catMethods = myCat.getClass().getDeclaredMethods();

        // Arrays.stream(catMethods).forEach(method ->
        // System.out.println(method.getName())); //we can also stream it like this

        for (Method method : catMethods) {

            // for public method //we dont do this irl for public methods
            if (method.getName().equals("meow")) {
                method.invoke(myCat);
            }
            // for private method
            if (method.getName().equals("heyThisisPrivate")) {
                method.setAccessible(true);
                method.invoke(myCat);
            }

            // for a public static method
            if (method.getName().equals("thisIsAPublicStaticMethod")) {
                method.setAccessible(true);
                method.invoke(null); // since it is a static method thus does not take object as a parameter
            }

            // for a private static method
            if (method.getName().equals("thisIsAPrivateStaticMethod")) {
                method.setAccessible(true);
                method.invoke(null); // since it is a static method thus does not take object as a parameter
            }
            // System.out.println(method.getName());
        }

    }

}
