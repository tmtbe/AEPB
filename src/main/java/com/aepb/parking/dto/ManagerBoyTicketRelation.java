package com.aepb.parking.dto;

import com.aepb.parking.repo.Table;

public class ManagerBoyTicketRelation implements Table {
    private Long id;
    private Long boyId;
    private Long ticketId;
    @Override
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getBoyId() {
        return boyId;
    }

    public void setBoyId(Long boyId) {
        this.boyId = boyId;
    }

    public Long getTicketId() {
        return ticketId;
    }

    public void setTicketId(Long ticketId) {
        this.ticketId = ticketId;
    }
}
