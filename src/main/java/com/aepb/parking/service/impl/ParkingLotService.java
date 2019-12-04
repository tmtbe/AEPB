package com.aepb.parking.service.impl;

import com.aepb.parking.exception.ParkingException;
import com.aepb.parking.exception.TicketException;
import com.aepb.parking.model.ParkingTicket;
import com.aepb.parking.service.Car;
import com.aepb.parking.service.Parking;

public class ParkingLotService extends AbstractService implements Parking {

    public ParkingTicket park(Long lotId, Car car) throws ParkingException {
        return park(parkingLotRepo.getParkLotEntity(lotId), car);
    }

    public void unPark(Long lotId, Long parkingTicketId) throws TicketException, ParkingException {
        unPark(parkingLotRepo.getParkLotEntity(lotId), ticketRepo.getTicketEntity(parkingTicketId));
    }
}
