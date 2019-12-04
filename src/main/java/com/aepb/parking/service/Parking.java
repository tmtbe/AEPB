package com.aepb.parking.service;

import com.aepb.parking.entity.ParkingLotEntity;
import com.aepb.parking.entity.TicketEntity;
import com.aepb.parking.exception.ParkingException;
import com.aepb.parking.exception.TicketException;
import com.aepb.parking.model.ParkingTicket;
import com.aepb.parking.repo.TicketRepo;
import com.aepb.parking.utils.Application;

public interface Parking {
    default ParkingTicket park(ParkingLotEntity parkingLotEntity, Car car) throws ParkingException {
        return getTicketRepo().parkToLot(car, parkingLotEntity.getParkingLot().getId());
    }

    default void unPark(ParkingLotEntity parkingLotEntity, TicketEntity ticketEntity) throws TicketException, ParkingException {
        if (ticketEntity.getTicket().getPick()) {
            throw new TicketException("车票已取过");
        }
        if (!parkingLotEntity.getParkingLot().getId().equals(ticketEntity.getParkingLot().getId())) {
            throw new ParkingException("不是该停车场的票");
        }
        getTicketRepo().setPick(ticketEntity);
    }

    default TicketRepo getTicketRepo() {
        return Application.app.getComponent(TicketRepo.class);
    }
}
