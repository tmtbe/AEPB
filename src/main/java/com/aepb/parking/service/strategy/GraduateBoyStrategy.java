package com.aepb.parking.service.strategy;

import com.aepb.parking.entity.ParkingLotEntity;
import com.aepb.parking.entity.ParkingLotSupplier;
import com.aepb.parking.exception.ParkingException;
import com.aepb.parking.service.ParkingBoyStrategy;

import java.util.Comparator;

public class GraduateBoyStrategy implements ParkingBoyStrategy {
    @Override
    public ParkingLotEntity getParkingLotEntityFromProvider(ParkingLotSupplier parkingLotSupplier) throws ParkingException {
        return parkingLotSupplier.getNotFullParkingLotEntities()
                .stream()
                .min(Comparator.comparing(n -> n.getParkingLot().getId()))
                .orElseThrow(() -> new ParkingException("没有合适的停车场"));
    }
}
