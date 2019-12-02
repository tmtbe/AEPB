package com.aepb.parking.service.impl;

import com.aepb.parking.Application;
import com.aepb.parking.exception.ParkingException;
import com.aepb.parking.exception.TicketException;
import com.aepb.parking.model.*;
import com.aepb.parking.service.Car;
import com.aepb.parking.service.Parking;
import com.aepb.parking.utils.SnowId;

import java.util.List;

public class ManagerBoyService extends AbstractService implements Parking {
    private final ParkingLotService parkingLotService;

    public ManagerBoyService() {
        super();
        parkingLotService = Application.app.getComponent(ParkingLotService.class);
    }

    public String getOwnName(ParkingTicket parkingTicket) throws TicketException {
        ManagerBoyTicketRelation managerBoyTicketRelation = managerBoyTicketRelationRepo.selectManagerBoyTicketRelationByTicketId(parkingTicket.getId());
        if (managerBoyTicketRelation == null) {
            throw new TicketException("获取信息失败");
        }
        Long boyId = managerBoyTicketRelation.getBoyId();
        ManagerBoy managerBoy = managerBoyRepo.selectManagerBoyById(boyId);
        if (managerBoy == null) {
            throw new TicketException("获取信息失败");
        } else {
            return managerBoy.getName();
        }
    }

    protected ParkingTicket defaultPark(List<ManagerBoyLotRelation> managerBoyLotRelationList, Long boyId, Car car) throws ParkingException {
        if (managerBoyLotRelationList.isEmpty()) throw new ParkingException("Boy没有可用的停车场");
        ParkingTicket ticket;
        for (ManagerBoyLotRelation managerBoyLotRelation : managerBoyLotRelationList) {
            try {
                ticket = parkingLotService.park(managerBoyLotRelation.getLotId(), car);
                ManagerBoyTicketRelation managerBoyTicketRelation = new ManagerBoyTicketRelation();
                managerBoyTicketRelation.setBoyId(boyId);
                managerBoyTicketRelation.setId(SnowId.Snow.nextId());
                managerBoyTicketRelation.setTicketId(ticket.getId());
                managerBoyTicketRelationRepo.insertManagerBoyTicketRelation(managerBoyTicketRelation);
                return ticket;
            } catch (ParkingException ignored) {
            }
        }
        throw new ParkingException("没有空位");
    }

    private Parking getParking(Long boyId) throws ParkingException {
        ManagerBoy managerBoy = managerBoyRepo.selectManagerBoyById(boyId);
        switch (managerBoy.getType()) {
            case GraduateBoy:
                return application.getComponent(GraduateBoyService.class);
            case SmartBoy:
                return application.getComponent(SmartBoyService.class);
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
        ParkingTicket parkingTicket = parkingTicketRepo.selectTicketById(parkingTicketId);
        if (parkingTicket == null) {
            throw new TicketException("不存在的票");
        }
        LotCarRelation lotCarRelation = lotCarRelationRepo.selectLotCarRelationById(parkingTicket.getLotCarRelationId());
        if (lotCarRelation == null) {
            throw new TicketException("错误");
        }
        ManagerBoyTicketRelation managerBoyTicketRelation = managerBoyTicketRelationRepo.selectManagerBoyTicketRelationByTicketId(parkingTicket.getId());
        if (managerBoyTicketRelation == null) {
            throw new ParkingException("不存在的关系");
        }
        if (!managerBoyTicketRelation.getBoyId().equals(boyId)) {
            throw new ParkingException("拒绝服务");
        }
        parkingLotService.unPark(lotCarRelation.getLotId(), parkingTicketId);
    }

    public void bindParkLot(Long boyId, ParkingLot... parkingLots) {
        for (ParkingLot parkingLot : parkingLots) {
            ManagerBoyLotRelation managerBoyLotRelation = new ManagerBoyLotRelation();
            managerBoyLotRelation.setId(SnowId.Snow.nextId());
            managerBoyLotRelation.setBoyId(boyId);
            managerBoyLotRelation.setLotId(parkingLot.getId());
            managerBoyLotRelationRepo.insertManagerBoyLotRelation(managerBoyLotRelation);
        }
    }
}
