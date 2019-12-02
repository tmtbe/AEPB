package com.aepb.parking.service.impl;

import com.aepb.parking.exception.ParkingException;
import com.aepb.parking.exception.TicketException;
import com.aepb.parking.model.ManagerBoyLotRelation;
import com.aepb.parking.model.ParkingTicket;
import com.aepb.parking.service.Car;
import com.aepb.parking.service.Parking;

import java.util.List;

public class GraduateBoyService extends AbstractService implements Parking {
    private ManagerBoyService managerBoyService;

    public GraduateBoyService() {
        managerBoyService = application.getComponent(ManagerBoyService.class);
    }

    @Override
    public String getOwnName(ParkingTicket parkingTicket) throws TicketException {
        return managerBoyService.getOwnName(parkingTicket);
    }

    @Override
    public ParkingTicket park(Long boyId, Car car) throws ParkingException {
        List<ManagerBoyLotRelation> managerBoyLotRelations = managerBoyLotRelationRepo.selectByBoyId(boyId);
        managerBoyLotRelations.sort((A, B) -> {
            if (A.getId() > B.getId()) {
                return 1;
            } else if (A.getId() < B.getId()) {
                return -1;
            } else {
                return 0;
            }
        });
        return managerBoyService.defaultPark(managerBoyLotRelations, boyId, car);
    }

    @Override
    public void unPark(Long id, Long parkingTicketId) throws ParkingException, TicketException {
        managerBoyService.defaultUnPark(id, parkingTicketId);
    }
}
