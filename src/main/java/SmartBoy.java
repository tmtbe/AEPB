public class SmartBoy extends GraduateBoy{

    public SmartBoy(String name) {
        super(name);
    }

    @Override
    public ParkingTicket park(Car car) throws ParkingException {
        parkingLotList.sort((A,B)-> Long.compare(B.getEmpytyCount(), A.getEmpytyCount()));
        return super.park(car);
    }
}
