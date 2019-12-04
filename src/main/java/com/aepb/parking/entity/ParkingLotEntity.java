package com.aepb.parking.entity;

import com.aepb.parking.model.ParkingLot;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Collections;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ParkingLotEntity implements ParkingLotProvider {
    private ParkingLot parkingLot;
    private Long capacity;

    @Override
    public List<ParkingLotEntity> getParkingLotEntities() {
        return Collections.singletonList(this);
    }
}
