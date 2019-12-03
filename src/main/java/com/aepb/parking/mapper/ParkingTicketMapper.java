package com.aepb.parking.mapper;

import com.aepb.parking.model.ParkingTicket;

import static com.aepb.parking.utils.HashDb.DB;

public class ParkingTicketMapper {
    public void insert(ParkingTicket ticket) {
        DB.add(ticket);
    }

    public ParkingTicket selectById(Long id) {
        return DB.getTable(ParkingTicket.class).where(ParkingTicket::getId, id).one();
    }

    public void update(ParkingTicket parkingTicket) {
        DB.update(ParkingTicket.class, ParkingTicket::getId, parkingTicket);
    }
}
