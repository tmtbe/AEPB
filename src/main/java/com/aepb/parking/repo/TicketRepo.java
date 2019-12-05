package com.aepb.parking.repo;

import com.aepb.parking.entity.TicketEntity;
import com.aepb.parking.exception.ParkingException;
import com.aepb.parking.exception.SystemError;
import com.aepb.parking.exception.TicketException;
import com.aepb.parking.model.*;
import com.aepb.parking.service.Car;
import com.aepb.parking.utils.SnowId;

import java.util.Date;
import java.util.List;

public class TicketRepo extends AbstractRepo {

    public TicketEntity getTicketEntity(Long ticketId) throws TicketException {
        ParkingTicket parkingTicket = parkingTicketMapper.selectById(ticketId);
        if (parkingTicket == null) {
            throw new TicketException("不存在的票据");
        }
        LotCarRelation lotCarRelation = lotCarRelationMapper.selectById(parkingTicket.getLotCarRelationId());
        if (lotCarRelation == null) {
            throw new SystemError("无法获取车的信息");
        }
        ParkingLot parkingLot = parkingLotMapper.selectById(lotCarRelation.getLotId());
        if (parkingLot == null) {
            throw new SystemError("无法获取停车场信息");
        }
        ParkingBoy parkingBoy = null;
        ParkingBoyTicketRelation parkingBoyTicketRelation = parkingBoyTicketRelationMapper.selectByTicketId(parkingTicket.getId());
        if (parkingBoyTicketRelation != null) {
            Long boyId = parkingBoyTicketRelation.getBoyId();
            parkingBoy = parkingBoyMapper.selectById(boyId);
            if (parkingBoy == null) {
                throw new SystemError("无法获取管理员信息");
            }
        }
        return TicketEntity.builder()
                .ticket(parkingTicket)
                .parkingLot(parkingLot)
                .parkingBoy(parkingBoy)
                .carId(lotCarRelation.getCarId())
                .build();
    }

    public void setPick(TicketEntity ticketEntity) {
        ticketEntity.getTicket().setPick(true);
        parkingTicketMapper.update(ticketEntity.getTicket());
    }

    public ParkingTicket parkToLot(Car car, Long lotId) throws ParkingException {
        LotCarRelation lotCarRelation = new LotCarRelation();
        lotCarRelation.setId(SnowId.Snow.nextId());
        lotCarRelation.setCarId(car.getCarId());
        lotCarRelation.setLotId(lotId);
        ParkingLot parkingLot = parkingLotMapper.selectById(lotId);
        if (parkingLot == null) throw new ParkingException("停车场不存在");
        if (lotCarRelationMapper.countByLotId(lotId) >= parkingLot.getMaxCapacity()) {
            throw new ParkingException("停车场已满");
        }
        List<LotCarRelation> tables = lotCarRelationMapper.selectByCarId(car.getCarId());
        if (tables.size() > 0) {
            throw new ParkingException("此车已入场");
        }
        lotCarRelationMapper.insert(lotCarRelation);
        ParkingTicket parkingTicket = new ParkingTicket();
        parkingTicket.setId(SnowId.Snow.nextId());
        parkingTicket.setCreateTime(new Date());
        parkingTicket.setLotCarRelationId(lotCarRelation.getId());
        parkingTicket.setPick(false);
        parkingTicketMapper.insert(parkingTicket);
        return parkingTicket;
    }

}
