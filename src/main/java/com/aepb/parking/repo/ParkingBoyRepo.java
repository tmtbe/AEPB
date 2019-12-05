package com.aepb.parking.repo;

import com.aepb.parking.entity.ParkingBoyEntity;
import com.aepb.parking.enums.ParkingBoyType;
import com.aepb.parking.model.*;
import com.aepb.parking.utils.SnowId;

import java.util.List;

import static java.util.stream.Collectors.toList;

public class ParkingBoyRepo extends AbstractRepo {
    private final ParkingLotRepo parkingLotRepo;

    public ParkingBoyRepo() {
        this.parkingLotRepo = app.getComponent(ParkingLotRepo.class);
    }

    public void bindParkLot(Long boyId, List<ParkingLot> parkingLots) {
        for (ParkingLot parkingLot : parkingLots) {
            ParkingBoyLotRelation parkingBoyLotRelation = new ParkingBoyLotRelation();
            parkingBoyLotRelation.setId(SnowId.Snow.nextId());
            parkingBoyLotRelation.setBoyId(boyId);
            parkingBoyLotRelation.setLotId(parkingLot.getId());
            parkingBoyLotRelationMapper.insert(parkingBoyLotRelation);
        }
    }

    public ParkingBoy getParkingBoy(Long boyId) {
        return parkingBoyMapper.selectById(boyId);
    }

    public ParkingBoy createParkingBoy(String name, ParkingBoyType parkingBoyType) {
        ParkingBoy parkingBoy = new ParkingBoy();
        parkingBoy.setId(SnowId.Snow.nextId());
        parkingBoy.setName(name);
        parkingBoy.setType(parkingBoyType);
        parkingBoyMapper.insert(parkingBoy);
        return parkingBoy;
    }

    public void ticketBindParkingBoy(Long boyId, ParkingTicket parkingTicket) {
        ParkingBoyTicketRelation parkingBoyTicketRelation = new ParkingBoyTicketRelation();
        parkingBoyTicketRelation.setBoyId(boyId);
        parkingBoyTicketRelation.setId(SnowId.Snow.nextId());
        parkingBoyTicketRelation.setTicketId(parkingTicket.getId());
        parkingBoyTicketRelationMapper.insert(parkingBoyTicketRelation);
    }

    public ParkingBoyEntity getManageBoyEntity(Long boyId) {
        return ParkingBoyEntity.builder()
                .parkingBoy(getParkingBoy(boyId))
                .parkingLotEntities(parkingBoyLotRelationMapper.selectByBoyId(boyId)
                        .stream().map(n -> parkingLotRepo.getParkLotEntity(n.getLotId())).collect(toList()))
                .build();

    }
}