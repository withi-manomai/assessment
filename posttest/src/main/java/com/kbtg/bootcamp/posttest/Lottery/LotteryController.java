package com.kbtg.bootcamp.posttest.Lottery;

import jakarta.validation.Valid;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LotteryController {
    private final LotteryService lotteryService;

    public LotteryController(LotteryService lotteryService) {
        this.lotteryService = lotteryService;
    }

    @GetMapping("/lotteries")
    public LotteryTicketListResponseDto getLotteryList(){
        return this.lotteryService.getLotteryList();
    }
    @PostMapping("/admin/lotteries")
    @PreAuthorize("hasRole('ADMIN')")
    public LotteryTicketResponseDto addLottery(@Valid @RequestBody LotteryRequestDto lotteryRequestDto) throws Exception{
        return this.lotteryService.addLottery(lotteryRequestDto);
    }


}



