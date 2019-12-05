package com.aepb.parking.entity;

import com.aepb.parking.model.ParkingBoy;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
@Builder
public class ParkingBoyEntity implements ParkingLotSupplier {
    private final ParkingBoy parkingBoy;
    private final List<ParkingLotEntity> parkingLotEntities;
}
