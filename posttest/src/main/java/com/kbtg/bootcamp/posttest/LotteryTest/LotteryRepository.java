package com.kbtg.bootcamp.posttest.LotteryTest;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LotteryRepository extends JpaRepository<Lottery,Long> {
    @Query("SELECT lt FROM Lottery lt WHERE lt.ticket = :ticket")
    Lottery findFirstByTicket(@Param("ticket") String ticket);

    @Query("SELECT lt.ticket FROM Lottery lt WHERE lt.amount>0")
    List<String> findAllTicketsInStock();


}
