package com.kbtg.bootcamp.posttest.UserTicketTest;

import com.kbtg.bootcamp.posttest.LotteryTest.Lottery;
import com.kbtg.bootcamp.posttest.LotteryTest.LotteryRepository;
import com.kbtg.bootcamp.posttest.LotteryTest.LotteryService;
import com.kbtg.bootcamp.posttest.UserTicket.*;
import org.apache.coyote.BadRequestException;
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


public class UserTicketServiceTest {
    @Mock
    UserTicketRepository userTicketRepository;
    @Mock
    LotteryRepository lotteryRepository;
    @InjectMocks
    UserTicketService userTicketService;
    @InjectMocks
    LotteryService lotteryService;
    @BeforeEach
    void setUp(){
        MockitoAnnotations.initMocks(this);
    }

    @Test
    @DisplayName("buyTicket ; stockLottery != null, existingUserTicket = null ; return id : 4 ")
    void buyTicketStockLotteryNotNullAndExistingUserTicketIsNull() throws Exception {
        Lottery stockLottery = new Lottery(1L,"000111",80,5);
        Lottery lottery = new Lottery(1L,"000111",80,5);
        Lottery saveLottery = new Lottery(1L,"000111",80,4);
        UserTicket saveUserTicket = new UserTicket(1L,"0000011111","000111",4,lottery);
        when(lotteryRepository.findFirstByTicket(any())).thenReturn(stockLottery);
        when(lotteryRepository.save(any())).thenReturn(saveLottery);
        when(userTicketRepository.findFirstByUserIdAndTicket(any(),any())).thenReturn(null);
        when(userTicketRepository.save(any())).thenReturn(saveUserTicket);


        String actualInputUserId = "0000011111";
        String actualInputTicket = "000111";
        UserTicketIdResponseDto actual = new UserTicketIdResponseDto(userTicketService.buyTicket(actualInputUserId, actualInputTicket).getId());

        long expectedId = 1L;
        UserTicketIdResponseDto expected = new UserTicketIdResponseDto(expectedId);

        assertEquals(expected.getId(),actual.getId());
    }

    @Test
    @DisplayName("buyTicket ; stockLottery != null, existingUserTicket != null ; return id : 1 ")
    void buyTicketStockLotteryNotNullAndExistingUserTicketNotNull() throws Exception {
        Lottery stockLottery = new Lottery(1L,"000111",80,5);
        Lottery lottery = new Lottery(1L,"000111",80,5);
        UserTicket existingUserTicket = new UserTicket(1L,"0000011111","000111",1,lottery);
        Lottery saveLottery = new Lottery(1L,"000111",80,4);
        UserTicket saveUserTicket = new UserTicket(1L,"0000011111","000111",1,lottery);
        when(lotteryRepository.findFirstByTicket(any())).thenReturn(stockLottery);
        when(lotteryRepository.save(any())).thenReturn(saveLottery);
        when(userTicketRepository.findFirstByUserIdAndTicket(any(),any())).thenReturn(existingUserTicket);
        when(userTicketRepository.save(any())).thenReturn(saveUserTicket);


        String actualInputUserId = "0000011111";
        String actualInputTicket = "000111";
        UserTicketIdResponseDto actual = new UserTicketIdResponseDto(userTicketService.buyTicket(actualInputUserId, actualInputTicket).getId());

        long expectedId = 1L;
        UserTicketIdResponseDto expected = new UserTicketIdResponseDto(expectedId);

        assertEquals(expected.getId(),actual.getId());
    }

    @Test
    @DisplayName("buyTicket ; stockLottery = null ; throw BadRequestException ")
    void buyTicketStockLotteryIsNull() throws Exception {
        Lottery stockLottery = null;
        when(lotteryRepository.findFirstByTicket(any())).thenReturn(stockLottery);

        assertThrows(BadRequestException.class, () -> {
            String actualInputUserId = "0000011111";
            String actualInputTicket = "000999";
            userTicketService.buyTicket(actualInputUserId, actualInputTicket);
        });
    }

