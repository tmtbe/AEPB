import java.util.Date;
import java.util.HashSet;

public class ParkingLot {
    private HashSet<String> contains = new HashSet<>();
    private HashSet<String> carIds = new HashSet<>();
    private long maxContain;
    public ParkingLot(int i, int i1) {
        this.maxContain = i*i1;
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
}
