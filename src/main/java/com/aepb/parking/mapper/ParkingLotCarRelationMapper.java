package com.aepb.parking.mapper;

import com.aepb.parking.exception.ParkingException;
import com.aepb.parking.model.LotCarRelation;
import com.aepb.parking.model.ParkingLot;
import com.aepb.parking.utils.Application;
import com.aepb.parking.utils.SnowId;

import java.util.List;

import static com.aepb.parking.utils.HashDb.DB;

public class ParkingLotCarRelationMapper {
    private final ParkingLotMapper parkingLotRepo;

    public ParkingLotCarRelationMapper() {
        parkingLotRepo = Application.app.getComponent(ParkingLotMapper.class);
    }

    public Long countByLotId(Long lotId) {
        return DB.getTable(LotCarRelation.class).where(LotCarRelation::getLotId, lotId).count();
    }

    public LotCarRelation selectById(Long id) {
        return DB.getTable(LotCarRelation.class).where(LotCarRelation::getId, id).one();
    }

    public void deleteById(Long id) {
        DB.remove(LotCarRelation.class, LotCarRelation::getId, id);
    }

    public LotCarRelation insertLotCarRelationWithLotId(String carId, Long lotId) throws ParkingException {
        LotCarRelation lotCarRelation = new LotCarRelation();
        lotCarRelation.setId(SnowId.Snow.nextId());
        lotCarRelation.setCarId(carId);
        lotCarRelation.setLotId(lotId);
        ParkingLot parkingLot = parkingLotRepo.selectById(lotId);
        if (parkingLot == null) throw new ParkingException("停车场不存在");
        if (this.countByLotId(lotId) >= parkingLot.getMaxCapacity()) {
            throw new ParkingException("停车场已满");
        }
        List<LotCarRelation> tables = selectByCarId(carId);
        if (tables.size() > 0) {
            throw new ParkingException("此车已入场");
        }
        DB.add(lotCarRelation);
        return lotCarRelation;
    }

    public List<LotCarRelation> selectByCarId(String carId) {
        return DB.getTable(LotCarRelation.class).where(LotCarRelation::getCarId, carId).list();
    }

    public List<LotCarRelation> selectAll() {
        return DB.getTable(LotCarRelation.class).list();
    }
}
