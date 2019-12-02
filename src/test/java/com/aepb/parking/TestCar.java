package com.aepb.parking;

import com.aepb.parking.service.Car;

class TestCar implements Car {
    private String carId;
    public TestCar(String carId){
        this.carId = carId;
    }
    @Override
    public String getCarId() {
        return carId;
    }
}