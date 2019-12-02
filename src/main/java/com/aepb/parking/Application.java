package com.aepb.parking;

import com.aepb.parking.repo.ManagerBoyRepo;
import com.aepb.parking.repo.ParkingLotRepo;
import com.aepb.parking.repo.ParkingTicketRepo;

public enum  Application {
    app;
    private ParkingTicketRepo parkingTicketRepo;
    private ManagerBoyRepo managerBoyRepo;
    private ParkingLotRepo parkingLotRepo;
    Application(){
        this.parkingTicketRepo = new ParkingTicketRepo();
        this.managerBoyRepo = new ManagerBoyRepo();
        this.parkingLotRepo = new ParkingLotRepo();
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
}
