package com.aepb.parking.service.strategy;

import com.aepb.parking.entity.ParkingLotProvider;
import com.aepb.parking.service.ParkingBoyStrategy;

import java.util.Comparator;

public class GraduateBoyStrategy implements ParkingBoyStrategy {
    @Override
    public void handle(ParkingLotProvider parkingLotProvider) {
        parkingLotProvider.getParkingLotEntities().sort(Comparator.comparing(n -> n.getParkingLot().getId()));
    }
}
