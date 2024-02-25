package com.kbtg.bootcamp.posttest.Lottery;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LotteryService {
    private final LotteryRepository lotteryRepository;

    public LotteryService(LotteryRepository lotteryRepository) {
        this.lotteryRepository = lotteryRepository;
    }

    public LotteryTicketListResponseDto getLotteryList() {
        List<String> tickets = lotteryRepository.findAllTicketsInStock();
        return new LotteryTicketListResponseDto(tickets);
    }

    public LotteryTicketResponseDto addLottery(LotteryRequestDto lotteryRequestDto) throws Exception {
        Lottery stockLottery = lotteryRepository.findFirstByTicket(lotteryRequestDto.getTicket());
        String ticket;
        if(stockLottery==null) {
            Lottery lottery = new Lottery();
            lottery.setTicket(lotteryRequestDto.getTicket());
            lottery.setPrice(lotteryRequestDto.getPrice());
            lottery.setAmount(lotteryRequestDto.getAmount());
            lotteryRepository.save(lottery);
            ticket = lotteryRequestDto.getTicket();
        }else {
            stockLottery.setAmount(stockLottery.getAmount()+lotteryRequestDto.getAmount());
            lotteryRepository.save(stockLottery);
            ticket = stockLottery.getTicket();

        }
        LotteryTicketResponseDto lotteryTicketResponseDto = new LotteryTicketResponseDto();
        lotteryTicketResponseDto.setTicket(ticket);
        return lotteryTicketResponseDto;
    }
}
