package com.aepb.parking.service.impl;

import com.aepb.parking.repo.ParkingBoyRepo;
import com.aepb.parking.repo.ParkingLotRepo;
import com.aepb.parking.repo.TicketRepo;
import com.aepb.parking.utils.Application;

public abstract class AbstractService {
    protected final Application app;
    protected final TicketRepo ticketRepo;
    protected final ParkingBoyRepo parkingBoyRepo;
    protected final ParkingLotRepo parkingLotRepo;
    public AbstractService() {
        this.app = Application.app;
        this.ticketRepo = Application.app.getComponent(TicketRepo.class);
        this.parkingBoyRepo = Application.app.getComponent(ParkingBoyRepo.class);
        this.parkingLotRepo = Application.app.getComponent(ParkingLotRepo.class);
    }
}
