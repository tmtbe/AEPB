package com.aepb.parking.mapper;

import com.aepb.parking.model.ManagerBoyLotRelation;

import java.util.List;

import static com.aepb.parking.utils.HashDb.DB;

public class ManagerBoyLotRelationMapper {
    public void insert(ManagerBoyLotRelation managerBoyLotRelation) {
        DB.add(managerBoyLotRelation);
    }

    public ManagerBoyLotRelation selectById(Long id) {
        return DB.getTable(ManagerBoyLotRelation.class)
                .where(ManagerBoyLotRelation::getId, id)
                .one();
    }

    public List<ManagerBoyLotRelation> selectByBoyId(Long boyId) {
        return DB.getTable(ManagerBoyLotRelation.class)
                .where(ManagerBoyLotRelation::getBoyId, boyId)
                .list();
    }

    public List<ManagerBoyLotRelation> selectAll() {
        return DB.getTable(ManagerBoyLotRelation.class).list();
    }
}
