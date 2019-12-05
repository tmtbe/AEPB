package com.aepb.parking.entity;

import com.aepb.parking.model.ParkingBoy;
import com.aepb.parking.model.ParkingLot;
import com.aepb.parking.model.ParkingTicket;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class TicketEntity {
    private final ParkingTicket ticket;
    private final ParkingLot parkingLot;
    private final ParkingBoy parkingBoy;
    private final String carId;
}
