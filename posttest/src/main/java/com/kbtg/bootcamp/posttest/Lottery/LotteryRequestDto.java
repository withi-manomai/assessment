package com.kbtg.bootcamp.posttest.Lottery;


import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class LotteryRequestDto{
    @NotNull
    @Size(min=6,max=6,message = "the number digits of ticket should be 6 digit")
    private String ticket;
    @NotNull
    @Min(value = 1,message = "Price must be greater than 0")
    private Integer price;
    @NotNull
    @Min(value=1,message = "The amount must greater than 0")
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
