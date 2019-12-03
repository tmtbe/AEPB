package com.aepb.parking.entity;

import com.aepb.parking.model.ParkingLot;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ParkingLotEntity {
    private ParkingLot parkingLot;
    private Long capacity;
}
