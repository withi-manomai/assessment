package com.kbtg.bootcamp.posttest.UserTicketTest;

import com.kbtg.bootcamp.posttest.UserTicket.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;

import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.lenient;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
public class UserTicketControllerTest {
    MockMvc mockMvc;
    @Mock
    UserTicketService userTicketService;
    @BeforeEach
    void setUp(){
        UserTicketController userTicketController = new UserTicketController(userTicketService);
        mockMvc = MockMvcBuilders.standaloneSetup(userTicketController).build();
    }
    @Test
    @DisplayName("POST : /users/{userId}/lotteries/{ticket} ; correct userId, normal ticket ; return id : 1 ")
    void buyTicket() throws Exception {
        String userId = "0000011111";
        String ticket = "000111";
        UserTicketIdResponseDto userTicketIdResponseDto = new UserTicketIdResponseDto(1L);
        when(userTicketService.buyTicket(any(),any())).thenReturn(userTicketIdResponseDto);

        mockMvc.perform(post("/users/{userId}/lotteries/{ticket}", userId, ticket)
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("POST : /users/{userId}/lotteries/{ticket} ; userId > 10 digit , correct ticket ; throw BadRequestException ")
    void buyTicketBadRequestUserIdDigitMoreThanTen() throws Exception {
        String userId = "00000111111";
        String ticket = "000111";
        UserTicketIdResponseDto userTicketIdResponseDto = new UserTicketIdResponseDto(1L);
        lenient().when(userTicketService.buyTicket(any(),any())).thenReturn(userTicketIdResponseDto);

        mockMvc.perform(post("/users/{userId}/lotteries/{ticket}", userId, ticket)
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

    @Test
    @DisplayName("POST : /users/{userId}/lotteries/{ticket} ; userId < 10 digit, correct ticket ; throw BadRequestException ")
    void buyTicketBadRequestUserIdDigitLessThanTen() throws Exception {
        String userId = "000001111";
        String ticket = "000111";
        UserTicketIdResponseDto userTicketIdResponseDto = new UserTicketIdResponseDto(1L);
        lenient().when(userTicketService.buyTicket(any(),any())).thenReturn(userTicketIdResponseDto);

        mockMvc.perform(post("/users/{userId}/lotteries/{ticket}", userId, ticket)
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

    @Test
    @DisplayName("POST : /users/{userId}/lotteries/{ticket} ; correct userId , ticket > 6 digit ; throw BadRequestException ")
    void buyTicketBadRequestTicketDigitMoreThanSix() throws Exception {
        String userId = "0000011111";
        String ticket = "0001111";
        UserTicketIdResponseDto userTicketIdResponseDto = new UserTicketIdResponseDto(1L);
        lenient().when(userTicketService.buyTicket(any(),any())).thenReturn(userTicketIdResponseDto);

        mockMvc.perform(post("/users/{userId}/lotteries/{ticket}", userId, ticket)
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

    @Test
    @DisplayName("POST : /users/{userId}/lotteries/{ticket} ; correct userId , ticket < 6 digit ; throw BadRequestException ")
    void buyTicketBadRequestTicketDigitLessThanSix() throws Exception {
        String userId = "0000011111";
        String ticket = "00011";
        UserTicketIdResponseDto userTicketIdResponseDto = new UserTicketIdResponseDto(1L);
        lenient().when(userTicketService.buyTicket(any(),any())).thenReturn(userTicketIdResponseDto);

        mockMvc.perform(post("/users/{userId}/lotteries/{ticket}", userId, ticket)
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

    @Test
    @DisplayName("GET : /users/{userId}/lotteries ; correct userId ; " +
            "return tickets = [000111,000222,000333],count=3,cost=240 ")
    void getUserBoughtTicketList() throws Exception {
        String userId = "0000011111";
        UserTicketBoughtListResponseDto userTicketBoughtListResponseDto = new UserTicketBoughtListResponseDto(new String[]{"000111","000222","000333"},3,240);
        when(userTicketService.getUserBoughtTicketList(any())).thenReturn(userTicketBoughtListResponseDto);

        mockMvc.perform(get("/users/{userId}/lotteries", userId)
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.tickets", is(Arrays.asList("000111", "000222", "000333"))))
                .andExpect(jsonPath("$.count", is(3)))
                .andExpect(jsonPath("$.cost", is(240)))
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("GET : /users/{userId}/lotteries ; userId > 10 digit ; " +
            "throw BadRequestException ")
    void getUserBoughtTicketListBadRequestUserIdDigitMoreThanTen() throws Exception {
        String userId = "00000111111";
        UserTicketBoughtListResponseDto userTicketBoughtListResponseDto = new UserTicketBoughtListResponseDto(new String[]{"000111","000222","000333"},3,240);
        lenient().when(userTicketService.getUserBoughtTicketList(any())).thenReturn(userTicketBoughtListResponseDto);

        mockMvc.perform(get("/users/{userId}/lotteries", userId)
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

    @Test
    @DisplayName("GET : /users/{userId}/lotteries ; userId < 10 digit ; " +
            "throw BadRequestException ")
    void getUserBoughtTicketListBadRequestUserIdDigitLessThanTen() throws Exception {
        String userId = "000001111";
        UserTicketBoughtListResponseDto userTicketBoughtListResponseDto = new UserTicketBoughtListResponseDto(new String[]{"000111","000222","000333"},3,240);
        lenient().when(userTicketService.getUserBoughtTicketList(any())).thenReturn(userTicketBoughtListResponseDto);

        mockMvc.perform(get("/users/{userId}/lotteries", userId)
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

    @Test
    @DisplayName("DELETE : /users/{userId}/lotteries/{ticket} ; correct userId and ticket ; " +
            "return ticket = 000111 ")
    void sellTicket() throws Exception {
        String userId = "0000011111";
        String ticket = "000111";
        UserTicketResponseDto userTicketResponseDto = new UserTicketResponseDto("000111");
        when(userTicketService.sellTicket(any(),any())).thenReturn(userTicketResponseDto);

        mockMvc.perform(delete("/users/{userId}/lotteries/{ticket}", userId, ticket)
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.ticket", is("000111")))
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("DELETE : /users/{userId}/lotteries/{ticket} ; userId > 10 digit, correct ticket ; " +
            "throw BadRequestException ")
    void sellTicketBadRequestUserIdDigitMoreThanTen() throws Exception {
        String userId = "00000111111";
        String ticket = "000111";
        UserTicketResponseDto userTicketResponseDto = new UserTicketResponseDto("000111");
        lenient().when(userTicketService.sellTicket(any(),any())).thenReturn(userTicketResponseDto);

        mockMvc.perform(delete("/users/{userId}/lotteries/{ticket}", userId, ticket)
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

    @Test
    @DisplayName("DELETE : /users/{userId}/lotteries/{ticket} ; userId < 10 digit, correct ticket ; " +
            "throw BadRequestException ")
    void sellTicketBadRequestUserIdDigitLessThanTen() throws Exception {
        String userId = "000001111";
        String ticket = "000111";
        UserTicketResponseDto userTicketResponseDto = new UserTicketResponseDto("000111");
        lenient().when(userTicketService.sellTicket(any(),any())).thenReturn(userTicketResponseDto);

        mockMvc.perform(delete("/users/{userId}/lotteries/{ticket}", userId, ticket)
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

    @Test
    @DisplayName("DELETE : /users/{userId}/lotteries/{ticket} ; correct userId , ticket > 6 ; " +
            "throw BadRequestException ")
    void sellTicketBadRequestTicketDigitMoreThanSix() throws Exception {
        String userId = "0000011111";
        String ticket = "0001111";
        UserTicketResponseDto userTicketResponseDto = new UserTicketResponseDto("0001111");
        lenient().when(userTicketService.sellTicket(any(),any())).thenReturn(userTicketResponseDto);

        mockMvc.perform(delete("/users/{userId}/lotteries/{ticket}", userId, ticket)
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

    @Test
    @DisplayName("DELETE : /users/{userId}/lotteries/{ticket} ; correct userId , ticket < 6 ; " +
            "throw BadRequestException ")
    void sellTicketBadRequestTicketDigitLessThanSix() throws Exception {
        String userId = "0000011111";
        String ticket = "00011";
        UserTicketResponseDto userTicketResponseDto = new UserTicketResponseDto("00011");
        lenient().when(userTicketService.sellTicket(any(),any())).thenReturn(userTicketResponseDto);

        mockMvc.perform(delete("/users/{userId}/lotteries/{ticket}", userId, ticket)
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }



}
