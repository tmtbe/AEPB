public interface Parking {
    ParkingTicket park(Car car) throws ParkingException;
    void unPark(ParkingTicket parkingTicket) throws ParkingException, TicketException;
    String getName();
}
