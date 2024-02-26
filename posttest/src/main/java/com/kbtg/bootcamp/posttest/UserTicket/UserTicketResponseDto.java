package com.kbtg.bootcamp.posttest.UserTicket;

public class UserTicketResponseDto {
    private String ticket;

    public UserTicketResponseDto(String ticket) {
        this.ticket = ticket;
    }

    public String getTicket() {
        return ticket;
    }

    public void setTicket(String ticket) {
        this.ticket = ticket;
    }
}
