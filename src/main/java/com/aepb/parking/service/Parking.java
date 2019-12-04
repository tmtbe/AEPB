package com.aepb.parking.service;

import com.aepb.parking.entity.ParkingLotEntity;
import com.aepb.parking.entity.ParkingLotProvider;
import com.aepb.parking.entity.TicketEntity;
import com.aepb.parking.exception.ParkingException;
import com.aepb.parking.exception.SystemError;
import com.aepb.parking.exception.TicketException;
import com.aepb.parking.model.ParkingTicket;
import com.aepb.parking.repo.TicketRepo;
import com.aepb.parking.utils.Application;

public interface Parking {
    default ParkingTicket park(ParkingLotProvider parkingLotProvider, Car car) throws ParkingException {
        if (parkingLotProvider.getParkingLotEntities().isEmpty()) {
            throw new SystemError("没有停车场");
        }
        ParkingTicket ticket;
        for (ParkingLotEntity parkingLotEntity : parkingLotProvider.getParkingLotEntities()) {
            try {
                ticket = getTicketRepo().parkToLot(car, parkingLotEntity.getParkingLot().getId());
                return ticket;
            } catch (ParkingException ignored) {
            }
        }
        throw new ParkingException("没有空位");
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
