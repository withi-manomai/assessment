package com.kbtg.bootcamp.posttest.Lottery;

import java.util.List;

public class TicketListResponseDto {
    public List<String> tickets;

    public TicketListResponseDto(List<String> tickets) {
        this.tickets = tickets;
    }

    public List<String> getTickets() {
        return tickets;
    }

    public void setTickets(List<String> tickets) {
        this.tickets = tickets;
    }
}
