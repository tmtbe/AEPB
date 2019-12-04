package com.aepb.parking.service;

import com.aepb.parking.entity.ParkingLotEntity;
import com.aepb.parking.entity.ParkingLotProvider;
import com.aepb.parking.exception.ParkingException;

public interface ParkingBoyStrategy {
    ParkingLotEntity getParkingLotEntityFromProvider(ParkingLotProvider parkingLotProvider) throws ParkingException;

    default Long getSurplus(ParkingLotEntity parkingLotEntity) {
        return parkingLotEntity.getParkingLot().getMaxCapacity() - parkingLotEntity.getCapacity();
    }
}
