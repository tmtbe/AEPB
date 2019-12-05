package com.aepb.parking.repo;

import com.aepb.parking.entity.ParkingLotEntity;
import com.aepb.parking.exception.SystemError;
import com.aepb.parking.model.ParkingLot;
import com.aepb.parking.utils.SnowId;

public class ParkingLotRepo extends AbstractRepo {

    public ParkingLotEntity getParkLotEntity(Long lotId) {
        ParkingLot parkingLot = parkingLotMapper.selectById(lotId);
        if (parkingLot == null) throw new SystemError("找不到停车场");
        Long count = lotCarRelationMapper.countByLotId(lotId);
        return ParkingLotEntity.builder()
                .parkingLot(parkingLot)
                .capacitySupplier(() -> lotCarRelationMapper.countByLotId(lotId))
                .build();
    }

    public ParkingLot createParkingLot(String name, Long maxCapacity) {
        ParkingLot parkingLot = new ParkingLot();
        parkingLot.setName(name);
        parkingLot.setMaxCapacity(maxCapacity);
        parkingLot.setId(SnowId.Snow.nextId());
        parkingLotMapper.insert(parkingLot);
        return parkingLot;
    }
}
