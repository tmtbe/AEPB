package com.aepb.parking.service.impl;

import com.aepb.parking.mapper.*;
import com.aepb.parking.utils.Application;

public abstract class AbstractService {
    protected final Application app;
    protected final ParkingLotCarRelationMapper lotCarRelationRepo;
    protected final ParkingTicketMapper parkingTicketRepo;
    protected final ParkingLotMapper parkingLotRepo;
    protected final ManagerBoyMapper managerBoyRepo;
    protected final ManagerBoyLotRelationMapper managerBoyLotRelationRepo;
    protected final ManagerBoyTicketRelationMapper managerBoyTicketRelationRepo;

    public AbstractService() {
        this.app = Application.app;
        this.managerBoyLotRelationRepo = Application.app.getComponent(ManagerBoyLotRelationMapper.class);
        this.managerBoyTicketRelationRepo = Application.app.getComponent(ManagerBoyTicketRelationMapper.class);
        this.lotCarRelationRepo = Application.app.getComponent(ParkingLotCarRelationMapper.class);
        this.parkingTicketRepo = Application.app.getComponent(ParkingTicketMapper.class);
        this.parkingLotRepo = Application.app.getComponent(ParkingLotMapper.class);
        this.managerBoyRepo = Application.app.getComponent(ManagerBoyMapper.class);
    }
}
