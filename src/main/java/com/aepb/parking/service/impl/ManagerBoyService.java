package com.aepb.parking.service.impl;

import com.aepb.parking.entity.ManagerBoyEntity;
import com.aepb.parking.entity.ParkingLotEntity;
import com.aepb.parking.entity.TicketEntity;
import com.aepb.parking.exception.ParkingException;
import com.aepb.parking.exception.TicketException;
import com.aepb.parking.model.ManagerBoy;
import com.aepb.parking.model.ParkingLot;
import com.aepb.parking.model.ParkingTicket;
import com.aepb.parking.service.Car;
import com.aepb.parking.service.Parking;

import java.util.Arrays;

public class ManagerBoyService extends AbstractService implements Parking {
    private final ParkingLotService parkingLotService;

    public ManagerBoyService() {
        super();
        parkingLotService = app.getComponent(ParkingLotService.class);
    }

    protected ParkingTicket defaultPark(ManagerBoyEntity managerBoyEntity, Long boyId, Car car) throws ParkingException {
        if (managerBoyEntity.getParkingLotEntities().isEmpty()) throw new ParkingException("Boy没有可用的停车场");
        ParkingTicket ticket;
        for (ParkingLotEntity parkingLotEntity : managerBoyEntity.getParkingLotEntities()) {
            try {
                ticket = parkingLotService.park(parkingLotEntity.getParkingLot().getId(), car);
                managerBoyRepo.ticketBindManagerBoy(boyId, ticket);
                return ticket;
            } catch (ParkingException ignored) {
            }
        }
        throw new ParkingException("没有空位");
    }

    private Parking getParking(Long boyId) throws ParkingException {
        ManagerBoy managerBoy = managerBoyRepo.getManagerBoy(boyId);
        switch (managerBoy.getType()) {
            case GraduateBoy:
                return app.getComponent(GraduateBoyService.class);
            case SmartBoy:
                return app.getComponent(SmartBoyService.class);
            default:
                throw new ParkingException("不支持的类别");
        }
    }

    @Override
    public ParkingTicket park(Long boyId, Car car) throws ParkingException {
        return getParking(boyId).park(boyId, car);
    }

    @Override
    public void unPark(Long boyId, Long parkingTicketId) throws ParkingException, TicketException {
        getParking(boyId).unPark(boyId, parkingTicketId);
    }

    protected void defaultUnPark(Long boyId, Long parkingTicketId) throws TicketException, ParkingException {
        TicketEntity ticketEntity = ticketRepo.getTicketEntity(parkingTicketId);
        if (ticketEntity.getManagerBoy() == null) {
            throw new ParkingException("拒绝服务");
        }
        if (!ticketEntity.getManagerBoy().getId().equals(boyId)) {
            throw new ParkingException("拒绝服务");
        }
        parkingLotService.unPark(ticketEntity.getParkingLot().getId(), parkingTicketId);
    }

    public void bindParkLot(Long boyId, ParkingLot... parkingLots) {
        managerBoyRepo.bindParkLot(boyId, Arrays.asList(parkingLots));
    }
}
