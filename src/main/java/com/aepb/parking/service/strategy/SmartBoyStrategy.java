package com.aepb.parking.service.strategy;

import com.aepb.parking.entity.ParkingLotEntity;
import com.aepb.parking.entity.ParkingLotSupplier;
import com.aepb.parking.exception.ParkingException;
import com.aepb.parking.service.ParkingBoyStrategy;

public class SmartBoyStrategy implements ParkingBoyStrategy {


    @Override
    public ParkingLotEntity getParkingLotEntityFromProvider(ParkingLotSupplier parkingLotSupplier) throws ParkingException {
        return parkingLotSupplier.getNotFullParkingLotEntities()
                .stream()
                .min((A, B) -> {
                    int result = getSurplus(B).compareTo(getSurplus(A));
                    if (result == 0) result = A.getParkingLot().getId().compareTo(B.getParkingLot().getId());
                    return result;
                })
                .orElseThrow(() -> new ParkingException("没有合适的停车场"));
    }
}
