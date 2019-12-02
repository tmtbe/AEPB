package com.aepb.parking.repo;

import com.aepb.parking.model.ParkingLot;

public class ParkingLotRepo {
    public void insertParkingLot(ParkingLot parkingLot) {
        HashDb.DB.add(parkingLot);
    }

    public ParkingLot selectParkingLotById(Long id) {
        return HashDb.DB.getById(ParkingLot.class, id);
    }
}
