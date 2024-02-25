package com.kbtg.bootcamp.posttest.UserTicket;

import com.kbtg.bootcamp.posttest.LotteryTest.Lottery;
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
    @ManyToOne
    @JoinColumn(name = "ticket", referencedColumnName = "ticket", insertable = false, updatable = false)
    private Lottery lottery;

    public UserTicket() {

    }

    public Lottery getLottery() {
        return lottery;
    }

    public void setLottery(Lottery lottery) {
        this.lottery = lottery;
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
                ", lottery=" + (lottery != null ? lottery.toString() : "null") +
                '}';
    }
}

