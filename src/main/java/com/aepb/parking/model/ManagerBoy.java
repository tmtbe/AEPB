package com.aepb.parking.model;

import com.aepb.parking.enums.ManagerBoyType;
import com.aepb.parking.repo.Table;

public class ManagerBoy implements Table {
    private Long id;
    private String name;
    private ManagerBoyType type;

    @Override
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ManagerBoyType getType() {
        return type;
    }

    public void setType(ManagerBoyType type) {
        this.type = type;
    }
}
