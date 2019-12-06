package com.aepb.parking;

import com.aepb.parking.entity.ParkingBoyEntity;
import com.aepb.parking.entity.ParkingLotEntity;
import com.aepb.parking.entity.ParkingManagerEntity;
import com.aepb.parking.entity.TicketEntity;
import com.aepb.parking.enums.ParkingBoyType;
import com.aepb.parking.exception.ParkingException;
import com.aepb.parking.exception.TicketException;
import com.aepb.parking.model.ParkingBoy;
import com.aepb.parking.model.ParkingLot;
import com.aepb.parking.model.ParkingTicket;
import org.junit.Before;
import org.junit.Test;

import static junit.framework.TestCase.assertEquals;

public class ManagerTest extends AbstractTest {
    private ParkingLot parkingLotA;
    private ParkingLot parkingLotB;
    private TestCar testCarA;
    private TestCar testCarB;
    private TestCar testCarC;
    private TestCar testCarD;
    private ParkingBoy superBoy;
    private ParkingManagerEntity parkingManagerEntity;

    @Before
    public void setUp() {
        super.setUp();
        parkingLotA = parkingLotRepo.createParkingLot("parkA", 2L);
        parkingLotB = parkingLotRepo.createParkingLot("parkB", 3L);
        superBoy = parkingBoyRepo.createParkingBoy("boy", ParkingBoyType.SuperBoy);
        managerBoyService.bindParkLot(superBoy.getId(), parkingLotA, parkingLotB);

        testCarA = new TestCar("粤A12133");
        testCarB = new TestCar("粤B12232");
        testCarC = new TestCar("粤B12234");
        testCarD = new TestCar("粤B12246");

        parkingManagerEntity = ParkingManagerEntity.builder().build();
    }

    @Test(expected = ParkingException.class)
    public void should_not_park() throws ParkingException {
       parkingManagerService.park(parkingManagerEntity,testCarA);
    }

    @Test
    public void should_park_use_boy() throws ParkingException, TicketException {
        ParkingBoyEntity superBoyEntity = parkingBoyRepo.getParkingBoyEntity(superBoy.getId());
        parkingManagerService.bindLotSupplier(parkingManagerEntity,superBoyEntity);
        ParkingTicket parkingTicket = parkingManagerService.park(parkingManagerEntity,testCarA);
        TicketEntity ticketEntity = ticketRepo.getTicketEntity(parkingTicket.getId());
        assertEquals(superBoy.getName(),ticketEntity.getParkingBoy().getName());
    }

    @Test
    public void should_unPark_use_boy() throws ParkingException, TicketException {
        ParkingBoyEntity superBoyEntity = parkingBoyRepo.getParkingBoyEntity(superBoy.getId());
        parkingManagerService.bindLotSupplier(parkingManagerEntity,superBoyEntity);
        ParkingTicket parkingTicket = parkingManagerService.park(parkingManagerEntity,testCarA);
        TicketEntity ticketEntity = ticketRepo.getTicketEntity(parkingTicket.getId());
        parkingManagerService.unPark(parkingManagerEntity,ticketEntity);
    }

    @Test
    public void should_park_use_lot() throws ParkingException, TicketException {
        ParkingLotEntity parkLotEntity = parkingLotRepo.getParkLotEntity(parkingLotA.getId());
        parkingManagerService.bindLotSupplier(parkingManagerEntity,parkLotEntity);
        ParkingTicket parkingTicket = parkingManagerService.park(parkingManagerEntity,testCarA);
        TicketEntity ticketEntity = ticketRepo.getTicketEntity(parkingTicket.getId());
        assertEquals(parkingLotA.getName(),ticketEntity.getParkingLot().getName());
    }

    @Test
    public void should_unPark_use_lot() throws ParkingException, TicketException {
        ParkingLotEntity parkLotEntity = parkingLotRepo.getParkLotEntity(parkingLotA.getId());
        parkingManagerService.bindLotSupplier(parkingManagerEntity,parkLotEntity);
        ParkingTicket parkingTicket = parkingManagerService.park(parkingManagerEntity,testCarA);
        TicketEntity ticketEntity = ticketRepo.getTicketEntity(parkingTicket.getId());
        parkingManagerService.unPark(parkingManagerEntity,ticketEntity);
    }
}
