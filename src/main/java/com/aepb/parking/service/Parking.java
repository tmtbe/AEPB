package com.aepb.parking.service;

import com.aepb.parking.dto.ParkingTicket;
import com.aepb.parking.exception.ParkingException;
import com.aepb.parking.exception.TicketException;

public interface Parking {
    ParkingTicket park(Car car) throws ParkingException;

    void unPark(ParkingTicket parkingTicket) throws ParkingException, TicketException;

    String getName();
}
