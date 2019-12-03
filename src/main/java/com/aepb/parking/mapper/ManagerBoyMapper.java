package com.aepb.parking.mapper;

import com.aepb.parking.model.ManagerBoy;

import static com.aepb.parking.utils.HashDb.DB;

public class ManagerBoyMapper {
    public void insert(ManagerBoy managerBoy) {
        DB.add(managerBoy);
    }

    public ManagerBoy selectById(Long id) {
        return DB.getTable(ManagerBoy.class).where(ManagerBoy::getId, id).one();
    }
}
