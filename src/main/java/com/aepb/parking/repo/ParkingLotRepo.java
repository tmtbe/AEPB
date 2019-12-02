package com.aepb.parking.repo;

import com.aepb.parking.dto.ParkingLot;
import com.aepb.parking.dto.ParkingTicket;

public class ParkingLotRepo {
   public void insertParkingLot(ParkingLot parkingLot){
      HashDb.DB.add(parkingLot);
   }
   public ParkingLot selectParkingLotById(Long id){
      return HashDb.DB.getById(ParkingLot.class,id);
   }
}
