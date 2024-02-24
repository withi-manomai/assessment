package com.kbtg.bootcamp.posttest.User;

import com.kbtg.bootcamp.posttest.Lottery.Lottery;
import com.kbtg.bootcamp.posttest.Lottery.LotteryRepository;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
@Service
public class UserService {
    private final UserTicketRepository userTicketRepository;
    private final LotteryRepository lotteryRepository;
    public UserService(UserTicketRepository userRepository, LotteryRepository lotteryRepository) {
        this.userTicketRepository = userRepository;
        this.lotteryRepository = lotteryRepository;
    }


    public List<UserTicket> getUserList() {
        return userTicketRepository.findAll();
    }

    public UserTicketIdResponseDto buyTicket(String userId, String ticket) {
        Lottery stockLottery = lotteryRepository.findFirstByTicket(ticket);
        //       System.out.println(stockLottery.toString());
        UserTicket existingUserTicket = userTicketRepository.findFirstByUserIdAndTicket(userId, ticket);
        //        System.out.println(exitingTicket.toString());
        Long returnId = null;
        if(stockLottery!=null && stockLottery.getAmount()>0) {
            if (existingUserTicket == null) {
                UserTicket userTicket = new UserTicket();
                userTicket.setUserId(userId);
                userTicket.setTicket(ticket);
                userTicket.setAmount(1);
                UserTicket saveUserTicket = userTicketRepository.save(userTicket);
                Long generatedId = saveUserTicket.getId();
                returnId = generatedId;
            } else {
                existingUserTicket.setAmount(existingUserTicket.getAmount() + 1);
                userTicketRepository.save(existingUserTicket);
                returnId = existingUserTicket.getId();
            }

                stockLottery.setAmount(stockLottery.getAmount()-1);
                lotteryRepository.save(stockLottery);

        }
        return new UserTicketIdResponseDto(returnId);
    }

    public UserBoughtTicketListResponseDto getUserBoughtTicketList(String userId) {
        List<UserTicket> userBoughtTicketList= userTicketRepository.findAllByUserId(userId);
        String[] boughtTicketList = new String[userBoughtTicketList.size()];
        Integer cost = 0;
        Integer count = 0;
        for (int i = 0; i < userBoughtTicketList.size(); i++) {
            boughtTicketList[i] = userBoughtTicketList.get(i).getTicket();
            cost += userBoughtTicketList.get(i).getAmount()*userBoughtTicketList.get(i).getLottery().getPrice();
            count += userBoughtTicketList.get(i).getAmount();
        }
        System.out.println(Arrays.toString(boughtTicketList));
        System.out.println(count.toString());
        System.out.println(cost.toString());
        UserBoughtTicketListResponseDto userBoughtTicketListResponseDto
                = new UserBoughtTicketListResponseDto(boughtTicketList,count,cost);

        return userBoughtTicketListResponseDto;
    }

    public UserTicketResponseDto sellTicket(String userId, String ticket) {
        Long Id = userTicketRepository.findFirstByUserIdAndTicket(userId,ticket).getId();
        userTicketRepository.deleteById(Id);
        return new UserTicketResponseDto(ticket);
    }
}
