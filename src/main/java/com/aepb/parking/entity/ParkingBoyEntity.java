package com.aepb.parking.entity;

import com.aepb.parking.model.ParkingBoy;
import com.aepb.parking.repo.ParkingLotRepo;
import com.aepb.parking.utils.Application;
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

    public List<ParkingLotEntity> getParkingLotEntities() {
        parkingLotEntities.forEach(n -> {
            Application.app.getComponent(ParkingLotRepo.class).updateParkLotEntity(n);
        });
        return parkingLotEntities;
    }
}
