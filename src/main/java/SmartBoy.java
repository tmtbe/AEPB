import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedHashMap;

public class SmartBoy extends GraduateBoy{

    public SmartBoy(String name) {
        super(name);
    }
    @Override
    public void bindParkLot(ParkingLot... parkingLot) {
        super.bindParkLot(parkingLot);
        Collections.sort(parkingLotList);
    }

    public static String getName(ParkingTicket parkingTicket) throws TicketException {
        if (parkingTicket.getMessages().size() >= 2) {
            return parkingTicket.getMessages().get(1);
        } else {
            throw new TicketException("获取信息失败");
        }
    }
}
