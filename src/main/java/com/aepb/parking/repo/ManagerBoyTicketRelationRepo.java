package com.aepb.parking.repo;

import com.aepb.parking.model.ManagerBoyLotRelation;
import com.aepb.parking.model.ManagerBoyTicketRelation;

import java.util.List;
import java.util.stream.Collectors;

public class ManagerBoyTicketRelationRepo {
    public void insertManagerBoyTicketRelation(ManagerBoyTicketRelation managerBoyLotRelation) {
        HashDb.DB.add(managerBoyLotRelation);
    }

    public ManagerBoyTicketRelation selectManagerBoyTicketRelationById(Long id) {
        return HashDb.DB.getById(ManagerBoyTicketRelation.class, id);
    }

    public ManagerBoyTicketRelation selectManagerBoyTicketRelationByTicketId(Long id) {
        return selectAll().stream().filter(n-> n.getTicketId().equals(id)).findAny().orElse(null);
    }

    public List<ManagerBoyTicketRelation> selectAll(){
        return HashDb.DB.getByClass(ManagerBoyTicketRelation.class).values().stream().map(n-> (ManagerBoyTicketRelation)n).collect(Collectors.toList());
    }
}
