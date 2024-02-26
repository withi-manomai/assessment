package com.kbtg.bootcamp.posttest.Lottery;

public class LotteryTicketResponseDto {
    private String ticket;

    public LotteryTicketResponseDto() {

    }
    public LotteryTicketResponseDto(String ticket) {
        this.ticket = ticket;
    }

    public String getTicket() {
        return ticket;
    }

    public void setTicket(String ticket) {
        this.ticket = ticket;
    }
}
