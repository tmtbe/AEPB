package com.aepb.parking.service.impl;

import com.aepb.parking.exception.ParkingException;
import com.aepb.parking.exception.TicketException;
import com.aepb.parking.model.LotCarRelation;
import com.aepb.parking.model.ParkingLot;
import com.aepb.parking.model.ParkingTicket;
import com.aepb.parking.service.Car;
import com.aepb.parking.service.Parking;
import com.aepb.parking.utils.SnowId;

import java.util.Date;

public class ParkingLotService extends AbstractService implements Parking {
    private final TicketService ticketService;
    public ParkingLotService (){
        super();
        ticketService = app.getComponent(TicketService.class);
    }

    public String getOwnName(ParkingTicket parkingTicket) throws TicketException {
        LotCarRelation lotCarRelation = lotCarRelationRepo.selectById(parkingTicket.getLotCarRelationId());
        if (lotCarRelation == null) {
            throw new TicketException("获取信息失败");
        }
        ParkingLot parkingLot = parkingLotRepo.selectById(lotCarRelation.getLotId());
        if (parkingLot == null) {
            throw new TicketException("获取信息失败");
        } else {
            return parkingLot.getName();
        }
    }

    public ParkingTicket park(Long lotId, Car car) throws ParkingException {
        LotCarRelation lotCarRelation = lotCarRelationRepo.insertLotCarRelationWithLotId(car.getCarId(), lotId);
        ParkingTicket parkingTicket = new ParkingTicket();
        parkingTicket.setId(SnowId.Snow.nextId());
        parkingTicket.setCreateTime(new Date());
        parkingTicket.setLotCarRelationId(lotCarRelation.getId());
        parkingTicket.setPick(false);
        parkingTicketRepo.insert(parkingTicket);
        return parkingTicket;
    }

    public void unPark(Long lotId,Long parkingTicketId) throws TicketException, ParkingException {
        ParkingTicket parkingTicket = ticketService.getTicketById(parkingTicketId);
        LotCarRelation lotCarRelation = lotCarRelationRepo.selectById(parkingTicket.getLotCarRelationId());
        if(lotCarRelation==null){
            throw new ParkingException("没有票的信息");
        }
        if(!lotId.equals(lotCarRelation.getLotId())){
            throw new ParkingException("不是该停车场的票");
        }
        if (parkingTicket.getPick()) {
            throw new ParkingException("已取过车票");
        }
        parkingTicket.setPick(true);
        parkingTicketRepo.update(parkingTicket);
        lotCarRelationRepo.deleteById(parkingTicket.getLotCarRelationId());
    }
}
