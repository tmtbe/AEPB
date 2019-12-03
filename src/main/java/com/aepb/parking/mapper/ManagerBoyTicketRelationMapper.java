package com.aepb.parking.mapper;

import com.aepb.parking.model.ManagerBoyTicketRelation;

import java.util.List;

import static com.aepb.parking.utils.HashDb.DB;

public class ManagerBoyTicketRelationMapper {
    public void insert(ManagerBoyTicketRelation managerBoyLotRelation) {
        DB.add(managerBoyLotRelation);
    }

    public ManagerBoyTicketRelation selectById(Long id) {
        return DB.getTable(ManagerBoyTicketRelation.class)
                .where(ManagerBoyTicketRelation::getId, id).one();
    }

    public ManagerBoyTicketRelation selectByTicketId(Long ticketId) {
        return DB.getTable(ManagerBoyTicketRelation.class)
                .where(ManagerBoyTicketRelation::getTicketId, ticketId).one();
    }

    public List<ManagerBoyTicketRelation> selectAll() {
        return DB.getTable(ManagerBoyTicketRelation.class).list();
    }
}
