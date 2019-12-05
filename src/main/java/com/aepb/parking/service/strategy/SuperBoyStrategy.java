package com.aepb.parking.service.strategy;

import com.aepb.parking.entity.ParkingLotEntity;
import com.aepb.parking.entity.ParkingLotSupplier;
import com.aepb.parking.exception.ParkingException;
import com.aepb.parking.service.ParkingBoyStrategy;

public class SuperBoyStrategy implements ParkingBoyStrategy {


    @Override
    public ParkingLotEntity getParkingLotEntityFromProvider(ParkingLotSupplier parkingLotSupplier) throws ParkingException {
        return parkingLotSupplier.getNotFullParkingLotEntities()
                .stream()
                .min((A, B) -> {
                    int result = getEmptyRate(B).compareTo(getEmptyRate(A));
                    if (result == 0) result = A.getParkingLot().getId().compareTo(B.getParkingLot().getId());
                    return result;
                })
                .orElseThrow(() -> new ParkingException("没有合适的停车场"));
    }

    public Float getEmptyRate(ParkingLotEntity parkingLotEntity) {
        return Float.valueOf(getSurplus(parkingLotEntity)) / Float.valueOf(parkingLotEntity.getParkingLot().getMaxCapacity());
    }
}
