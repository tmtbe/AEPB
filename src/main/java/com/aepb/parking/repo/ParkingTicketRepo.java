package com.aepb.parking.repo;

import com.aepb.parking.dto.ParkingTicket;

public class ParkingTicketRepo {
   public void insertTicket(ParkingTicket ticket){
      HashDb.DB.add(ticket);
   }
   public ParkingTicket selectTicketById(Long id){
      return HashDb.DB.getById(ParkingTicket.class,id);
   }
}
