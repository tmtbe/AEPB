package com.aepb.parking.repo;

import com.aepb.parking.model.ParkingTicket;

public class ParkingTicketRepo {
    public void insertTicket(ParkingTicket ticket) {
        HashDb.DB.add(ticket);
    }

    public ParkingTicket selectTicketById(Long id) {
        return HashDb.DB.getById(ParkingTicket.class, id);
    }

    public void updateTicket(ParkingTicket parkingTicket) {
        HashDb.DB.add(parkingTicket);
    }
}
