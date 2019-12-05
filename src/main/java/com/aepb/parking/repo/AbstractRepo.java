package com.aepb.parking.repo;

import com.aepb.parking.mapper.*;
import com.aepb.parking.utils.Application;

public abstract class AbstractRepo {
    protected final Application app;
    protected final ParkingLotCarRelationMapper lotCarRelationMapper;
    protected final ParkingTicketMapper parkingTicketMapper;
    protected final ParkingLotMapper parkingLotMapper;
    protected final ParkingBoyMapper parkingBoyMapper;
    protected final ParkingBoyLotRelationMapper parkingBoyLotRelationMapper;
    protected final ParkingBoyTicketRelationMapper parkingBoyTicketRelationMapper;

    public AbstractRepo() {
        this.app = Application.app;
        this.parkingBoyLotRelationMapper = app.getComponent(ParkingBoyLotRelationMapper.class);
        this.parkingBoyTicketRelationMapper = app.getComponent(ParkingBoyTicketRelationMapper.class);
        this.lotCarRelationMapper = app.getComponent(ParkingLotCarRelationMapper.class);
        this.parkingTicketMapper = app.getComponent(ParkingTicketMapper.class);
        this.parkingLotMapper = app.getComponent(ParkingLotMapper.class);
        this.parkingBoyMapper = app.getComponent(ParkingBoyMapper.class);
    }
}
