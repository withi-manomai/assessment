package com.kbtg.bootcamp.posttest.UserTicket;

import jakarta.validation.constraints.Size;
import org.apache.coyote.BadRequestException;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserTicketController {

    private final UserTicketService userService;

    public UserTicketController(UserTicketService userService) {
        this.userService = userService;
    }

    @GetMapping("/users")
    public List<UserTicket> getUserTicketList(){
        return this.userService.getUserList();
    }

    @PostMapping("/users/{userId}/lotteries/{ticket}")
    public UserTicketIdResponseDto buyTicket
            (@PathVariable
             @Size(min = 10, max = 10, message = "UserId should be a 10-digit number")
             String userId
            ,@PathVariable
             @Size(min =6,max = 6,message = "Ticket should be a 6-digit number")
             String ticket) throws Exception{
        return this.userService.buyTicket(userId, ticket);
    }

    @GetMapping("/users/{userId}/lotteries")
    public UserTicketBoughtListResponseDto getUserBoughtTicketList
            (@PathVariable
             @Size(min = 10, max = 10, message = "UserId should be a 10-digit number")
             String userId)throws Exception{
        return this.userService.getUserBoughtTicketList(userId);
    }
    @DeleteMapping("/users/{userId}/lotteries/{ticket}")
    public UserTicketResponseDto sellTicket
            (@PathVariable
             @Size(min = 10, max = 10, message = "UserId should be a 10-digit number")
             String userId
             ,@PathVariable
             @Size(min =6,max = 6,message = "Ticket should be a 6-digit number")
             String ticket) throws BadRequestException {
        return this.userService.sellTicket(userId,ticket);
    }

}
