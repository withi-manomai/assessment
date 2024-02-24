package com.kbtg.bootcamp.posttest.User;

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
