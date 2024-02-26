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
import static org.mockito.Mockito.doNothing;
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
    @DisplayName("getLotteryList ; return ticket : [000111,000222,000333]")
    void getLotteryList() throws Exception{
        List<String> tickets = List.of("000111", "000222", "000333");
        List<String> lotteries = new ArrayList<>(tickets);
        when(lotteryRepository.findAllTicketsInStock()).thenReturn(lotteries);

        LotteryTicketListResponseDto actual = lotteryService.getLotteryList();

        List<String> expectedTickets = List.of("000111", "000222", "000333");
        LotteryTicketListResponseDto expected = new LotteryTicketListResponseDto(expectedTickets);

        assertArrayEquals(expected.getTickets().toArray(), actual.getTickets().toArray());
    }

    @Test
    @DisplayName("addLottery ; The ticket has stock amount of 0 tickets ; return ticket : [000444]")
    void addLotteryAmountInStockEqualsZero() throws Exception{
        LotteryRequestDto lotteryRequestDto = new LotteryRequestDto("000444",80,5);
        when(lotteryRepository.findFirstByTicket(any())).thenReturn(null);
        Lottery lotterySave = new Lottery(4L,"000444",80,5);
        when(lotteryRepository.save(any())).thenReturn(lotterySave);
        LotteryTicketResponseDto actual = lotteryService.addLottery(lotteryRequestDto);

        String expectedTicket = "000444";
        LotteryTicketResponseDto expected = new LotteryTicketResponseDto(expectedTicket);

        assertEquals(expected.getTicket(),actual.getTicket());
    }

    @Test
    @DisplayName("addLottery ; The ticket has a stock amount greater than 0 ; return ticket: [000111]")
    void addLotteryAmountInStockMoreThanZero() throws Exception{
        LotteryRequestDto lotteryRequestDto = new LotteryRequestDto("000111",80,5);
        Lottery stockLottery = new Lottery(1L,"000111",80,1);
        when(lotteryRepository.findFirstByTicket(any())).thenReturn(stockLottery);
        Lottery lotterySave = new Lottery(1L,"000111",80,6);
        when(lotteryRepository.save(any())).thenReturn(lotterySave);

        LotteryTicketResponseDto actual = lotteryService.addLottery(lotteryRequestDto);

        String expectedTicket = "000111";
        LotteryTicketResponseDto expected = new LotteryTicketResponseDto(expectedTicket);

        assertEquals(expected.getTicket(),actual.getTicket());
    }

}