import org.junit.Before;
import org.junit.Test;

import static junit.framework.TestCase.*;

public class GraduateBoyTest {
    private ParkingLot parkingLotA;
    private ParkingLot parkingLotB;
    private TestCar testCarA;
    private TestCar testCarB;
    private TestCar testCarC;
    private TestCar testCarD;
    private GraduateBoy graduateBoy;
    @Before
    public void init(){
        parkingLotA = new ParkingLot("parkA",1,2);
        parkingLotB = new ParkingLot("parkB",1,1);

        graduateBoy = new GraduateBoy("boyA");
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
        assertEquals(parkingLotB.getName(),ParkingLot.getName(ticketC));
        assertEquals(parkingLotA.getName(),ticketB.getMessages().get(0));
        assertEquals(parkingLotA.getName(),ticketA.getMessages().get(0));
    }

    @Test
    public void should_get_ticket_info() throws ParkingException {
        ParkingTicket ticket = graduateBoy.park(testCarB);
        assertNotNull(ticket.getMessages());
        assertEquals(parkingLotA.getName(),ticket.getMessages().get(0));
        assertEquals(graduateBoy.getName(),ticket.getMessages().get(1));
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
