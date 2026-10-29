package util;

import model.annotation.MaxLength;
import model.annotation.Positive;
import java.lang.reflect.Field;
import java.util.ArrayList;

public class AnnotationValidator {
    public static String[] validate(Object obj) {
        ArrayList<String> errors = new ArrayList<>();
        for (Field field : obj.getClass().getDeclaredFields()) {
            try {
                field.setAccessible(true);
                Object value = field.get(obj);
                if (field.isAnnotationPresent(Positive.class) && value instanceof Number number && number.longValue() < 0) {
                    errors.add(field.getName() + " " + field.getAnnotation(Positive.class).message());
                }
                if (field.isAnnotationPresent(MaxLength.class) && value instanceof String text && text.length() > field.getAnnotation(MaxLength.class).value()) {
                    errors.add(field.getName() + " exceeds maximum length");
                }
            } catch (IllegalAccessException e) {
                errors.add("Could not validate " + field.getName());
            }
        }
        return errors.toArray(new String[0]);
    }
}