    @Test
    @DisplayName("buyTicket ; stock amount = 0 ; throw BadRequestException ")
    void buyTicketStockAmountIsZero() throws Exception {
        Lottery stockLottery = new Lottery(1L,"000999",80,0);
        when(lotteryRepository.findFirstByTicket(any())).thenReturn(stockLottery);

        assertThrows(BadRequestException.class, () -> {
            String actualInputUserId = "0000011111";
            String actualInputTicket = "000999";
            userTicketService.buyTicket(actualInputUserId, actualInputTicket);
        });
    }

    @Test
    @DisplayName("buyTicket ; stock amount < 0 ; throw BadRequestException ")
    void buyTicketStockAmountLessThanZero() throws Exception {
        Lottery stockLottery = new Lottery(1L,"000999",80,-1);
        when(lotteryRepository.findFirstByTicket(any())).thenReturn(stockLottery);

        assertThrows(BadRequestException.class, () -> {
            String actualInputUserId = "0000011111";
            String actualInputTicket = "000999";
            userTicketService.buyTicket(actualInputUserId, actualInputTicket);
        });
    }

    @Test
    @DisplayName("getUserBoughtTicketList ; correct userId; return tickets = [000111,000222,000333],count=3,cost=240 ")
    void getUserBoughtTicketList() throws Exception {

        Lottery lottery1 = new Lottery(1L,"000111",80,5);
        Lottery lottery2 = new Lottery(2L,"000222",80,5);
        Lottery lottery3 = new Lottery(3L,"000333",80,5);
        UserTicket userTicket1 = new UserTicket(1L,"0000011111","000111",1,lottery1);
        UserTicket userTicket2 = new UserTicket(1L,"0000011111","000222",1,lottery2);
        UserTicket userTicket3 = new UserTicket(1L,"0000011111","000333",1,lottery3);
        List<UserTicket> userBoughtTicketList;
        userBoughtTicketList = new ArrayList<>(List.of(userTicket1,userTicket2,userTicket3));
        when(userTicketRepository.findAllByUserId(any())).thenReturn(userBoughtTicketList);


        UserTicketBoughtListResponseDto actual =
                new UserTicketBoughtListResponseDto
                        (userTicketService.getUserBoughtTicketList("0000011111").getTickets()
                                ,userTicketService.getUserBoughtTicketList("0000011111").getCount()
                                ,userTicketService.getUserBoughtTicketList("0000011111").getCost());

        UserTicketBoughtListResponseDto expected =
                new UserTicketBoughtListResponseDto
                        (new String[]{"000111","000222","000333"},3,240);

        assertArrayEquals(expected.getTickets(), actual.getTickets());
        assertEquals(expected.getCount(), actual.getCount());
        assertEquals(expected.getCost(), actual.getCost());
    }

    @Test
    @DisplayName("sellTicket ; user have that ticket ; return ticket : 000111 ")
    void sellTicketUserHaveThatTicket() throws Exception {
        Lottery lottery = new Lottery(1L,"000111",80,5);
        UserTicket userTicket = new UserTicket(1L,"0000011111","000111",1,lottery);
        when(userTicketRepository.findFirstByUserIdAndTicket(any(),any())).thenReturn(userTicket);
        doNothing().when(userTicketRepository).deleteById(any());

        String actualInputUserId = "0000011111";
        String actualInputTicket = "000111";
        UserTicketResponseDto actual = new UserTicketResponseDto(userTicketService.sellTicket(actualInputUserId, actualInputTicket).getTicket());

        String expectedTicket = "000111";
        UserTicketResponseDto expected = new UserTicketResponseDto(expectedTicket);

        assertEquals(expected.getTicket(), actual.getTicket());
    }

    @Test
    @DisplayName("sellTicket ; user don't have that ticket ; throw BadRequestException ")
    void sellTicketUserDontHaveThatTicket() throws Exception {
        UserTicket userTicket = null;
        when(userTicketRepository.findFirstByUserIdAndTicket(any(),any())).thenReturn(userTicket);
        doNothing().when(userTicketRepository).deleteById(any());

        assertThrows(BadRequestException.class, () ->
        {
            String actualInputUserId = "0000011111";
            String actualInputTicket = "000111";
            userTicketService.sellTicket(actualInputUserId, actualInputTicket);
        });
    }
}
