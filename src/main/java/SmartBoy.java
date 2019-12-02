public class SmartBoy extends GraduateBoy{

    public SmartBoy(String name) {
        super(name);
    }
    @Override
    public void bindParkLot(ParkingLot... parkingLot) {
        super.bindParkLot(parkingLot);
        parkingLotList.sort((A,B)-> Long.compare(B.getEmpytyCount(), A.getEmpytyCount()));;
    }

    public static String getName(ParkingTicket parkingTicket) throws TicketException {
        if (parkingTicket.getMessages().size() >= 2) {
            return parkingTicket.getMessages().get(1);
        } else {
            throw new TicketException("获取信息失败");
        }
    }
}
