package com.kbtg.bootcamp.posttest.User;

public class UserBoughtTicketListResponseDto {
    private String[] tickets;
    private Integer count;
    private Integer cost;

    public UserBoughtTicketListResponseDto(String[] tickets, Integer count, Integer cost) {
        this.tickets = tickets;
        this.count = count;
        this.cost = cost;
    }

    public String[] getTickets() {
        return tickets;
    }

    public void setTickets(String[] tickets) {
        this.tickets = tickets;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public Integer getCost() {
        return cost;
    }

    public void setCost(Integer cost) {
        this.cost = cost;
    }
}
