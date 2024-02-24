package com.kbtg.bootcamp.posttest.UserTicket;

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
    public UserTicketIdResponseDto buyTicket(@PathVariable String userId,@PathVariable String ticket){
        return this.userService.buyTicket(userId, ticket);
    }

    @GetMapping("/users/{userId}/lotteries")
    public UserTicketBoughtListResponseDto getUserBoughtTicketList(@PathVariable String userId){
        return this.userService.getUserBoughtTicketList(userId);
    }
    @DeleteMapping("/users/{userId}/lotteries/{ticket}")
    public UserTicketResponseDto sellTicket(@PathVariable String userId,@PathVariable String ticket){
        return this.userService.sellTicket(userId,ticket);
    }

}
