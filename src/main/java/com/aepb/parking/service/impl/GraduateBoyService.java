package com.aepb.parking.service.impl;

import com.aepb.parking.exception.ParkingException;
import com.aepb.parking.exception.TicketException;
import com.aepb.parking.model.ManagerBoyLotRelation;
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
    public String getOwnName(ParkingTicket parkingTicket) throws TicketException {
        return managerBoyService.getOwnName(parkingTicket);
    }

    @Override
    public ParkingTicket park(Long boyId, Car car) throws ParkingException {
        List<ManagerBoyLotRelation> managerBoyLotRelations = managerBoyLotRelationRepo.selectByBoyId(boyId);
        managerBoyLotRelations.sort(Comparator.comparing(ManagerBoyLotRelation::getId));
        return managerBoyService.defaultPark(managerBoyLotRelations, boyId, car);
    }

    @Override
    public void unPark(Long id, Long parkingTicketId) throws ParkingException, TicketException {
        managerBoyService.defaultUnPark(id, parkingTicketId);
    }
}
