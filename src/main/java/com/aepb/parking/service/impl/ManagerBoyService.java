package com.aepb.parking.service.impl;

import com.aepb.parking.entity.ManagerBoyEntity;
import com.aepb.parking.entity.TicketEntity;
import com.aepb.parking.exception.ParkingException;
import com.aepb.parking.exception.TicketException;
import com.aepb.parking.model.ManagerBoy;
import com.aepb.parking.model.ParkingLot;
import com.aepb.parking.model.ParkingTicket;
import com.aepb.parking.service.Car;
import com.aepb.parking.service.Parking;
import com.aepb.parking.service.ParkingBoyStrategy;
import com.aepb.parking.service.strategy.GraduateBoyStrategy;
import com.aepb.parking.service.strategy.SmartBoyStrategy;

import java.util.Arrays;

public class ManagerBoyService extends AbstractService implements Parking {
    public ManagerBoyService() {
        super();
    }

    private ParkingBoyStrategy getParkingBoyStrategy(Long boyId) throws ParkingException {
        ManagerBoy managerBoy = managerBoyRepo.getManagerBoy(boyId);
        switch (managerBoy.getType()) {
            case GraduateBoy:
                return app.getComponent(GraduateBoyStrategy.class);
            case SmartBoy:
                return app.getComponent(SmartBoyStrategy.class);
            default:
                throw new ParkingException("不支持的类别");
        }
    }

    public ParkingTicket park(Long boyId, Car car) throws ParkingException {
        ManagerBoyEntity manageBoyEntity = managerBoyRepo.getManageBoyEntity(boyId);
        getParkingBoyStrategy(boyId).handleParkingLotProvider(manageBoyEntity);
        ParkingTicket parkingTicket = park(manageBoyEntity, car);
        managerBoyRepo.ticketBindManagerBoy(boyId, parkingTicket);
        return parkingTicket;
    }

    public void unPark(Long boyId, Long parkingTicketId) throws ParkingException, TicketException {
        TicketEntity ticketEntity = ticketRepo.getTicketEntity(parkingTicketId);
        if (ticketEntity.getManagerBoy() == null) {
            throw new ParkingException("拒绝服务");
        }
        if (!ticketEntity.getManagerBoy().getId().equals(boyId)) {
            throw new ParkingException("拒绝服务");
        }
        unPark(parkingLotRepo.getParkLotEntity(ticketEntity.getParkingLot().getId()), ticketEntity);
    }

    public void bindParkLot(Long boyId, ParkingLot... parkingLots) {
        managerBoyRepo.bindParkLot(boyId, Arrays.asList(parkingLots));
    }
}
