package com.aepb.parking.mapper;

import com.aepb.parking.model.ParkingBoyLotRelation;

import java.util.List;

import static com.aepb.parking.utils.HashDb.DB;

public class ParkingBoyLotRelationMapper {
    public void insert(ParkingBoyLotRelation parkingBoyLotRelation) {
        DB.add(parkingBoyLotRelation);
    }

    public ParkingBoyLotRelation selectById(Long id) {
        return DB.getTable(ParkingBoyLotRelation.class)
                .where(ParkingBoyLotRelation::getId, id)
                .one();
    }

    public List<ParkingBoyLotRelation> selectByBoyId(Long boyId) {
        return DB.getTable(ParkingBoyLotRelation.class)
                .where(ParkingBoyLotRelation::getBoyId, boyId)
                .list();
    }

    public List<ParkingBoyLotRelation> selectAll() {
        return DB.getTable(ParkingBoyLotRelation.class).list();
    }

    public ParkingBoyLotRelation selectByLotId(long lotId) {
        return DB.getTable(ParkingBoyLotRelation.class)
                .where(ParkingBoyLotRelation::getLotId,lotId)
                .one();
    }
}
