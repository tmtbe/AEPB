package com.aepb.parking.dto;

import com.aepb.parking.Application;
import com.aepb.parking.exception.TicketException;
import com.aepb.parking.repo.Table;
import com.aepb.parking.service.Car;
import com.aepb.parking.utils.SnowId;

import java.util.Date;

public class ParkingTicket implements Table {
    private Long id;
    private Long lotCarRelationId;
    private Long parkingLotId;
    private Long managerId;
    private Date createTime;
    private String carId;
    private Boolean pick;

    public void gen(Car car, Date createTime) {
        this.id = SnowId.Snow.nextId();
        this.carId = car.getCarId();
        this.createTime = createTime;
    }

    @Override
    public Long getId() {
        return id;
    }

    public Long getParkingLotId() {
        return parkingLotId;
    }

    public void setParkingLotId(Long parkingLotId) {
        this.parkingLotId = parkingLotId;
    }

    public Long getManagerId() {
        return managerId;
    }

    public void setManagerId(Long managerId) {
        this.managerId = managerId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getCarId() {
        return carId;
    }

    public void setCarId(String carId) {
        this.carId = carId;
    }

    public Boolean isPick() {
        return pick;
    }

    public void setPick(Boolean pick) {
        this.pick = pick;
    }

    public Long getLotCarRelationId() {
        return lotCarRelationId;
    }

    public void setLotCarRelationId(Long lotCarRelationId) {
        this.lotCarRelationId = lotCarRelationId;
    }
}
