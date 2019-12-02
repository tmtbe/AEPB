package com.aepb.parking;

import com.aepb.parking.dto.LotCarRelation;
import com.aepb.parking.dto.ManagerBoyTicketRelation;
import com.aepb.parking.repo.*;

public enum  Application {
    app;
    private final LotCarRelationRepo lotCarRelationRepo;
    private final ManagerBoyLotRelationRepo managerBoyLotRelationRepo;
    private final ParkingTicketRepo parkingTicketRepo;
    private final ManagerBoyRepo managerBoyRepo;
    private final ParkingLotRepo parkingLotRepo;
    private final ManagerBoyTicketRelationRepo managerBoyTicketRelationRepo;
    Application(){
        this.parkingTicketRepo = new ParkingTicketRepo();
        this.managerBoyRepo = new ManagerBoyRepo();
        this.parkingLotRepo = new ParkingLotRepo();
        this.lotCarRelationRepo = new LotCarRelationRepo();
        this.managerBoyLotRelationRepo = new ManagerBoyLotRelationRepo();
        this.managerBoyTicketRelationRepo = new ManagerBoyTicketRelationRepo();
    }

    public ParkingTicketRepo getParkingTicketRepo() {
        return parkingTicketRepo;
    }

    public ManagerBoyRepo getManagerBoyRepo() {
        return managerBoyRepo;
    }

    public ParkingLotRepo getParkingLotRepo() {
        return parkingLotRepo;
    }

    public LotCarRelationRepo getLotCarRelationRepo() {
        return lotCarRelationRepo;
    }

    public ManagerBoyLotRelationRepo getManagerBoyLotRelationRepo() {
        return managerBoyLotRelationRepo;
    }

    public ManagerBoyTicketRelationRepo getManagerBoyTicketRelationRepo() {
        return managerBoyTicketRelationRepo;
    }
}
