package com.aepb.parking;

import com.aepb.parking.repo.ParkingLotRepo;
import com.aepb.parking.repo.TicketRepo;
import com.aepb.parking.service.impl.AbstractService;
import com.aepb.parking.service.impl.ParkingBoyService;
import com.aepb.parking.service.impl.ParkingLotService;
import com.aepb.parking.utils.HashDb;
import org.junit.Before;

public abstract class AbstractTest extends AbstractService {
    protected final ParkingLotService parkingLotService;
    protected final ParkingBoyService managerBoyService;
    protected final TicketRepo ticketRepo;
    protected final ParkingLotRepo parkingLotRepo;

    public AbstractTest() {
        super();
        this.parkingLotService = this.app.getComponent(ParkingLotService.class);
        this.managerBoyService = this.app.getComponent(ParkingBoyService.class);
        this.ticketRepo = this.app.getComponent(TicketRepo.class);
        this.parkingLotRepo = this.app.getComponent(ParkingLotRepo.class);
    }

    @Before
    public void setUp() {
        HashDb.DB.clean();
    }
}
