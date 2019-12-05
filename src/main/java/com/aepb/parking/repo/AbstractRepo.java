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
        this.parkingBoyLotRelationMapper = Application.app.getComponent(ParkingBoyLotRelationMapper.class);
        this.parkingBoyTicketRelationMapper = Application.app.getComponent(ParkingBoyTicketRelationMapper.class);
        this.lotCarRelationMapper = Application.app.getComponent(ParkingLotCarRelationMapper.class);
        this.parkingTicketMapper = Application.app.getComponent(ParkingTicketMapper.class);
        this.parkingLotMapper = Application.app.getComponent(ParkingLotMapper.class);
        this.parkingBoyMapper = Application.app.getComponent(ParkingBoyMapper.class);
    }
}
