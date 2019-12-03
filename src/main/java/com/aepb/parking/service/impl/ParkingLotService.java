package com.aepb.parking.service.impl;

import com.aepb.parking.entity.TicketEntity;
import com.aepb.parking.exception.ParkingException;
import com.aepb.parking.exception.TicketException;
import com.aepb.parking.model.ParkingTicket;
import com.aepb.parking.service.Car;
import com.aepb.parking.service.Parking;

public class ParkingLotService extends AbstractService implements Parking {
    public ParkingTicket park(Long lotId, Car car) throws ParkingException {
        return ticketRepo.parkToLot(car, lotId);
    }

    public void unPark(Long lotId,Long parkingTicketId) throws TicketException, ParkingException {
        TicketEntity ticketEntity = ticketRepo.getTicketEntity(parkingTicketId);
        if (ticketEntity.getTicket().getPick()) {
            throw new TicketException("车票已取过");
        }
        if (!lotId.equals(ticketEntity.getParkingLot().getId())) {
            throw new ParkingException("不是该停车场的票");
        }
        ticketRepo.setPick(ticketEntity);
    }
}
