package com.aepb.parking.model;

import com.aepb.parking.repo.Table;
import com.aepb.parking.utils.SnowId;

import java.util.Date;

public class ParkingTicket implements Table {
    private Long id;
    private Long lotCarRelationId;
    private Date createTime;
    private Boolean pick;

    public void create(Long lotCarRelationId) {
        this.id = SnowId.Snow.nextId();
        this.lotCarRelationId = lotCarRelationId;
        this.createTime = new Date();
        this.pick = false;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
