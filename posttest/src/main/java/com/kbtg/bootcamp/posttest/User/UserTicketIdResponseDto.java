package com.kbtg.bootcamp.posttest.User;

public class UserTicketIdResponseDto {
    private Long id;

    public UserTicketIdResponseDto(Long id) {
        this.id = id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}
