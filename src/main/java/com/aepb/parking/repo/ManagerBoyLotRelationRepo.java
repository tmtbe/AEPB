package com.aepb.parking.repo;

import com.aepb.parking.dto.ManagerBoyLotRelation;

import java.util.List;

import static java.util.stream.Collectors.toList;

public class ManagerBoyLotRelationRepo {
    public void insertManagerBoyLotRelation(ManagerBoyLotRelation managerBoyLotRelation) {
        HashDb.DB.add(managerBoyLotRelation);
    }

    public ManagerBoyLotRelation selectManagerBoyLotRelationById(Long id) {
        return HashDb.DB.getById(ManagerBoyLotRelation.class, id);
    }

    public List<Table> selectByBoyId(Long boyId) {
        return HashDb.DB.getByClass(ManagerBoyLotRelation.class).values().stream().filter(n->{
            return ((ManagerBoyLotRelation) n).getBoyId().equals(boyId);
        }).collect(toList());
    }
}
