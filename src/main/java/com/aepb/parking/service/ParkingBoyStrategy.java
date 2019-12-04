package com.aepb.parking.service;

import com.aepb.parking.entity.ParkingLotProvider;

public interface ParkingBoyStrategy {
    void handle(ParkingLotProvider parkingLotProvider);
}
