package com.aepb.parking.service.impl;

import com.aepb.parking.Application;
import com.aepb.parking.dto.ManagerBoy;
import com.aepb.parking.dto.ParkingTicket;
import com.aepb.parking.exception.ParkingException;
import com.aepb.parking.exception.TicketException;
import com.aepb.parking.service.Car;
import com.aepb.parking.service.Parking;

import java.util.ArrayList;
import java.util.LinkedHashMap;

public class GraduateBoyService implements Parking {
    private String name;
    private LinkedHashMap<String, ParkingLotService> parkingLotHashMap = new LinkedHashMap<>();
    private ArrayList<ParkingLotService> parkingLotList = new ArrayList<>();

    public GraduateBoyService(String name) {
        this.name = name;
    }

    public static String getName(ParkingTicket parkingTicket) throws TicketException {
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

    @Override
    public ParkingTicket park(Car car) throws ParkingException {
        ParkingTicket parkingTicket = null;
        for (ParkingLotService parkingLot : parkingLotList) {
            if (parkingLot.isFull()) continue;
            parkingTicket = parkingLot.park(car);
            break;
        }
        if (parkingTicket != null) {
            parkingTicket.addMessage(this.name);
            return parkingTicket;
        } else {
            throw new ParkingException("没有停车位了");
        }
    }

    @Override
    public void unPark(ParkingTicket parkingTicket) throws ParkingException, TicketException {
        if (!this.name.equals(GraduateBoyService.getName(parkingTicket))) {
            throw new ParkingException("拒绝办理");
        }
        ParkingLotService parkingLot = parkingLotHashMap.get(ParkingLotService.getName(parkingTicket));
        if (parkingLot == null) {
            throw new ParkingException("拒绝办理");
        }
        parkingLot.unPark(parkingTicket);
    }

    @Override
    public String getName() {
        return name;
    }

    public void bindParkLot(ParkingLotService... parkingLot) {
        for (ParkingLotService lot : parkingLot) {
            this.parkingLotHashMap.put(lot.getName(), lot);
            this.parkingLotList.add(lot);
        }
    }
}
