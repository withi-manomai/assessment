package com.kbtg.bootcamp.posttest.LotteryTest;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

class LotteryServiceTest {
    @Mock
    LotteryRepository lotteryRepository;
    @InjectMocks
    LotteryService lotteryService;
    @BeforeEach
    void setUp(){
        MockitoAnnotations.initMocks(this);
    }

    @Test
    @DisplayName("getLotteryList : should return ticket : [000111,000222,000333]")
    void getLotteryList() throws Exception{
        List<String> lotteries = new ArrayList<>(List.of("000111","000222","000333"));
        when(lotteryRepository.findAllTicketsInStock()).thenReturn(lotteries);

        LotteryTicketListResponseDto actual = lotteryService.getLotteryList();

        LotteryTicketListResponseDto expected = new LotteryTicketListResponseDto(List.of("000111","000222","000333"));
        assertArrayEquals(expected.getTickets().toArray(), actual.getTickets().toArray());
    }

    @Test
    @DisplayName("addLottery : The ticket has stock amount of 0 tickets; should return ticket : [000111]")
    void addLotteryAmountInStockEqualsZero() throws Exception{
        LotteryRequestDto lotteryRequestDto = new LotteryRequestDto("000111",80,5);
        when(lotteryRepository.findFirstByTicket(any())).thenReturn(null);

        LotteryTicketResponseDto actual = lotteryService.addLottery(lotteryRequestDto);

        LotteryTicketResponseDto expected = new LotteryTicketResponseDto("000111");

        assertEquals(expected.getTicket(),actual.getTicket());
    }

    @Test
    @DisplayName("addLottery: The ticket has a stock amount greater than 0; should return ticket: [000111]")
    void addLotteryAmountInStockMoreThanZero() throws Exception{
        LotteryRequestDto lotteryRequestDto = new LotteryRequestDto("000111",80,5);
        Lottery stockLottery = new Lottery(1L,"000111",80,1);
        when(lotteryRepository.findFirstByTicket(any())).thenReturn(stockLottery);

        LotteryTicketResponseDto actual = lotteryService.addLottery(lotteryRequestDto);

        LotteryTicketResponseDto expected = new LotteryTicketResponseDto("000111");

        assertEquals(expected.getTicket(),actual.getTicket());
    }
}