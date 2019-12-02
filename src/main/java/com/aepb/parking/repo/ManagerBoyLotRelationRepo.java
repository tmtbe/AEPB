package com.aepb.parking.repo;

import com.aepb.parking.dto.ManagerBoy;
import com.aepb.parking.dto.ManagerBoyLotRelation;

public class ManagerBoyLotRelationRepo {
    public void insertManagerBoyLotRelation(ManagerBoyLotRelation managerBoyLotRelation) {
        HashDb.DB.add(managerBoyLotRelation);
    }

    public ManagerBoyLotRelation selectManagerBoyLotRelationById(Long id) {
        return HashDb.DB.getById(ManagerBoyLotRelation.class, id);
    }
}
