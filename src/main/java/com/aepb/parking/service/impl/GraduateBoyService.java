package com.aepb.parking.service.impl;

import com.aepb.parking.entity.ParkingLotEntity;
import com.aepb.parking.exception.ParkingException;
import com.aepb.parking.exception.TicketException;
import com.aepb.parking.model.ParkingTicket;
import com.aepb.parking.service.Car;
import com.aepb.parking.service.Parking;

import java.util.Comparator;
import java.util.List;

public class GraduateBoyService extends AbstractService implements Parking {
    private ManagerBoyService managerBoyService;

    public GraduateBoyService() {
        managerBoyService = app.getComponent(ManagerBoyService.class);
    }

    @Override
    public ParkingTicket park(Long boyId, Car car) throws ParkingException {
        List<ParkingLotEntity> manageParkingLot = managerBoyRepo.getManageParkingLot(boyId);
        manageParkingLot.sort(Comparator.comparing(n -> n.getParkingLot().getId()));
        return managerBoyService.defaultPark(manageParkingLot, boyId, car);
    }

    @Override
    public void unPark(Long id, Long parkingTicketId) throws ParkingException, TicketException {
        managerBoyService.defaultUnPark(id, parkingTicketId);
    }
}
