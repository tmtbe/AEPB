package com.aepb.parking.repo;

import com.aepb.parking.Application;
import com.aepb.parking.dto.LotCarRelation;
import com.aepb.parking.dto.ParkingLot;
import com.aepb.parking.exception.ParkingException;
import com.aepb.parking.utils.SnowId;

import java.util.HashMap;

public class LotCarRelationRepo {
    private Long countByLotId(Long lotId) {
        HashMap<Long, Table> all = HashDb.DB.getByClass(LotCarRelation.class);
        if(all==null) return 0L;
        return all.values().stream().filter(n-> ((LotCarRelation) n).getLotId().equals(lotId)).count();
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
        ParkingLot parkingLot = Application.app.getParkingLotRepo().selectParkingLotById(lotId);
        if(parkingLot == null) throw new ParkingException("停车场不存在");
        if(this.countByLotId(lotId)>=parkingLot.getMaxCapacity()){
            throw new ParkingException("停车场已满");
        }
        HashDb.DB.add(lotCarRelation);
        return lotCarRelation;
    }
}
