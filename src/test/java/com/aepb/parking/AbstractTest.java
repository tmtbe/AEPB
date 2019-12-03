package com.aepb.parking;

import com.aepb.parking.enums.ManagerBoyType;
import com.aepb.parking.model.ManagerBoy;
import com.aepb.parking.model.ParkingLot;
import com.aepb.parking.repo.HashDb;
import com.aepb.parking.service.impl.AbstractService;
import com.aepb.parking.service.impl.ManagerBoyService;
import com.aepb.parking.service.impl.ParkingLotService;
import com.aepb.parking.utils.SnowId;
import org.junit.Before;

public abstract class AbstractTest extends AbstractService {
    protected final ParkingLotService parkingLotService;
    protected final ManagerBoyService managerBoyService;
    public AbstractTest(){
        super();
        this.parkingLotService = this.app.getComponent(ParkingLotService.class);
        this.managerBoyService = this.app.getComponent(ManagerBoyService.class);
    }
    @Before
    public void setUp(){
        HashDb.DB.clean();
    }

    protected ParkingLot createParkingLot(String name, Long maxCapacity){
        ParkingLot parkingLot = new ParkingLot();
        parkingLot.setName(name);
        parkingLot.setMaxCapacity(maxCapacity);
        parkingLot.setId(SnowId.Snow.nextId());
        parkingLotRepo.insertParkingLot(parkingLot);
        return parkingLot;
    }

    protected ManagerBoy createManagerBoy(String name, ManagerBoyType managerBoyType){
        ManagerBoy managerBoy = new ManagerBoy();
        managerBoy.setId(SnowId.Snow.nextId());
        managerBoy.setName(name);
        managerBoy.setType(managerBoyType);
        managerBoyRepo.insertManagerBoy(managerBoy);
        return managerBoy;
    }
}
