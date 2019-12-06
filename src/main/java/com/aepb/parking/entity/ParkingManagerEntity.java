package com.aepb.parking.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@Builder
public class ParkingManagerEntity implements ParkingLotSupplier {
    private List<ParkingLotSupplier> parkingLotSuppliers;
    @Override
    public List<ParkingLotEntity> getParkingLotEntities() {
        if(parkingLotSuppliers == null) parkingLotSuppliers = new ArrayList<>();
        ArrayList<ParkingLotEntity> list = new ArrayList<>();
        parkingLotSuppliers.forEach(n -> list.addAll(n.getParkingLotEntities()));
        return list;
    }

    public void addLotSupplier(ParkingLotSupplier parkingLotSupplier) {
        if(parkingLotSuppliers == null) parkingLotSuppliers = new ArrayList<>();
        parkingLotSuppliers.add(parkingLotSupplier);
    }
}
