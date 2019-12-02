package com.aepb.parking.repo;

import com.aepb.parking.model.ManagerBoyLotRelation;

import java.util.List;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;

public class ManagerBoyLotRelationRepo {
    public void insertManagerBoyLotRelation(ManagerBoyLotRelation managerBoyLotRelation) {
        HashDb.DB.add(managerBoyLotRelation);
    }

    public ManagerBoyLotRelation selectManagerBoyLotRelationById(Long id) {
        return HashDb.DB.getById(ManagerBoyLotRelation.class, id);
    }

    public List<ManagerBoyLotRelation> selectByBoyId(Long boyId) {
        return selectAll()
                .stream().filter(n -> n.getBoyId().equals(boyId)).collect(toList());
    }

    public List<ManagerBoyLotRelation> selectAll(){
        return HashDb.DB.getByClass(ManagerBoyLotRelation.class).values().stream().map(n-> (ManagerBoyLotRelation) n).collect(Collectors.toList());
    }
}
