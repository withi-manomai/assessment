package com.kbtg.bootcamp.posttest.User;

import jakarta.persistence.*;

@Entity
@Table(name = "user_ticket")
public class UserTicket {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "userid")
    private String userId;
    @Column(name = "ticket")
    private String ticket;
    @Column(name = "amount")
    private Integer amount;

    public UserTicket() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getTicket() {
        return ticket;
    }

    public void setTicket(String ticket) {
        this.ticket = ticket;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }
    @Override
    public String toString() {
        return "UserTicket{" +
                "id=" + id +
                ", userId='" + userId + '\'' +
                ", ticket='" + ticket + '\'' +
                ", amount=" + amount +
                '}';
    }
}

