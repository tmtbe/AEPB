package com.aepb.parking.entity;

import com.aepb.parking.model.ParkingLot;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.sql.Array;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.function.LongSupplier;

@Data
@AllArgsConstructor
@Builder
public class ParkingLotEntity implements ParkingLotSupplier {
    private final ParkingLot parkingLot;
    private final LongSupplier capacitySupplier;

    public Long getCapacity() {
        return capacitySupplier.getAsLong();
    }

    @Override
    public List<ParkingLotEntity> getParkingLotEntities() {
        return Collections.singletonList(this);
    }
}
