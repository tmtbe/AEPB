package com.aepb.parking;

import com.aepb.parking.enums.ManagerBoyType;
import com.aepb.parking.exception.ParkingException;
import com.aepb.parking.exception.TicketException;
import com.aepb.parking.model.ManagerBoy;
import com.aepb.parking.model.ParkingLot;
import com.aepb.parking.model.ParkingTicket;
import org.junit.Before;
import org.junit.Test;

import static junit.framework.TestCase.assertEquals;

public class SuperBoyTest extends AbstractTest {
    private ParkingLot parkingLotA;
    private ParkingLot parkingLotB;
    private TestCar testCarA;
    private TestCar testCarB;
    private TestCar testCarC;
    private TestCar testCarD;
    private ManagerBoy superBoy;

    @Before
    public void setUp() {
        super.setUp();
        parkingLotA = parkingLotRepo.createParkingLot("parkA", 2L);
        parkingLotB = parkingLotRepo.createParkingLot("parkB", 3L);
        superBoy = managerBoyRepo.createManagerBoy("boy", ManagerBoyType.SuperBoy);
        managerBoyService.bindParkLot(superBoy.getId(), parkingLotA, parkingLotB);

        testCarA = new TestCar("粤A12133");
        testCarB = new TestCar("粤B12232");
        testCarC = new TestCar("粤B12234");
        testCarD = new TestCar("粤B12246");
    }

    @Test
    public void should_sortBy_emptyRate() throws ParkingException, TicketException {
        //A 100%,B 100%
        ParkingTicket ticketA = managerBoyService.park(superBoy.getId(), testCarA);
        assertEquals(parkingLotA.getName(), ticketRepo.getTicketEntity(ticketA.getId()).getParkingLot().getName());
        //A 50%,B 100%
        ParkingTicket ticketB = managerBoyService.park(superBoy.getId(), testCarB);
        assertEquals(parkingLotB.getName(), ticketRepo.getTicketEntity(ticketB.getId()).getParkingLot().getName());
        //A 50%,B 66.7%
        ParkingTicket ticketC = managerBoyService.park(superBoy.getId(), testCarC);
        assertEquals(parkingLotB.getName(), ticketRepo.getTicketEntity(ticketC.getId()).getParkingLot().getName());
        //A 50%,B 33.3%
        ParkingTicket ticketD = managerBoyService.park(superBoy.getId(), testCarD);
        assertEquals(parkingLotA.getName(), ticketRepo.getTicketEntity(ticketD.getId()).getParkingLot().getName());
    }
}
