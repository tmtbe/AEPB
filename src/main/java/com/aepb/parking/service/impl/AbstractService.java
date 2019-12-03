package com.aepb.parking.service.impl;

import com.aepb.parking.Application;
import com.aepb.parking.repo.*;

public abstract class AbstractService {
    protected final Application app;
    protected final ParkingLotCarRelationRepo lotCarRelationRepo;
    protected final ParkingTicketRepo parkingTicketRepo;
    protected final ParkingLotRepo parkingLotRepo;
    protected final ManagerBoyRepo managerBoyRepo;
    protected final ManagerBoyLotRelationRepo managerBoyLotRelationRepo;
    protected final ManagerBoyTicketRelationRepo managerBoyTicketRelationRepo;

    public AbstractService() {
        this.app = Application.app;
        this.managerBoyLotRelationRepo = Application.app.getComponent(ManagerBoyLotRelationRepo.class);
        this.managerBoyTicketRelationRepo = Application.app.getComponent(ManagerBoyTicketRelationRepo.class);
        this.lotCarRelationRepo = Application.app.getComponent(ParkingLotCarRelationRepo.class);
        this.parkingTicketRepo = Application.app.getComponent(ParkingTicketRepo.class);
        this.parkingLotRepo = Application.app.getComponent(ParkingLotRepo.class);
        this.managerBoyRepo = Application.app.getComponent(ManagerBoyRepo.class);
    }
}
