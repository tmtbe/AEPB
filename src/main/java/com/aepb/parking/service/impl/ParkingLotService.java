package com.aepb.parking.service.impl;

import com.aepb.parking.dto.ParkingTicket;
import com.aepb.parking.exception.ParkingException;
import com.aepb.parking.exception.TicketException;
import com.aepb.parking.service.Car;
import com.aepb.parking.service.Parking;

import java.util.Date;
import java.util.HashSet;

public class ParkingLotService implements Parking {
    private HashSet<String> contains = new HashSet<>();
    private HashSet<String> carIds = new HashSet<>();
    private long maxContain;
    private String name;

    public ParkingLotService(String name, int i, int i1) {
        this.maxContain = i * i1;
        this.name = name;
    }

    public static String getName(ParkingTicket parkingTicket) throws TicketException {
        if (parkingTicket.getMessages().size() >= 1) {
            return parkingTicket.getMessages().get(0);
        } else {
            throw new TicketException("获取信息失败");
        }
    }

    public long getMaxContain() {
        return maxContain;
    }

    public ParkingTicket park(Car car) throws ParkingException {
        if (contains.size() >= maxContain) {
            throw new ParkingException("已满");
        }
        ParkingTicket parkingTicket = new ParkingTicket();
        Date date = new Date();
        parkingTicket.gen(car, date.getTime());
        if (carIds.contains(car.getCarId())) {
            throw new ParkingException("此车ID已入库");
        }
        if (contains.contains(parkingTicket.toString())) {
            throw new ParkingException("此车已入库");
        }
        contains.add(parkingTicket.toString());
        carIds.add(car.getCarId());
        parkingTicket.addMessage(this.getName());
        return parkingTicket;
    }

    public void unPark(ParkingTicket parkingTicket) throws ParkingException, TicketException {
        ParkingTicket.validate(parkingTicket.toString());
        if (!contains.contains(parkingTicket.toString())) {
            throw new ParkingException("查无此车");
        }
        ParkingTicket.destroy(parkingTicket);
        contains.remove(parkingTicket.toString());
        carIds.remove(parkingTicket.getCar().getCarId());
    }

    @Override
    public String getName() {
        return name;
    }

    public boolean isFull() {
        return contains.size() == maxContain;
    }
}
