package com.aepb.parking.model;

import com.aepb.parking.mapper.Table;
import com.aepb.parking.utils.SnowId;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
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
}
