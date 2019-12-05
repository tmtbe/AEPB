package com.aepb.parking.entity;

import com.aepb.parking.model.ParkingLot;
import com.aepb.parking.repo.ParkingLotRepo;
import com.aepb.parking.utils.Application;
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

    public Long getCapacity() {
        Application.app.getComponent(ParkingLotRepo.class).updateParkLotCapacity(this);
        return capacity;
    }
}
