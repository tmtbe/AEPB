package com.aepb.parking.entity;

import com.aepb.parking.model.ManagerBoy;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ManagerBoyEntity implements ParkingLotProvider {
    private ManagerBoy managerBoy;
    private List<ParkingLotEntity> parkingLotEntities;
}
