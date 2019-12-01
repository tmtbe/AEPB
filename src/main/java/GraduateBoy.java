import java.util.*;

public class GraduateBoy implements Parking{
    private String name;
    private LinkedHashMap<String,ParkingLot> parkingLotHashMap = new LinkedHashMap<>();
    private ArrayList<ParkingLot> parkingLotList = new ArrayList<>();
    public GraduateBoy(String name){
        this.name = name;
    }
    @Override
    public ParkingTicket park(Car car) throws ParkingException {
        ParkingTicket parkingTicket = null;
        for (ParkingLot parkingLot:parkingLotList) {
            if(parkingLot.isFull()) continue;
            parkingTicket =  parkingLot.park(car);
            break;
        }
        if(parkingTicket!=null) {
            parkingTicket.addMessage(this.name);
            return parkingTicket;
        }else{
            throw new ParkingException("没有停车位了");
        }
    }

    @Override
    public void unPark(ParkingTicket parkingTicket) throws ParkingException, TicketException {
        if(!this.name.equals(GraduateBoy.getName(parkingTicket))){
            throw new ParkingException("拒绝办理");
        };
        ParkingLot parkingLot = parkingLotHashMap.get(ParkingLot.getName(parkingTicket));
        if(parkingLot==null){
            throw new ParkingException("拒绝办理");
        }
        parkingLot.unPark(parkingTicket);
    }

    @Override
    public String getName() {
        return name;
    }


    public void bindParkLot(ParkingLot ... parkingLot) {
        for (ParkingLot lot : parkingLot) {
            this.parkingLotHashMap.put(lot.getName(),lot);
            this.parkingLotList.add(lot);
        }
    }

    public static String getName(ParkingTicket parkingTicket) throws TicketException {
        if (parkingTicket.getMessages().size() >= 2) {
            return parkingTicket.getMessages().get(1);
        } else {
            throw new TicketException("获取信息失败");
        }
    }
}
