package com.aepb.parking;

import com.aepb.parking.enums.ParkingBoyType;
import com.aepb.parking.exception.ParkingException;
import com.aepb.parking.exception.TicketException;
import com.aepb.parking.model.ParkingBoy;
import com.aepb.parking.model.ParkingLot;
import com.aepb.parking.model.ParkingTicket;
import org.junit.Before;
import org.junit.Test;

import static junit.framework.TestCase.assertEquals;

public class SmartBoyTest extends AbstractTest {
    private ParkingLot parkingLotA;
    private ParkingLot parkingLotB;
    private TestCar testCarA;
    private TestCar testCarB;
    private TestCar testCarC;
    private TestCar testCarD;
    private ParkingBoy smartBoy;

    @Before
    public void setUp() {
        super.setUp();
        parkingLotA = parkingLotRepo.createParkingLot("parkA", 1L);
        parkingLotB = parkingLotRepo.createParkingLot("parkB", 3L);
        smartBoy = parkingBoyRepo.createParkingBoy("boy", ParkingBoyType.SmartBoy);
        managerBoyService.bindParkLot(smartBoy.getId(), parkingLotA, parkingLotB);

        testCarA = new TestCar("粤A12133");
        testCarB = new TestCar("粤B12232");
        testCarC = new TestCar("粤B12234");
        testCarD = new TestCar("粤B12246");
    }

    @Test
    public void should_sortBy_surplus() throws ParkingException, TicketException {
        ParkingTicket ticketA = managerBoyService.park(smartBoy.getId(), testCarA);
        ParkingTicket ticketB = managerBoyService.park(smartBoy.getId(), testCarB);
        ParkingTicket ticketC = managerBoyService.park(smartBoy.getId(), testCarC);
        ParkingTicket ticketD = managerBoyService.park(smartBoy.getId(), testCarD);
        assertEquals(parkingLotB.getName(), ticketRepo.getTicketEntity(ticketA.getId()).getParkingLot().getName());
        assertEquals(parkingLotB.getName(), ticketRepo.getTicketEntity(ticketB.getId()).getParkingLot().getName());
        assertEquals(parkingLotA.getName(), ticketRepo.getTicketEntity(ticketC.getId()).getParkingLot().getName());
        assertEquals(parkingLotB.getName(), ticketRepo.getTicketEntity(ticketD.getId()).getParkingLot().getName());
    }
}
