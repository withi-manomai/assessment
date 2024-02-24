package com.kbtg.bootcamp.posttest.Lottery;

import java.util.List;

public class LotteryTicketListResponseDto {
    public List<String> tickets;

    public LotteryTicketListResponseDto(List<String> tickets) {
        this.tickets = tickets;
    }

    public List<String> getTickets() {
        return tickets;
    }

    public void setTickets(List<String> tickets) {
        this.tickets = tickets;
    }
}
