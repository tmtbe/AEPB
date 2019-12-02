import org.junit.Before;
import org.junit.Test;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertNotNull;

public class SmartBoyTest {
    private ParkingLot parkingLotA;
    private ParkingLot parkingLotB;
    private ParkingLot parkingLotC;
    private TestCar testCarA;
    private SmartBoy smartBoy;
    @Before
    public void init(){
        parkingLotA = new ParkingLot("parkA",1,1);
        parkingLotB = new ParkingLot("parkB",1,10);
        parkingLotC = new ParkingLot("parkC",1,2);

        smartBoy = new SmartBoy("boyA");
        smartBoy.bindParkLot(parkingLotA, parkingLotB, parkingLotC);

        testCarA = new TestCar("粤A12133");
    }
    @Test
    public void should_select_most_empty_lot() throws ParkingException, TicketException {
        ParkingTicket park = smartBoy.park(testCarA);
        String name = ParkingLot.getName(park);
        assertEquals(parkingLotB.getName(),name);
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
