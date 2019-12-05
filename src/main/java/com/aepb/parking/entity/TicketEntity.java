package com.aepb.parking.entity;

import com.aepb.parking.model.ParkingBoy;
import com.aepb.parking.model.ParkingLot;
import com.aepb.parking.model.ParkingTicket;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TicketEntity {
    private ParkingTicket ticket;
    private ParkingLot parkingLot;
    private ParkingBoy parkingBoy;
    private String carId;
}
