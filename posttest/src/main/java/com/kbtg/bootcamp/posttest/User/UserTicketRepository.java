package com.kbtg.bootcamp.posttest.User;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserTicketRepository extends JpaRepository<UserTicket,Long> {
    @Query("SELECT ut FROM UserTicket ut WHERE ut.userId = :userId AND ut.ticket = :ticket ORDER BY ut.id ASC")
    UserTicket findFirstByUserIdAndTicket(@Param("userId") String userId, @Param("ticket") String ticket);
}
