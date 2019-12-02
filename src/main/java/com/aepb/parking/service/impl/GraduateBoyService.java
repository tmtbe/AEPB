package com.aepb.parking.service.impl;

import com.aepb.parking.Application;
import com.aepb.parking.dto.LotCarRelation;
import com.aepb.parking.dto.ManagerBoy;
import com.aepb.parking.dto.ParkingTicket;
import com.aepb.parking.exception.ParkingException;
import com.aepb.parking.exception.TicketException;
import com.aepb.parking.service.Car;
import com.aepb.parking.service.Parking;
import com.aepb.parking.utils.SnowId;

import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;

public enum  GraduateBoyService implements Parking {
    service;

    public String getOwnName(ParkingTicket parkingTicket) throws TicketException {
        Long managerId = parkingTicket.getManagerId();
        if(managerId==null){
            throw new TicketException("获取信息失败");
        }
        ManagerBoy managerBoy = Application.app.getManagerBoyRepo().selectManagerBoyById(managerId);
        if(managerBoy==null){
            throw new TicketException("获取信息失败");
        }else{
            return managerBoy.getName();
        }
    }

    public ParkingTicket park(Long lotId,Car car) throws ParkingException {
        LotCarRelation lotCarRelation = Application.app.getLotCarRelationRepo().insertLotCarRelationWithLotId(car.getCarId(), lotId);
        ParkingTicket parkingTicket = new ParkingTicket();
        parkingTicket.setId(SnowId.Snow.nextId());
        parkingTicket.setCarId(car.getCarId());
        parkingTicket.setCreateTime(new Date());
        parkingTicket.setParkingLotId(lotId);
        parkingTicket.setLotCarRelationId(lotCarRelation.getId());
        parkingTicket.setPick(false);
        Application.app.getParkingTicketRepo().insertTicket(parkingTicket);
        return parkingTicket;
    }

    public void unPark(Long parkingTicketId) throws TicketException {
        ParkingTicket parkingTicket = Application.app.getParkingTicketRepo().selectTicketById(parkingTicketId);
        if (parkingTicket == null) {
            throw new TicketException("不存在的票据");
        }
        if(parkingTicket.isPick()){
            throw new TicketException("已取过车票");
        }
        Application.app.getParkingTicketRepo().updateTicket(parkingTicket);
        Application.app.getLotCarRelationRepo().deleteLotCarRelationById(parkingTicket.getLotCarRelationId());
    }

    public void bindParkLot(ParkingLotService... parkingLot) {
        for (ParkingLotService lot : parkingLot) {
            this.parkingLotHashMap.put(lot.getName(), lot);
            this.parkingLotList.add(lot);
        }
    }
}
