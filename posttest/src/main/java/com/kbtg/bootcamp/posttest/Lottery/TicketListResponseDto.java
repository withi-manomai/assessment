package com.kbtg.bootcamp.posttest.Lottery;

import java.util.List;

public class TicketResponseDto {
    public List<String> tickets;

    public TicketResponseDto(List<String> tickets) {
        this.tickets = tickets;
    }

    public List<String> getTickets() {
        return tickets;
    }

    public void setTickets(List<String> tickets) {
        this.tickets = tickets;
    }
}
