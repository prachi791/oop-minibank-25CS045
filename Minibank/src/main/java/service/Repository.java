package service;

import model.annotation.Id;
import model.Account;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;

public class Repository<T> {
    private final HashMap<String, T> items = new HashMap<>();

    public T save(T entity) {
        if (entity == null) throw new IllegalArgumentException("Entity cannot be null.");
        try {
            for (Class<?> type = entity.getClass(); type != null; type = type.getSuperclass()) {
                for (Field field : type.getDeclaredFields()) {
                    if (field.isAnnotationPresent(Id.class)) {
                        field.setAccessible(true);
                        Object value = field.get(entity);
                        if (value == null) throw new IllegalArgumentException("Id cannot be null.");
                        items.put(value.toString(), entity);
                        if (entity instanceof Account account) Account.updateSequence(account.getAccountNumber());
                        return entity;
                    }
                }
            }
        } catch (IllegalAccessException e) {
            throw new IllegalArgumentException("Could not read entity id.", e);
        }
        throw new IllegalArgumentException("Entity has no @Id field.");
    }

    @SafeVarargs
    public final void saveAll(T... values) { for (T value : values) save(value); }
    public T findById(String id) { return items.get(id); }
    public ArrayList<T> findAll() { return new ArrayList<>(items.values()); }
    public boolean delete(String id) { return items.remove(id) != null; }
    public int size() { return items.size(); }
}
