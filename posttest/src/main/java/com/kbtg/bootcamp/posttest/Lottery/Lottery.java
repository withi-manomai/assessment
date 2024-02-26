package com.kbtg.bootcamp.posttest.Lottery;

import jakarta.persistence.*;

@Entity
@Table(name="lottery")
public class Lottery {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "ticket")
    private String ticket;
    @Column(name = "price")
    private Integer price;

    @Column(name = "amount")
    private Integer amount;

    public Lottery() {

    }

    public Lottery(Long id, String ticket, Integer price, Integer amount) {
        this.id = id;
        this.ticket = ticket;
        this.price = price;
        this.amount = amount;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
    @Override
    public String toString() {
        return "Lottery {" +
                "id=" + id +
                ", price='" + price + '\'' +
                ", ticket='" + ticket + '\'' +
                ", amount=" + amount +
                '}';
    }
}
