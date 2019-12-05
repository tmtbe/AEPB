package com.aepb.parking.entity;

import com.aepb.parking.model.ParkingBoy;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ParkingBoyEntity implements ParkingLotProvider {
    private ParkingBoy parkingBoy;
    private List<ParkingLotEntity> parkingLotEntities;
}
