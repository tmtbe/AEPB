package com.aepb.parking.service.impl;

import com.aepb.parking.exception.TicketException;
import com.aepb.parking.model.ParkingTicket;

public class TicketService extends AbstractService{
    public ParkingTicket getTicketById(Long parkingTicketId) throws TicketException {
        ParkingTicket parkingTicket = parkingTicketRepo.selectTicketById(parkingTicketId);
        if (parkingTicket == null) {
            throw new TicketException("不存在的票据");
        }
        return parkingTicket;
    }
}
