package com.aepb.parking.service;

import com.aepb.parking.entity.*;
import com.aepb.parking.exception.ParkingException;
import com.aepb.parking.exception.TicketException;
import com.aepb.parking.model.ParkingTicket;
import com.aepb.parking.service.impl.AbstractService;
import com.aepb.parking.service.impl.ParkingBoyService;
import com.aepb.parking.service.impl.ParkingLotService;

import java.util.Arrays;
import java.util.List;

public class ParkingManagerService extends AbstractService implements Parking {
    private final ParkingBoyService parkingBoyService;
    private final ParkingLotService parkingLotService;

    public ParkingManagerService() {
        this.parkingBoyService = app.getComponent(ParkingBoyService.class);
        this.parkingLotService = app.getComponent(ParkingLotService.class);
    }

    public ParkingTicket park(ParkingManagerEntity parkingManagerEntity, Car car) throws ParkingException {
        List<ParkingLotEntity> parkingLotEntities = parkingManagerEntity.getNotFullParkingLotEntities();
        if (parkingLotEntities.isEmpty()) {
            throw new ParkingException("没有可以停的停车场");
        }
        ParkingLotEntity parkingLotEntity = parkingLotEntities.get(0);
        ParkingBoyEntity parkingBoyEntity = parkingBoyRepo.getParkingBoyFormLot(parkingLotEntity.getParkingLot().getId());
        if (parkingBoyEntity != null) {
            return parkingBoyService.park(parkingBoyEntity.getParkingBoy().getId(), car);
        } else {
            return parkingLotService.park(parkingLotEntity.getParkingLot().getId(), car);
        }
    }

    public void unPark(ParkingManagerEntity parkingManagerEntity, TicketEntity ticketEntity) throws TicketException, ParkingException {
        if (ticketEntity.getParkingBoy() != null) {
            parkingBoyService.unPark(ticketEntity.getParkingBoy().getId(), ticketEntity.getTicket().getId());
        } else {
            parkingLotService.unPark(ticketEntity.getParkingLot().getId(), ticketEntity.getTicket().getId());
        }
    }

    public void bindLotSupplier(ParkingManagerEntity parkingManagerEntity, ParkingLotSupplier... parkingLotSupplier) {
        if (parkingLotSupplier.length == 0) return;
        Arrays.stream(parkingLotSupplier).forEach(parkingManagerEntity::addLotSupplier);
    }
}
