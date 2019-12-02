package com.aepb.parking.service.impl;

import com.aepb.parking.Application;
import com.aepb.parking.dto.LotCarRelation;
import com.aepb.parking.dto.ManagerBoy;
import com.aepb.parking.dto.ParkingLot;
import com.aepb.parking.dto.ParkingTicket;
import com.aepb.parking.exception.ParkingException;
import com.aepb.parking.exception.TicketException;
import com.aepb.parking.service.Car;
import com.aepb.parking.service.Parking;
import com.aepb.parking.utils.SnowId;

import java.util.Date;
import java.util.HashSet;

public enum ParkingLotService implements Parking {
    service;
    private HashSet<String> contains = new HashSet<>();
    private HashSet<String> carIds = new HashSet<>();
    private long maxContain;
    private String name;

    public String getName(ParkingTicket parkingTicket) throws TicketException {
        Long parkingLotId = parkingTicket.getParkingLotId();
        if(parkingLotId==null){
            throw new TicketException("获取信息失败");
        }
        ParkingLot parkingLot = Application.app.getParkingLotRepo().selectParkingLotById(parkingLotId);
        if(parkingLot==null){
            throw new TicketException("获取信息失败");
        }else{
            return parkingLot.getName();
        }
    }

    public long getMaxContain() {
        return maxContain;
    }

    public ParkingTicket park(Long id,Car car) throws ParkingException {
        ParkingLot parkingLot = Application.app.getParkingLotRepo().selectParkingLotById(id);
        if(parkingLot==null){
            throw new ParkingException("不存在该停车场");
        }
        if (contains.size() >= maxContain) {
            throw new ParkingException("已满");
        }
        LotCarRelation lotCarRelation = new LotCarRelation();
        lotCarRelation.setId(SnowId.Snow.nextId());
        lotCarRelation.setCarId(car.getCarId());
        lotCarRelation.setLotId(parkingLot.getId());
        Application.app.getLotCarRelationRepo().insertLotCarRelation(lotCarRelation);
        ParkingTicket parkingTicket = new ParkingTicket();
        parkingTicket.setCarId(car.getCarId());
        parkingTicket.setCreateTime(new Date());
        parkingTicket.setParkingLotId(parkingLot.getId());
        parkingTicket.setLotCarRelationId(lotCarRelation.getId());
        parkingTicket.setPick(false);
        return parkingTicket;
    }

    public void unPark(Long parkingTicketId) throws ParkingException, TicketException {
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

    @Override
    public String getName() {
        return name;
    }

    public boolean isFull() {
        return contains.size() == maxContain;
    }
}
