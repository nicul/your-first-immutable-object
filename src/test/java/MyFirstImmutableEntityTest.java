import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class MyFirstImmutableEntityTest {

    private static final String CLASS_NAME = "MyFirstImmutableEntity";
    private static final String FIELD_NAME = "myFirstImmutableField";
    private static final String GETTER_METHOD_NAME = "getMyFirstImmutableField";

    @Test
    public void shouldHaveCreatedPublicFinalClassWithoutAnyPackage() {
        final Class<?> myFirstImmutableClass = assertClassExistsInDefaultPackage();

        if(!Modifier.isPublic(myFirstImmutableClass.getModifiers())) {
            fail("The class " + CLASS_NAME + " has not been declared 'public'");
        }

        if(!Modifier.isFinal(myFirstImmutableClass.getModifiers())) {
            fail("The class " + CLASS_NAME + " has not been declared 'final'");
        }
    }

    @Test
    public void shouldHaveAddedPrivateFinalIntFieldToTheClass() {
        final Class<?> myFirstImmutableClass = assertClassExistsInDefaultPackage();

        final Field field = assertImmutableFieldExistsInClass(myFirstImmutableClass);

        if(!field.getType().equals(int.class)) {
            fail("The field " + FIELD_NAME + " has been found in " + CLASS_NAME +
                    " but it is of type " + field.getType() + " rather than 'int'");
        }
        if(!Modifier.isPrivate(field.getModifiers())) {
            fail("The field " + FIELD_NAME + " has been found in " + CLASS_NAME +
                    " but it isn't declared 'private'");
        }
        if(!Modifier.isFinal(field.getModifiers())) {
            fail("The field " + FIELD_NAME + " has been found in " + CLASS_NAME +
                    " but it isn't declared 'final'");
        }

    }

    @Test
    public void shouldHaveCreatedGetterMethodForTheField() {
        final Class<?> myFirstImmutableClass = assertClassExistsInDefaultPackage();

        try {
            final Method getter = myFirstImmutableClass.getDeclaredMethod(GETTER_METHOD_NAME);
            if(!Modifier.isPublic(getter.getModifiers())) {
                fail("The method " + GETTER_METHOD_NAME + " has been found in " + CLASS_NAME +
                        " but it isn't declared 'public'");
            }

            MyFirstImmutableEntity myFirstImmutableEntity = new MyFirstImmutableEntity(3);
            assertEquals(3, myFirstImmutableEntity.getMyFirstImmutableField(),
                    "The " + GETTER_METHOD_NAME + " method doesn't return the correct result.");

        } catch (NoSuchMethodException e) {
            fail("The method " + GETTER_METHOD_NAME + " has not been declared in " + CLASS_NAME);
        }

    }

    private Class<?> assertClassExistsInDefaultPackage() {
        try {
            return Class.forName(CLASS_NAME);
        } catch (ClassNotFoundException e) {
            fail("The class " + CLASS_NAME + " has not been found in the default package");
            return null;
        }
    }

    private Field assertImmutableFieldExistsInClass(Class<?> myFirstImmutableClass) {
        try {
            return myFirstImmutableClass.getDeclaredField(FIELD_NAME);
        } catch (NoSuchFieldException e) {
            fail("The field " + FIELD_NAME + " has not been declared in " + CLASS_NAME);
            return null;
        }
    }
}
