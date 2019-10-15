import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

import static org.junit.jupiter.api.Assertions.fail;

public class MyFirstImmutableEntityTest {

    private static final String CLASS_NAME = "MyFirstImmutableEntity";
    private static final String FIELD_NAME = "myFirstImmutableField";

    @Test
    public void shouldHaveCreatedPublicFinalClassWithoutAnyPackage() {
        assertClassExistsInDefaultPackage();
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

    }

    private Class<?> assertClassExistsInDefaultPackage() {
        try {
            return Class.forName(CLASS_NAME);
        } catch (ClassNotFoundException e) {
            fail("The class " + CLASS_NAME + " has not been found in the default package.");
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
