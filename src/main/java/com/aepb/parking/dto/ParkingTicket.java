package com.aepb.parking.dto;

import com.aepb.parking.Application;
import com.aepb.parking.exception.TicketException;
import com.aepb.parking.repo.Table;
import com.aepb.parking.service.Car;
import com.aepb.parking.utils.SnowId;

import java.util.Date;

public class ParkingTicket implements Table {
    private Long id;
    private Long parkingLotId;
    private Long managerId;
    private Date createTime;
    private Car car;

    public static ParkingTicket validate(Long id) throws TicketException {
        ParkingTicket parkingTicket = Application.app.getParkingTicketRepo().selectTicketById(id);
        if (parkingTicket == null) {
            throw new TicketException("不存在的票据");
        }
        return parkingTicket;
    }

    public static void destroy(ParkingTicket parkingTicket) {

    }

    public void gen(Car car, Date createTime) {
        this.id = SnowId.Snow.nextId();
        this.car = car;
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

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }
}
