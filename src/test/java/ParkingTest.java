import org.junit.Before;
import org.junit.Test;

import java.util.Date;

import static junit.framework.TestCase.assertNotNull;
import static junit.framework.TestCase.assertNotSame;

public class ParkingTest {
    private ParkingLot parkingLot;
    private TestCar testCarA;
    private TestCar testCarB;
    @Before
    public void init(){
        parkingLot = new ParkingLot("park",10,10);
        testCarA = new TestCar("粤A12133");
        testCarB = new TestCar("粤B12232");
    }
    @Test
    public void should_return_ticket() throws ParkingException {
        ParkingTicket parkingTicket = parkingLot.park(testCarA);
        assertNotNull(parkingTicket);
    }

    @Test(expected = ParkingException.class)
    public void should_fail_double_car_id_ticket() throws ParkingException {
        parkingLot.park(testCarA);
        parkingLot.park(testCarA);
    }

    @Test(expected = ParkingException.class)
    public void should_fail_ticket_with_full_parking() throws ParkingException {
        for(int i=0;i<=parkingLot.getMaxContain();i++) {
            parkingLot.park(testCarA);
        }
    }

    @Test
    public void should_create_ticket(){
        ParkingTicket parkingTicket = new ParkingTicket();
        Date date = new Date();
        parkingTicket.gen(testCarA,date.getTime());
        String oneTicketString = parkingTicket.toString();
        parkingTicket.gen(testCarB,date.getTime());
        String otherTicketString = parkingTicket.toString();
        assertNotSame(oneTicketString,otherTicketString);
    }

    @Test(expected = TicketException.class)
    public void validate_ticket_fast_fail() throws TicketException {
        String ticketString = "";
        ParkingTicket.validate(ticketString);
    }

    @Test(expected = TicketException.class)
    public void validate_ticket_fail() throws TicketException {
        String ticketString = "TICKET-abcvc";
        ParkingTicket.validate(ticketString);
    }

    @Test()
    public void validate_ticket_success() throws TicketException {
        String ticketString = "TICKET@粤A121331575183928804@463d9b9ca23d2a6264f3c68291b2f6d0";
        ParkingTicket.validate(ticketString);
    }

    @Test
    public void should_return_success_get_car() throws ParkingException, TicketException {
        ParkingTicket ticket = parkingLot.park(testCarA);
        parkingLot.unPark(ticket);
    }
    @Test(expected = ParkingException.class)
    public void should_return_fail_double_get_car() throws ParkingException, TicketException {
        ParkingTicket ticket = parkingLot.park(testCarA);
        parkingLot.unPark(ticket);
        parkingLot.unPark(ticket);
    }

    class TestCar implements Car{
        private String carId;
        public TestCar(String carId){
            this.carId = carId;
        }
        @Override
        public String getCarId() {
            return carId;
        }
    }
}
