package com.aepb.parking.service.impl;

import com.aepb.parking.exception.ParkingException;
import com.aepb.parking.exception.TicketException;
import com.aepb.parking.model.ManagerBoyLotRelation;
import com.aepb.parking.model.ParkingLot;
import com.aepb.parking.model.ParkingTicket;
import com.aepb.parking.service.Car;
import com.aepb.parking.service.Parking;

import java.util.List;

public class SmartBoyService extends AbstractService implements Parking {
    private ManagerBoyService managerBoyService;

    public SmartBoyService() {
        managerBoyService = app.getComponent(ManagerBoyService.class);
    }

    @Override
    public String getOwnName(ParkingTicket parkingTicket) throws TicketException {
        return managerBoyService.getOwnName(parkingTicket);
    }

    @Override
    public ParkingTicket park(Long boyId, Car car) throws ParkingException {
        List<ManagerBoyLotRelation> managerBoyLotRelations = managerBoyLotRelationRepo.selectByBoyId(boyId);
        managerBoyLotRelations.sort((A, B) -> {
            int result = getSurplus(B).compareTo(getSurplus(A));
            if(result == 0) result = A.getId().compareTo(B.getId());
            return result;
        });
        return managerBoyService.defaultPark(managerBoyLotRelations, boyId, car);
    }

    private Long getSurplus(ManagerBoyLotRelation managerBoyLotRelation) {
        ParkingLot parkingLot = parkingLotRepo.selectParkingLotById(managerBoyLotRelation.getLotId());
        Long count = lotCarRelationRepo.countByLotId(managerBoyLotRelation.getLotId());
        return parkingLot.getMaxCapacity()-count;
    }

    @Override
    public void unPark(Long id, Long parkingTicketId) throws ParkingException, TicketException {
        managerBoyService.defaultUnPark(id, parkingTicketId);
    }
}
