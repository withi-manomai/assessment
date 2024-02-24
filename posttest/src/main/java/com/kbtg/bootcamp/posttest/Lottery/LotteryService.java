package com.kbtg.bootcamp.posttest.Lottery;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LotteryService {
    private final LotteryRepository lotteryRepository;

    public LotteryService(LotteryRepository lotteryRepository) {
        this.lotteryRepository = lotteryRepository;
    }

    public TicketListResponseDto getLotteryList() {
        List<String> tickets = lotteryRepository.findAllTickets();
        return new TicketListResponseDto(tickets);
    }

    public TicketResponseDto addLottery(LotteryRequestDto lotteryRequestDto) throws Exception {
        Lottery lottery = new Lottery();
        lottery.setTicket(lotteryRequestDto.getTicket());
        lottery.setPrice(lotteryRequestDto.getPrice());
        lottery.setAmount(lotteryRequestDto.getAmount());
        lotteryRepository.save(lottery);
        String ticket = lotteryRequestDto.getTicket();
        TicketResponseDto ticketResponseDto = new TicketResponseDto();
        ticketResponseDto.setTicket(ticket);
        return ticketResponseDto;
    }
}
