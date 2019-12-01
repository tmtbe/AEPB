
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;

public class ParkingTicket {
    private static String prefix="TICKET";
    private String ticketString;
    private ArrayList<String> messages = new ArrayList<>();
    private Car car;

    public static ParkingTicket validate(String ticketString) throws TicketException {
        if(!ticketString.startsWith(ParkingTicket.prefix)){
            throw new TicketException("验证失败");
        }
        String[] split = ticketString.split("@");
        if(split.length<3){
            throw new TicketException("验证失败");
        }
        String md5 = crypt(split[1]);
        if(!md5.equals(split[2])){
            throw new TicketException("验证失败");
        }
        ParkingTicket parkingTicket = new ParkingTicket();
        parkingTicket.ticketString = ticketString;
        parkingTicket.messages = new ArrayList<>();
        if(split.length>3){
            for (int i = 3;i<split.length;i++){
                parkingTicket.messages.add(split[i]);
            }
        }
        return parkingTicket;
    }
    public static void destory(ParkingTicket parkingTicket) {
    }

    public void addMessage(String message){
        messages.add(message);
    }

    public void gen(Car car, long time) {
        String value = car.getCarId()+time;
        String md5 = crypt(value);
        ticketString =prefix+"@"+ value+"@"+md5;
        for(String messages : messages){
            ticketString=ticketString+"@"+messages;
        }
        this.car = car;
    }

    @Override
    public String toString() {
        return ticketString;
    }


    public static String crypt(String str) {
        if (str == null || str.length() == 0) {
            throw new IllegalArgumentException("String to encript cannot be null or zero length");
        }
        StringBuffer hexString = new StringBuffer();
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(str.getBytes());
            byte[] hash = md.digest();
            for (int i = 0; i < hash.length; i++) {
                if ((0xff & hash[i]) < 0x10) {
                    hexString.append("0" + Integer.toHexString((0xFF & hash[i])));
                } else {
                    hexString.append(Integer.toHexString(0xFF & hash[i]));
                }
            }
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return hexString.toString();
    }

    public Car getCar() {
        return car;
    }

    public ArrayList<String> getMessages() {
        return messages;
    }
}
