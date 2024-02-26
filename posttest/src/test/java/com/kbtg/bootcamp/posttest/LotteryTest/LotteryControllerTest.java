package com.kbtg.bootcamp.posttest.LotteryTest;

import com.kbtg.bootcamp.posttest.Lottery.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.lenient;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
class LotteryControllerTest {
    MockMvc mockMvc;
    @Mock
    LotteryService lotteryService;
    @BeforeEach
    void setUp(){
        LotteryController lotteryController = new LotteryController(lotteryService);
        mockMvc = MockMvcBuilders.standaloneSetup(lotteryController).build();
    }

    @Test
    @DisplayName("GET: /lotteries ; return tickets: [000111,000222,000333]")
    void getLotteryList() throws Exception {
        List<String> tickets = List.of("000111", "000222", "000333");
        List<String> lotteries = new ArrayList<>(tickets);
        LotteryTicketListResponseDto lotteryTicketListResponseDto = new LotteryTicketListResponseDto(lotteries);

        when(lotteryService.getLotteryList()).thenReturn(lotteryTicketListResponseDto);

        mockMvc.perform(get("/lotteries"))
                .andExpect(jsonPath("$.tickets", is(Arrays.asList("000111", "000222", "000333"))))
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("POST: /admin/lotteries ; correct lottery ; return ticket : 000111")
    void addLottery() throws Exception {
        LotteryRequestDto lotteryRequestDto = new LotteryRequestDto("000111",80,5);
        String ticketJson = "{\"ticket\":\"000111\",\"price\":80,\"amount\":5}";

        String ticket = lotteryRequestDto.getTicket();
        LotteryTicketResponseDto lotteryTicketResponseDto = new LotteryTicketResponseDto(ticket);

        when(lotteryService.addLottery(any())).thenReturn(lotteryTicketResponseDto);

        mockMvc.perform(post("/admin/lotteries")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(ticketJson))
                .andExpect(jsonPath("$.ticket", is("000111")))
                .andExpect(status().isOk());
    }
    @Test
    @DisplayName("POST: /admin/lotteries ; lottery > 6 digit ; throw BadRequestException")
    void addLotteryBadRequestDigitMoreThanSix() throws Exception {
        LotteryRequestDto lotteryRequestDto = new LotteryRequestDto("0001111",80,5);
        String ticketRequestJson = "{\"ticket\":\"0001111\",\"price\":80,\"amount\":5}";

        String ticket = lotteryRequestDto.getTicket();
        LotteryTicketResponseDto lotteryTicketResponseDto = new LotteryTicketResponseDto(ticket);

        lenient().when(lotteryService.addLottery(any())).thenReturn(lotteryTicketResponseDto);

        mockMvc.perform(post("/admin/lotteries")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(ticketRequestJson))
                .andExpect(status().isBadRequest());
    }

    @Test
    @DisplayName("POST: /admin/lotteries ; lottery < 6 digit ; throw BadRequestException")
    void addLotteryBadRequestTicketDigitLessThanSix() throws Exception {
        LotteryRequestDto lotteryRequestDto = new LotteryRequestDto("00011",80,5);
        String ticketRequestJson = "{\"ticket\":\"00011\",\"price\":80,\"amount\":5}";

        String ticket = lotteryRequestDto.getTicket();
        LotteryTicketResponseDto lotteryTicketResponseDto = new LotteryTicketResponseDto(ticket);

        lenient().when(lotteryService.addLottery(any())).thenReturn(lotteryTicketResponseDto);

        mockMvc.perform(post("/admin/lotteries")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(ticketRequestJson))
                .andExpect(status().isBadRequest());
    }

    @Test
    @DisplayName("POST: /admin/lotteries ; price < 0 ; throw BadRequestException")
    void addLotteryBadRequestPriceLessThanZero() throws Exception {
        LotteryRequestDto lotteryRequestDto = new LotteryRequestDto("000111",-1,5);
        String ticketRequestJson = "{\"ticket\":\"000111\",\"price\":-1,\"amount\":5}";

        String ticket = lotteryRequestDto.getTicket();
        LotteryTicketResponseDto lotteryTicketResponseDto = new LotteryTicketResponseDto(ticket);

        lenient().when(lotteryService.addLottery(any())).thenReturn(lotteryTicketResponseDto);

        mockMvc.perform(post("/admin/lotteries")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(ticketRequestJson))
                .andExpect(status().isBadRequest());
    }

    @Test
    @DisplayName("POST: /admin/lotteries ; amount < 0 ; throw BadRequestException")
    void addLotteryBadRequestAmountLessThanZero() throws Exception {
        LotteryRequestDto lotteryRequestDto = new LotteryRequestDto("000111",80,-1);
        String ticketRequestJson = "{\"ticket\":\"000111\",\"price\":80,\"amount\":-1}";

        String ticket = lotteryRequestDto.getTicket();
        LotteryTicketResponseDto lotteryTicketResponseDto = new LotteryTicketResponseDto(ticket);

        lenient().when(lotteryService.addLottery(any())).thenReturn(lotteryTicketResponseDto);

        mockMvc.perform(post("/admin/lotteries")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(ticketRequestJson))
                .andExpect(status().isBadRequest());
    }

}