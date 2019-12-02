package com.aepb.parking.repo;

import com.aepb.parking.dto.ManagerBoyLotRelation;
import com.aepb.parking.dto.ManagerBoyTicketRelation;

public class ManagerBoyTicketRelationRepo {
    public void insertManagerBoyTicketRelation(ManagerBoyTicketRelation managerBoyLotRelation) {
        HashDb.DB.add(managerBoyLotRelation);
    }

    public ManagerBoyTicketRelation selectManagerBoyTicketRelationById(Long id) {
        return HashDb.DB.getById(ManagerBoyTicketRelation.class, id);
    }
}
