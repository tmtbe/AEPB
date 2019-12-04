package com.aepb.parking.entity;

import java.util.List;
import java.util.stream.Collectors;

public interface ParkingLotProvider {
    List<ParkingLotEntity> getParkingLotEntities();

    default List<ParkingLotEntity> getNotFullParkingLotEntities() {
        return getParkingLotEntities().stream().filter(n -> getSurplus(n) > 0).collect(Collectors.toList());
    }

    default Long getSurplus(ParkingLotEntity parkingLotEntity) {
        return parkingLotEntity.getParkingLot().getMaxCapacity() - parkingLotEntity.getCapacity();
    }
}
