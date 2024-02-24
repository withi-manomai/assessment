package com.kbtg.bootcamp.posttest.Lottery;


public class LotteryRequestDto{
    private String ticket;
    private Integer price;
    private Integer amount;

    public LotteryRequestDto() {

    }

    public String getTicket() {
        return ticket;
    }

    public void setTicket(String ticket) {
        this.ticket = ticket;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }
}
