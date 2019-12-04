package com.aepb.parking.service.strategy;

import com.aepb.parking.entity.ParkingLotEntity;
import com.aepb.parking.entity.ParkingLotProvider;
import com.aepb.parking.exception.ParkingException;
import com.aepb.parking.service.ParkingBoyStrategy;

import java.util.List;
import java.util.stream.Collectors;

public class SmartBoyStrategy implements ParkingBoyStrategy {


    @Override
    public ParkingLotEntity getParkingLotEntityFromProvider(ParkingLotProvider parkingLotProvider) throws ParkingException {
        List<ParkingLotEntity> collect = parkingLotProvider.getParkingLotEntities()
                .stream().filter(n -> getSurplus(n) > 0).sorted((A, B) -> {
            int result = getSurplus(B).compareTo(getSurplus(A));
            if (result == 0) result = A.getParkingLot().getId().compareTo(B.getParkingLot().getId());
            return result;
                }).collect(Collectors.toList());
        if (collect.isEmpty()) throw new ParkingException("没有合适的停车场");
        return collect.get(0);
    }
}
