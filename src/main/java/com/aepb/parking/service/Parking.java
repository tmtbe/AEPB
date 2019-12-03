package com.aepb.parking.service;

import com.aepb.parking.exception.ParkingException;
import com.aepb.parking.exception.TicketException;
import com.aepb.parking.model.ParkingTicket;

public interface Parking {
    ParkingTicket park(Long id, Car car) throws ParkingException;

    void unPark(Long id,Long parkingTicketId) throws ParkingException, TicketException;
}
