package com.aepb.parking.repo;

import com.aepb.parking.entity.ParkingLotEntity;
import com.aepb.parking.enums.ManagerBoyType;
import com.aepb.parking.model.*;
import com.aepb.parking.utils.SnowId;

import java.util.List;

import static java.util.stream.Collectors.toList;

public class ManagerBoyRepo extends AbstractRepo {
    private final ParkingLotRepo parkingLotRepo;

    public ManagerBoyRepo() {
        this.parkingLotRepo = app.getComponent(ParkingLotRepo.class);
    }

    public void bindParkLot(Long boyId, List<ParkingLot> parkingLots) {
        for (ParkingLot parkingLot : parkingLots) {
            ManagerBoyLotRelation managerBoyLotRelation = new ManagerBoyLotRelation();
            managerBoyLotRelation.setId(SnowId.Snow.nextId());
            managerBoyLotRelation.setBoyId(boyId);
            managerBoyLotRelation.setLotId(parkingLot.getId());
            managerBoyLotRelationMapper.insert(managerBoyLotRelation);
        }
    }

    public ManagerBoy getManagerBoy(Long boyId) {
        return managerBoyMapper.selectById(boyId);
    }

    public ManagerBoy createManagerBoy(String name, ManagerBoyType managerBoyType) {
        ManagerBoy managerBoy = new ManagerBoy();
        managerBoy.setId(SnowId.Snow.nextId());
        managerBoy.setName(name);
        managerBoy.setType(managerBoyType);
        managerBoyMapper.insert(managerBoy);
        return managerBoy;
    }

    public void ticketBindManagerBoy(Long boyId, ParkingTicket parkingTicket) {
        ManagerBoyTicketRelation managerBoyTicketRelation = new ManagerBoyTicketRelation();
        managerBoyTicketRelation.setBoyId(boyId);
        managerBoyTicketRelation.setId(SnowId.Snow.nextId());
        managerBoyTicketRelation.setTicketId(parkingTicket.getId());
        managerBoyTicketRelationMapper.insert(managerBoyTicketRelation);
    }

    public List<ParkingLotEntity> getManageParkingLot(Long boyId) {
        return managerBoyLotRelationMapper.selectByBoyId(boyId)
                .stream().map(n -> parkingLotRepo.getParkLotEntity(n.getLotId())).collect(toList());

    }
}