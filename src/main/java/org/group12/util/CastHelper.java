package org.group12.util;

public class CastHelper {
    public static <T> T castObject(Class<T> clazz, Object obj) {
        try {
            return clazz.cast(obj);
        } catch (ClassCastException e) {
            System.out.println("Object with ID " + obj.toString() + " is not a " + clazz.getSimpleName() + "!");
            return null;
        }
    }
}
