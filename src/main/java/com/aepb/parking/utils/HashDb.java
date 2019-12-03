package com.aepb.parking.utils;

import com.aepb.parking.mapper.Table;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

public enum HashDb {
    DB;
    private HashMap<Class, List<Table>> hashMap = new HashMap<>();

    public void add(Table table) {
        if (!hashMap.containsKey(table.getClass())) {
            hashMap.put(table.getClass(), new ArrayList<>());
        }
        List<Table> list = hashMap.get(table.getClass());
        list.add(table);
    }

    public <T extends Table, R> void remove(Class<T> table, Function<T, R> fun, R value) {
        List<T> collect = getTable(table).where(fun, value).collect(Collectors.toList());
        List<Table> tables = hashMap.get(table);
        if (tables != null) {
            tables.removeAll(collect);
        }
    }

    public <T extends Table, R> void update(Class<T> table, Function<T, R> fun, T row) {
        remove(table, fun, fun.apply(row));
        add(row);
    }

    public <T extends Table> Select<T> getTable(Class<T> table) {
        List<Table> tables = hashMap.get(table);
        if (tables == null) {
            return new Select<>(new ArrayList<T>().stream());
        } else {
            return new Select<>(tables.stream().map(n -> (T) n));
        }
    }

    public void clean() {
        hashMap = new HashMap<>();
    }
}
