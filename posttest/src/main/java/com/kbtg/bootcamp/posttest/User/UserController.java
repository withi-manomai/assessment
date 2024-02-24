package com.kbtg.bootcamp.posttest.User;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
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
    public UserBoughtTicketListResponseDto getUserBoughtTicketList(@PathVariable String userId){
        return this.userService.getUserBoughtTicketList(userId);
    }

}
