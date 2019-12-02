public class SmartBoy extends GraduateBoy{

    public SmartBoy(String name) {
        super(name);
    }
    @Override
    public void bindParkLot(ParkingLot... parkingLot) {
        super.bindParkLot(parkingLot);
        parkingLotList.sort((A,B)-> Long.compare(B.getEmpytyCount(), A.getEmpytyCount()));;
    }
}
