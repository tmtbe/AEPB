package com.aepb.parking.repo;

import com.aepb.parking.model.ManagerBoy;

public class ManagerBoyRepo {
    public void insertManagerBoy(ManagerBoy managerBoy) {
        HashDb.DB.add(managerBoy);
    }

    public ManagerBoy selectManagerBoyById(Long id) {
        return HashDb.DB.getById(ManagerBoy.class, id);
    }
}
