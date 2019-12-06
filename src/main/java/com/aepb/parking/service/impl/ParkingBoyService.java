package com.aepb.parking.service.impl;

import com.aepb.parking.entity.ParkingBoyEntity;
import com.aepb.parking.entity.ParkingLotEntity;
import com.aepb.parking.entity.TicketEntity;
import com.aepb.parking.exception.ParkingException;
import com.aepb.parking.exception.TicketException;
import com.aepb.parking.model.ParkingBoy;
import com.aepb.parking.model.ParkingLot;
import com.aepb.parking.model.ParkingTicket;
import com.aepb.parking.service.Car;
import com.aepb.parking.service.Parking;
import com.aepb.parking.service.ParkingBoyStrategy;
import com.aepb.parking.service.strategy.GraduateBoyStrategy;
import com.aepb.parking.service.strategy.SmartBoyStrategy;
import com.aepb.parking.service.strategy.SuperBoyStrategy;

import java.util.Arrays;

public class ParkingBoyService extends AbstractService implements Parking {
    public ParkingBoyService() {
        super();
    }

    private ParkingBoyStrategy getParkingBoyStrategy(Long boyId) throws ParkingException {
        ParkingBoy parkingBoy = parkingBoyRepo.getParkingBoy(boyId);
        switch (parkingBoy.getType()) {
            case GraduateBoy:
                return app.getComponent(GraduateBoyStrategy.class);
            case SmartBoy:
                return app.getComponent(SmartBoyStrategy.class);
            case SuperBoy:
                return app.getComponent(SuperBoyStrategy.class);
            default:
                throw new ParkingException("不支持的类别");
        }
    }

    public ParkingTicket park(Long boyId, Car car) throws ParkingException {
        ParkingBoyEntity manageBoyEntity = parkingBoyRepo.getParkingBoyEntity(boyId);
        ParkingLotEntity parkingLotEntity = getParkingBoyStrategy(boyId).getParkingLotEntityFromProvider(manageBoyEntity);
        ParkingTicket parkingTicket = park(parkingLotEntity, car);
        parkingBoyRepo.ticketBindParkingBoy(boyId, parkingTicket);
        return parkingTicket;
    }

    public void unPark(Long boyId, Long parkingTicketId) throws ParkingException, TicketException {
        TicketEntity ticketEntity = ticketRepo.getTicketEntity(parkingTicketId);
        if (ticketEntity.getParkingBoy() == null) {
            throw new ParkingException("拒绝服务");
        }
        if (!ticketEntity.getParkingBoy().getId().equals(boyId)) {
            throw new ParkingException("拒绝服务");
        }
        unPark(parkingLotRepo.getParkLotEntity(ticketEntity.getParkingLot().getId()), ticketEntity);
    }

    public void bindParkLot(Long boyId, ParkingLot... parkingLots) {
        parkingBoyRepo.bindParkLot(boyId, Arrays.asList(parkingLots));
    }
}
