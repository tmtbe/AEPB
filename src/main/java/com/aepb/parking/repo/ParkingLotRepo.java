package com.aepb.parking.repo;

import com.aepb.parking.dto.ParkingLot;
import com.aepb.parking.dto.ParkingTicket;

public class ParkingLotRepo {
   public void insertTicket(ParkingLot parkingLot){
      HashDb.DB.add(parkingLot);
   }
   public ParkingLot selectTicketById(Long id){
      return HashDb.DB.getById(ParkingLot.class,id);
   }
}
