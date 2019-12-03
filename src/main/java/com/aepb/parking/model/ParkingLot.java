package com.aepb.parking.model;

import com.aepb.parking.mapper.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ParkingLot implements Table {
    private Long id;
    private String name;
    private Long maxCapacity;
}
