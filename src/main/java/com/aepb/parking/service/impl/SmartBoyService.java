package com.aepb.parking.service.impl;

import com.aepb.parking.entity.ManagerBoyEntity;
import com.aepb.parking.entity.ParkingLotEntity;
import com.aepb.parking.exception.ParkingException;
import com.aepb.parking.exception.TicketException;
import com.aepb.parking.model.ParkingTicket;
import com.aepb.parking.service.Car;
import com.aepb.parking.service.Parking;

public class SmartBoyService extends AbstractService implements Parking {
    private ManagerBoyService managerBoyService;

    public SmartBoyService() {
        managerBoyService = app.getComponent(ManagerBoyService.class);
    }

    @Override
    public ParkingTicket park(Long boyId, Car car) throws ParkingException {
        ManagerBoyEntity manageBoyEntity = managerBoyRepo.getManageBoyEntity(boyId);
        manageBoyEntity.getParkingLotEntities().sort((A, B) -> {
            int result = getSurplus(B).compareTo(getSurplus(A));
            if (result == 0) result = A.getParkingLot().getId().compareTo(B.getParkingLot().getId());
            return result;
        });
        return managerBoyService.defaultPark(manageBoyEntity, boyId, car);
    }

    private Long getSurplus(ParkingLotEntity parkingLotEntity) {
        return parkingLotEntity.getParkingLot().getMaxCapacity() - parkingLotEntity.getCapacity();
    }

    @Override
    public void unPark(Long id, Long parkingTicketId) throws ParkingException, TicketException {
        managerBoyService.defaultUnPark(id, parkingTicketId);
    }
}
