package com.aepb.parking.repo;

import com.aepb.parking.dto.LotCarRelation;

import java.util.HashMap;

public enum  HashDb {
    DB;
    private HashMap<Class,HashMap<Long,Table>> hashMap = new HashMap<>();
    public void add(Table table){
        if(!hashMap.containsKey(table.getClass())){
            hashMap.put(table.getClass(),new HashMap<>());
        }
        HashMap<Long, Table> objectTableHashMap = hashMap.get(table.getClass());
        objectTableHashMap.put(table.getId(),table);
    }
    public <T> T getById(Class<T> table,Long id){
        if(!hashMap.containsKey(table)){
            return null;
        }
        HashMap<Long, Table> longTableHashMap = hashMap.get(table);
        Table result = longTableHashMap.get(id);
        if(result!=null){
            return (T) result;
        }else{
            return null;
        }
    }

    public <T> void remove(Class<T> table, Long id) {
        if(hashMap.containsKey(table)){
            HashMap<Long, Table> longTableHashMap = hashMap.get(table);
            longTableHashMap.remove(id);
        }
    }

    public <T> HashMap<Long, Table> getByClass(Class<T> lotCarRelationClass) {
        return hashMap.get(lotCarRelationClass);
    }
}
