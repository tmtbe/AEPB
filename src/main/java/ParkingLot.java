import java.util.Date;
import java.util.HashSet;

public class ParkingLot implements Parking,Comparable{
    private HashSet<String> contains = new HashSet<>();
    private HashSet<String> carIds = new HashSet<>();
    private long maxContain;
    private String name;
    public ParkingLot(String name,int i, int i1) {
        this.maxContain = i*i1;
        this.name = name;
    }

    public long getMaxContain(){
        return maxContain;
    }
    public ParkingTicket park(Car car) throws ParkingException {
        if(contains.size()>=maxContain){
            throw new ParkingException("已满");
        }
        ParkingTicket parkingTicket = new ParkingTicket();
        Date date = new Date();
        parkingTicket.gen(car,date.getTime());
        if(carIds.contains(car.getCarId())){
            throw new ParkingException("此车ID已入库");
        }
        if(contains.contains(parkingTicket.toString())){
            throw new ParkingException("此车已入库");
        }
        contains.add(parkingTicket.toString());
        carIds.add(car.getCarId());
        parkingTicket.addMessage(this.getName());
        return parkingTicket;
    }

    public void unPark(ParkingTicket parkingTicket) throws ParkingException, TicketException {
        ParkingTicket.validate(parkingTicket.toString());
        if(!contains.contains(parkingTicket.toString())){
            throw new ParkingException("查无此车");
        }
        ParkingTicket.destory(parkingTicket);
        contains.remove(parkingTicket.toString());
        carIds.remove(parkingTicket.getCar().getCarId());
    }

    @Override
    public String getName() {
        return name;
    }

    public boolean isFull() {
        return contains.size()==maxContain;
    }

    public static String getName(ParkingTicket parkingTicket) throws TicketException {
        if (parkingTicket.getMessages().size() >= 1) {
            return parkingTicket.getMessages().get(0);
        } else {
            throw new TicketException("获取信息失败");
        }
    }

    @Override
    public int compareTo(Object o) {
        ParkingLot parkingLot = (ParkingLot) o;
        if(parkingLot.maxContain-parkingLot.contains.size()>this.maxContain-this.contains.size()) {
            return 1;
        }else{
            return -1;
        }
    }
}
