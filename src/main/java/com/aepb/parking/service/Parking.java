package com.aepb.parking.service;

import com.aepb.parking.dto.ParkingTicket;
import com.aepb.parking.exception.ParkingException;
import com.aepb.parking.exception.TicketException;

public interface Parking {
    String getOwnName(ParkingTicket parkingTicket) throws TicketException;

    ParkingTicket park(Long id,Car car) throws ParkingException;

    void unPark(Long parkingTicketId) throws ParkingException, TicketException;
}