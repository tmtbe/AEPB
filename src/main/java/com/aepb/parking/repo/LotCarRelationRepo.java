package com.aepb.parking.repo;

import com.aepb.parking.Application;
import com.aepb.parking.model.LotCarRelation;
import com.aepb.parking.model.ParkingLot;
import com.aepb.parking.exception.ParkingException;
import com.aepb.parking.utils.SnowId;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class LotCarRelationRepo {
    private final ParkingLotRepo parkingLotRepo;
    public LotCarRelationRepo(){
        parkingLotRepo = Application.app.getComponent(ParkingLotRepo.class);
    }
    public Long countByLotId(Long lotId) {
        return selectAll().stream().filter(n -> ((LotCarRelation) n).getLotId().equals(lotId)).count();
    }

    public LotCarRelation selectLotCarRelationById(Long id) {
        return HashDb.DB.getById(LotCarRelation.class, id);
    }

    public void deleteLotCarRelationById(Long id) {
        HashDb.DB.remove(LotCarRelation.class, id);
    }

    public LotCarRelation insertLotCarRelationWithLotId(String carId, Long lotId) throws ParkingException {
        LotCarRelation lotCarRelation = new LotCarRelation();
        lotCarRelation.setId(SnowId.Snow.nextId());
        lotCarRelation.setCarId(carId);
        lotCarRelation.setLotId(lotId);
        ParkingLot parkingLot = parkingLotRepo.selectParkingLotById(lotId);
        if (parkingLot == null) throw new ParkingException("停车场不存在");
        if (this.countByLotId(lotId) >= parkingLot.getMaxCapacity()) {
            throw new ParkingException("停车场已满");
        }
        List<Table> tables = selectByCarId(carId);
        if(tables.size()>0){
            throw new ParkingException("此车已入场");
        }
        HashDb.DB.add(lotCarRelation);
        return lotCarRelation;
    }

    public List<Table> selectByCarId(String carId) {
       return selectAll().stream().filter(n-> ((LotCarRelation)n).getCarId().equals(carId)).collect(Collectors.toList());
    }

    public List<Table> selectAll(){
       return new ArrayList<>(HashDb.DB.getByClass(LotCarRelation.class).values());
    }
}
