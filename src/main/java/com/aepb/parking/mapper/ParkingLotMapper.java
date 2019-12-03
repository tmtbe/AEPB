package com.aepb.parking.mapper;

import com.aepb.parking.model.ParkingLot;

import static com.aepb.parking.utils.HashDb.DB;

public class ParkingLotMapper {
    public void insert(ParkingLot parkingLot) {
        DB.add(parkingLot);
    }

    public ParkingLot selectById(Long id) {
        return DB.getTable(ParkingLot.class).where(ParkingLot::getId, id).one();
    }
}
