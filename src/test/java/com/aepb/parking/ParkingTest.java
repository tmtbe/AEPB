package com.aepb.parking;

import com.aepb.parking.model.ParkingLot;
import com.aepb.parking.exception.ParkingException;
import com.aepb.parking.exception.TicketException;
import com.aepb.parking.model.ParkingTicket;
import com.aepb.parking.utils.SnowId;
import org.junit.Before;
import org.junit.Test;

import static junit.framework.TestCase.assertNotNull;
import static junit.framework.TestCase.assertNotSame;

public class ParkingTest extends AbstractTest {
    private ParkingLot parkingLot;
    private TestCar testCarA;
    private TestCar testCarB;
    @Before
    public void setUp(){
        super.setUp();
        parkingLot = createParkingLot("park",100L);
        testCarA = new TestCar("粤A12133");
        testCarB = new TestCar("粤B12232");
    }
    @Test
    public void should_return_ticket() throws ParkingException {
        ParkingTicket parkingTicket = parkingLotService.park(parkingLot.getId(),testCarA);
        assertNotNull(parkingTicket);
    }

    @Test(expected = ParkingException.class)
    public void should_fail_double_car_id_ticket() throws ParkingException {
        parkingLotService.park(parkingLot.getId(),testCarA);
        parkingLotService.park(parkingLot.getId(),testCarA);
    }

    @Test(expected = ParkingException.class)
    public void should_fail_ticket_with_full_parking() throws ParkingException {
        for(int i=0;i<=parkingLot.getMaxCapacity();i++) {
            parkingLotService.park(parkingLot.getId(),testCarA);
        }
    }

    @Test
    public void should_create_ticket(){
        ParkingTicket parkingTicketA = new ParkingTicket();
        parkingTicketA.create(SnowId.Snow.nextId());
        ParkingTicket parkingTicketB = new ParkingTicket();
        parkingTicketB.create(SnowId.Snow.nextId());
        assertNotSame(parkingTicketA.getId(),parkingTicketB.getId());
    }

    @Test
    public void should_return_success_get_car() throws ParkingException, TicketException {
        ParkingTicket ticket = parkingLotService.park(parkingLot.getId(),testCarA);
        parkingLotService.unPark(parkingLot.getId(),ticket.getId());
    }
    @Test(expected = ParkingException.class)
    public void should_return_fail_double_get_car() throws ParkingException, TicketException {
        ParkingTicket ticket = parkingLotService.park(parkingLot.getId(),testCarA);
        parkingLotService.unPark(parkingLot.getId(),ticket.getId());
        parkingLotService.unPark(parkingLot.getId(),ticket.getId());
    }
}
