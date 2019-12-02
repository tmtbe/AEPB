package com.aepb.parking.repo;

import com.aepb.parking.dto.LotCarRelation;
import com.aepb.parking.dto.ManagerBoyLotRelation;

public class LotCarRelationRepo {
    public void insertLotCarRelation(LotCarRelation lotCarRelation) {
        HashDb.DB.add(lotCarRelation);
    }

    public LotCarRelation selectLotCarRelationById(Long id) {
        return HashDb.DB.getById(LotCarRelation.class, id);
    }

    public void deleteLotCarRelationById(Long id) {
        HashDb.DB.remove(LotCarRelation.class, id);
    }
}
