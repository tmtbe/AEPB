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
    private ParkingLot parkingLot;
    private final LongSupplier LongSupplier;

    public Long getCapacity() {
        return LongSupplier.getAsLong();
    }
}
