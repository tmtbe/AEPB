package com.aepb.parking.repo;

import com.aepb.parking.mapper.*;
import com.aepb.parking.utils.Application;

public abstract class AbstractRepo {
    protected final Application app;
    protected final ParkingLotCarRelationMapper lotCarRelationMapper;
    protected final ParkingTicketMapper parkingTicketMapper;
    protected final ParkingLotMapper parkingLotMapper;
    protected final ManagerBoyMapper managerBoyMapper;
    protected final ManagerBoyLotRelationMapper managerBoyLotRelationMapper;
    protected final ManagerBoyTicketRelationMapper managerBoyTicketRelationMapper;

    public AbstractRepo() {
        this.app = Application.app;
        this.managerBoyLotRelationMapper = Application.app.getComponent(ManagerBoyLotRelationMapper.class);
        this.managerBoyTicketRelationMapper = Application.app.getComponent(ManagerBoyTicketRelationMapper.class);
        this.lotCarRelationMapper = Application.app.getComponent(ParkingLotCarRelationMapper.class);
        this.parkingTicketMapper = Application.app.getComponent(ParkingTicketMapper.class);
        this.parkingLotMapper = Application.app.getComponent(ParkingLotMapper.class);
        this.managerBoyMapper = Application.app.getComponent(ManagerBoyMapper.class);
    }
}
