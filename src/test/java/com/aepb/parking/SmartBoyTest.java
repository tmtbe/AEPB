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
import static junit.framework.TestCase.assertNotNull;

public class SmartBoyTest extends AbstractTest{
    private ParkingLot parkingLotA;
    private ParkingLot parkingLotB;
    private TestCar testCarA;
    private TestCar testCarB;
    private TestCar testCarC;
    private TestCar testCarD;
    private ManagerBoy smartBoy;

    @Before
    public void setUp(){
        super.setUp();
        parkingLotA = createParkingLot("parkA",1L);
        parkingLotB = createParkingLot("parkB",3L);
        smartBoy = createManagerBoy("boy", ManagerBoyType.SmartBoy);
        managerBoyService.bindParkLot(smartBoy.getId(),parkingLotA, parkingLotB);

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
        assertEquals(parkingLotB.getName(), parkingLotService.getOwnName(ticketA));
        assertEquals(parkingLotB.getName(), parkingLotService.getOwnName(ticketB));
        assertEquals(parkingLotA.getName(), parkingLotService.getOwnName(ticketC));
        assertEquals(parkingLotB.getName(), parkingLotService.getOwnName(ticketD));
    }
}
