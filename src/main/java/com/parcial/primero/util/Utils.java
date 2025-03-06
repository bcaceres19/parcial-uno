package com.parcial.primero.util;

import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.security.SecureRandom;
import java.time.LocalDateTime;

/**
 * Utility class for comparing two objects and maintaining non-null values.
 */
public class Utils {

    private static final String CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
    private static final SecureRandom RANDOM = new SecureRandom();


    /**
     * Compares two objects and returns a new object containing the differences.
     * If a field in the new object is null or empty, the old value is retained.
     *
     * @param oldObject The original object.
     * @param newObject The updated object.
     * @param <T>       The type of the object.
     * @return A new object containing the merged differences.
     * @throws IllegalArgumentException If either object is null.
     * @throws RuntimeException         If an error occurs during reflection.
     */
    public static <T> T compareOldNewObject(T oldObject, T newObject) {
        if (oldObject == null || newObject == null) {
            throw new IllegalArgumentException("Objects to compare cannot be null.");
        }

        try {
            // Retrieve the object's class
            Class<?> clazz = oldObject.getClass();
            // Ensure the class has a no-argument constructor before instantiating
            T differenceObject = (T) clazz.getDeclaredConstructor().newInstance();

            Field[] fields = clazz.getDeclaredFields();

            for (Field field : fields) {
                field.setAccessible(true); // Allow access to private attributes

                Object oldValue = field.get(oldObject);
                Object newValue = field.get(newObject);

                // If the new value is null or empty, retain the old value
                if (newValue == null || (newValue instanceof String && ((String) newValue).trim().isEmpty())) {
                    field.set(differenceObject, oldValue);
                }
                // Special handling for LocalDateTime
                else if (field.getType().equals(LocalDateTime.class)) {
                    if (oldValue == null || !oldValue.equals(newValue)) {
                        field.set(differenceObject, newValue);
                    } else {
                        field.set(differenceObject, oldValue);
                    }
                }
                // If the attribute is an object, apply recursive comparison
                else if (!isPrimitiveOrWrapper(field.getType())) {
                    Object comparedValue = compareOldNewObject(oldValue, newValue);
                    field.set(differenceObject, comparedValue);
                }
                // If the value has changed, set the new value
                else if (!newValue.equals(oldValue)) {
                    field.set(differenceObject, newValue);
                }
                // If it hasn't changed, retain the old value
                else {
                    field.set(differenceObject, oldValue);
                }
            }

            return differenceObject;

        } catch (Exception e) {
            throw new RuntimeException("Error comparing objects: " + e.getMessage(), e);
        }
    }

    /**
     * Checks whether a given type is a primitive, a wrapper, or LocalDateTime.
     *
     * @param type The class type to check.
     * @return True if the type is a primitive, wrapper, or LocalDateTime; false otherwise.
     */
    private static boolean isPrimitiveOrWrapper(Class<?> type) {
        return type.isPrimitive() ||
                type.equals(String.class) ||
                type.equals(Integer.class) ||
                type.equals(Long.class) ||
                type.equals(Double.class) ||
                type.equals(Float.class) ||
                type.equals(Boolean.class) ||
                type.equals(Character.class) ||
                type.equals(Short.class) ||
                type.equals(Byte.class) ||
                type.equals(LocalDateTime.class) ||
                type.equals(BigDecimal.class); // Added LocalDateTime support
    }


    /**
     * Generates a random alphanumeric code of the specified length.
     *
     * @param length The desired length of the generated code.
     * @return A randomly generated alphanumeric string.
     */
    public static String generateRandomCode(int length) {
        StringBuilder sb = new StringBuilder(length);
        for (int i = 0; i < length; i++) {
            sb.append(CHARACTERS.charAt(RANDOM.nextInt(CHARACTERS.length())));
        }
        return sb.toString();
    }

}
