package com.aepb.parking;

import com.aepb.parking.enums.ManagerBoyType;
import com.aepb.parking.model.ManagerBoy;
import com.aepb.parking.model.ManagerBoyTicketRelation;
import com.aepb.parking.model.ParkingLot;
import com.aepb.parking.exception.ParkingException;
import com.aepb.parking.exception.TicketException;
import com.aepb.parking.service.impl.ManagerBoyService;
import com.aepb.parking.service.impl.ParkingLotService;
import com.aepb.parking.model.ParkingTicket;
import org.junit.Before;
import org.junit.Test;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertNotNull;

public class GraduateBoyTest extends AbstractTest{
    private ParkingLot parkingLotA;
    private ParkingLot parkingLotB;
    private TestCar testCarA;
    private TestCar testCarB;
    private TestCar testCarC;
    private TestCar testCarD;
    private ManagerBoy graduateBoy;

    @Before
    public void setUp(){
        super.setUp();
        parkingLotA = createParkingLot("parkA",1L);
        parkingLotB = createParkingLot("parkB",2L);
        graduateBoy = createManagerBoy("boy", ManagerBoyType.GraduateBoy);
        managerBoyService.bindParkLot(graduateBoy.getId(),parkingLotA, parkingLotB);

        testCarA = new TestCar("粤A12133");
        testCarB = new TestCar("粤B12232");
        testCarC = new TestCar("粤B12234");
        testCarD = new TestCar("粤B12246");
    }
    @Test(expected = ParkingException.class)
    public void should_not_park_when_park_all_full() throws ParkingException {
        managerBoyService.park(graduateBoy.getId(),testCarA);
        managerBoyService.park(graduateBoy.getId(),testCarB);
        managerBoyService.park(graduateBoy.getId(),testCarC);
        managerBoyService.park(graduateBoy.getId(),testCarD);
    }

    @Test
    public void should_success_park() throws ParkingException {
        ParkingTicket ticket = managerBoyService.park(graduateBoy.getId(),testCarA);
        assertNotNull(ticket);
    }

    @Test
    public void should_success_3_car_diff_park() throws ParkingException, TicketException {
        ParkingTicket ticketA = managerBoyService.park(graduateBoy.getId(),testCarA);
        ParkingTicket ticketB = managerBoyService.park(graduateBoy.getId(),testCarB);
        ParkingTicket ticketC = managerBoyService.park(graduateBoy.getId(),testCarC);
        assertEquals(parkingLotA.getName(), parkingLotService.getOwnName(ticketA));
        assertEquals(parkingLotB.getName(), parkingLotService.getOwnName(ticketB));
        assertEquals(parkingLotB.getName(), parkingLotService.getOwnName(ticketC));
    }

    @Test
    public void should_get_ticket_info() throws ParkingException, TicketException {
        ParkingTicket ticket = managerBoyService.park(graduateBoy.getId(),testCarA);
        assertEquals(graduateBoy.getName(), managerBoyService.getOwnName(ticket));
        assertEquals(parkingLotA.getName(), parkingLotService.getOwnName(ticket));
    }

    @Test
    public void should_unPark() throws TicketException, ParkingException {
        ParkingTicket ticket = managerBoyService.park(graduateBoy.getId(),testCarB);
        managerBoyService.unPark(graduateBoy.getId(),ticket.getId());
    }
    @Test(expected = ParkingException.class)
    public void should_not_unPark() throws ParkingException, TicketException {
        ParkingTicket ticket = parkingLotService.park(parkingLotA.getId(),testCarA);
        managerBoyService.unPark(graduateBoy.getId(),ticket.getId());
    }
}
