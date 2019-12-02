package com.aepb.parking.service.impl;

import com.aepb.parking.Application;
import com.aepb.parking.dto.*;
import com.aepb.parking.exception.ParkingException;
import com.aepb.parking.exception.TicketException;
import com.aepb.parking.repo.Table;
import com.aepb.parking.service.Car;
import com.aepb.parking.service.Parking;
import com.aepb.parking.utils.SnowId;

import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;

public enum  GraduateBoyService implements Parking {
    service;

    public String getOwnName(ParkingTicket parkingTicket) throws TicketException {
        ManagerBoyTicketRelation managerBoyTicketRelation = Application.app.getManagerBoyTicketRelationRepo().selectManagerBoyTicketRelationById(parkingTicket.getId());
        if(managerBoyTicketRelation==null){
            throw new TicketException("获取信息失败");
        }
        Long boyId = managerBoyTicketRelation.getBoyId();
        ManagerBoy managerBoy = Application.app.getManagerBoyRepo().selectManagerBoyById(boyId);
        if(managerBoy==null){
            throw new TicketException("获取信息失败");
        }else{
            return managerBoy.getName();
        }
    }

    public ParkingTicket park(Long boyId,Car car) throws ParkingException {
        List<Table> managerBoyLotRelationList = Application.app.getManagerBoyLotRelationRepo().selectByBoyId(boyId);
        if(managerBoyLotRelationList.isEmpty()) throw new ParkingException("Boy没有可用的停车场");
        ManagerBoyLotRelation selectOne = (ManagerBoyLotRelation) managerBoyLotRelationList.get(0);
        ParkingTicket ticket = ParkingLotService.service.park(selectOne.getLotId(), car);
        ManagerBoyTicketRelation managerBoyTicketRelation = new ManagerBoyTicketRelation();
        managerBoyTicketRelation.setBoyId(boyId);
        managerBoyTicketRelation.setId(SnowId.Snow.nextId());
        managerBoyTicketRelation.setTicketId(ticket.getId());
        Application.app.getManagerBoyTicketRelationRepo().insertManagerBoyTicketRelation(managerBoyTicketRelation);
        return ticket;
    }

    public void unPark(Long parkingTicketId) throws TicketException {
        ParkingLotService.service.unPark(parkingTicketId);
    }

    public void bindParkLot(ParkingLotService... parkingLot) {

    }
}
