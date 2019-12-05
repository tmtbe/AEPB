package com.aepb.parking.mapper;

import com.aepb.parking.model.ParkingBoy;

import static com.aepb.parking.utils.HashDb.DB;

public class ParkingBoyMapper {
    public void insert(ParkingBoy parkingBoy) {
        DB.add(parkingBoy);
    }

    public ParkingBoy selectById(Long id) {
        return DB.getTable(ParkingBoy.class).where(ParkingBoy::getId, id).one();
    }
}
