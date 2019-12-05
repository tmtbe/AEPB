package com.aepb.parking.mapper;

import com.aepb.parking.model.ParkingBoyTicketRelation;

import java.util.List;

import static com.aepb.parking.utils.HashDb.DB;

public class ParkingBoyTicketRelationMapper {
    public void insert(ParkingBoyTicketRelation parkingBoyLotRelation) {
        DB.add(parkingBoyLotRelation);
    }

    public ParkingBoyTicketRelation selectById(Long id) {
        return DB.getTable(ParkingBoyTicketRelation.class)
                .where(ParkingBoyTicketRelation::getId, id).one();
    }

    public ParkingBoyTicketRelation selectByTicketId(Long ticketId) {
        return DB.getTable(ParkingBoyTicketRelation.class)
                .where(ParkingBoyTicketRelation::getTicketId, ticketId).one();
    }

    public List<ParkingBoyTicketRelation> selectAll() {
        return DB.getTable(ParkingBoyTicketRelation.class).list();
    }
}
