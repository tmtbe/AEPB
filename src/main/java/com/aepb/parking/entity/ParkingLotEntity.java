package com.aepb.parking.entity;

import com.aepb.parking.model.ParkingLot;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.function.LongSupplier;

@Data
@AllArgsConstructor
@Builder
public class ParkingLotEntity {
    private final ParkingLot parkingLot;
    private final LongSupplier capacitySupplier;

    public Long getCapacity() {
        return capacitySupplier.getAsLong();
    }
}
