package com.aepb.parking.service;

import com.aepb.parking.entity.ParkingLotEntity;
import com.aepb.parking.entity.ParkingLotSupplier;
import com.aepb.parking.exception.ParkingException;

public interface ParkingBoyStrategy {
    ParkingLotEntity getParkingLotEntityFromProvider(ParkingLotSupplier parkingLotSupplier) throws ParkingException;

    default Long getSurplus(ParkingLotEntity parkingLotEntity) {
        return parkingLotEntity.getParkingLot().getMaxCapacity() - parkingLotEntity.getCapacity();
    }
}
