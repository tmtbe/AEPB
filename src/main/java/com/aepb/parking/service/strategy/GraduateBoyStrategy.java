package com.aepb.parking.service.strategy;

import com.aepb.parking.entity.ParkingLotEntity;
import com.aepb.parking.entity.ParkingLotProvider;
import com.aepb.parking.exception.ParkingException;
import com.aepb.parking.service.ParkingBoyStrategy;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class GraduateBoyStrategy implements ParkingBoyStrategy {
    @Override
    public ParkingLotEntity getParkingLotEntityFromProvider(ParkingLotProvider parkingLotProvider) throws ParkingException {
        List<ParkingLotEntity> collect = parkingLotProvider.getParkingLotEntities()
                .stream().filter(n -> getSurplus(n) > 0)
                .sorted(Comparator.comparing(n -> n.getParkingLot().getId())).collect(Collectors.toList());
        if (collect.isEmpty()) {
            throw new ParkingException("没有合适的停车场");
        } else {
            return collect.get(0);
        }
    }
}
