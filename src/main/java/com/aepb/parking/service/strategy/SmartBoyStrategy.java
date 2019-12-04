package com.aepb.parking.service.strategy;

import com.aepb.parking.entity.ParkingLotEntity;
import com.aepb.parking.entity.ParkingLotProvider;
import com.aepb.parking.service.ParkingBoyStrategy;

public class SmartBoyStrategy implements ParkingBoyStrategy {
    private Long getSurplus(ParkingLotEntity parkingLotEntity) {
        return parkingLotEntity.getParkingLot().getMaxCapacity() - parkingLotEntity.getCapacity();
    }

    @Override
    public void handle(ParkingLotProvider parkingLotProvider) {
        parkingLotProvider.getParkingLotEntities().sort((A, B) -> {
            int result = getSurplus(B).compareTo(getSurplus(A));
            if (result == 0) result = A.getParkingLot().getId().compareTo(B.getParkingLot().getId());
            return result;
        });
    }
}
