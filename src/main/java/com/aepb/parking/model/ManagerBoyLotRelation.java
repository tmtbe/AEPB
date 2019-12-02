package com.aepb.parking.model;

import com.aepb.parking.repo.Table;

public class ManagerBoyLotRelation implements Table {
    private Long id;
    private Long boyId;
    private Long lotId;

    @Override
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getBoyId() {
        return boyId;
    }

    public void setBoyId(Long boyId) {
        this.boyId = boyId;
    }

    public Long getLotId() {
        return lotId;
    }

    public void setLotId(Long lotId) {
        this.lotId = lotId;
    }
}
