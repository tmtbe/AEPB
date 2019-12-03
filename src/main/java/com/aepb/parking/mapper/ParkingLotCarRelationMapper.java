package com.aepb.parking.mapper;

import com.aepb.parking.model.LotCarRelation;
import com.aepb.parking.utils.Application;

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

    public List<LotCarRelation> selectByCarId(String carId) {
        return DB.getTable(LotCarRelation.class).where(LotCarRelation::getCarId, carId).list();
    }

    public List<LotCarRelation> selectAll() {
        return DB.getTable(LotCarRelation.class).list();
    }

    public void insert(LotCarRelation lotCarRelation) {
        DB.add(lotCarRelation);
    }
}
