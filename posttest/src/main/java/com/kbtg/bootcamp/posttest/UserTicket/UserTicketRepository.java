package com.kbtg.bootcamp.posttest.UserTicket;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserTicketRepository extends JpaRepository<UserTicket,Long> {
    @Query("SELECT ut FROM UserTicket ut WHERE ut.userId = :userId AND ut.ticket = :ticket ORDER BY ut.id ASC")
    UserTicket findFirstByUserIdAndTicket(@Param("userId") String userId, @Param("ticket") String ticket);
    @Query("SELECT ut FROM UserTicket ut LEFT JOIN FETCH ut.lottery WHERE ut.userId = :userId")
    List<UserTicket> findAllByUserId(@Param("userId") String userId);
}
