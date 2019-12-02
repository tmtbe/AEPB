package com.aepb.parking;

import com.aepb.parking.exception.ParkingException;
import com.aepb.parking.exception.TicketException;
import com.aepb.parking.service.impl.GraduateBoyService;
import com.aepb.parking.service.impl.ParkingLotService;
import com.aepb.parking.dto.ParkingTicket;
import org.junit.Before;
import org.junit.Test;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertNotNull;

public class GraduateBoyTest {
    private ParkingLotService parkingLotA;
    private ParkingLotService parkingLotB;
    private TestCar testCarA;
    private TestCar testCarB;
    private TestCar testCarC;
    private TestCar testCarD;
    private GraduateBoyService graduateBoy;
    @Before
    public void init(){
        parkingLotA = new ParkingLotService("parkA",1,2);
        parkingLotB = new ParkingLotService("parkB",1,1);

        graduateBoy = new GraduateBoyService("boyA");
        graduateBoy.bindParkLot(parkingLotA, parkingLotB);

        testCarA = new TestCar("粤A12133");
        testCarB = new TestCar("粤B12232");
        testCarC = new TestCar("粤B12234");
        testCarD = new TestCar("粤B12246");
    }
    @Test(expected = ParkingException.class)
    public void should_not_park_when_park_all_full() throws ParkingException {
        graduateBoy.park(testCarA);
        graduateBoy.park(testCarB);
        graduateBoy.park(testCarC);
        graduateBoy.park(testCarD);
    }

    @Test
    public void should_success_park() throws ParkingException {
        ParkingTicket ticket = graduateBoy.park(testCarB);
        assertNotNull(ticket);
    }

    @Test
    public void should_success_3_car_diff_park() throws ParkingException, TicketException {
        ParkingTicket ticketA = graduateBoy.park(testCarA);
        ParkingTicket ticketB = graduateBoy.park(testCarB);
        ParkingTicket ticketC = graduateBoy.park(testCarC);
        assertEquals(parkingLotB.getName(), ParkingLotService.getName(ticketC));
        assertEquals(parkingLotA.getName(), ParkingLotService.getName(ticketB));
        assertEquals(parkingLotA.getName(), ParkingLotService.getName(ticketA));
    }

    @Test
    public void should_get_ticket_info() throws ParkingException, TicketException {
        ParkingTicket ticket = graduateBoy.park(testCarB);
        assertNotNull(ticket.getMessages());
        assertEquals(parkingLotA.getName(), ParkingLotService.getName(ticket));
        assertEquals(graduateBoy.getName(), GraduateBoyService.getName(ticket));
    }

    @Test
    public void should_unPark() throws TicketException, ParkingException {
        ParkingTicket ticket = graduateBoy.park(testCarB);
        graduateBoy.unPark(ticket);
    }
    @Test(expected = TicketException.class)
    public void should_not_unPark() throws ParkingException, TicketException {
        ParkingTicket ticket = parkingLotA.park(testCarA);
        graduateBoy.unPark(ticket);
    }
}
