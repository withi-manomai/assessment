package com.kbtg.bootcamp.posttest.Security;

import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class UserRepository {
    List<CustomUserDetail> userDetails = new ArrayList<>();
    public UserRepository() {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        CustomUserDetail user = new CustomUserDetail("username", encoder.encode("password"));
        user.setRoles(List.of("USER"));
        user.setPermissions(List.of(("USER_BUY-TICKET"),("USER_GET-USER-BOUGHT-TICKET-LIST"),("USER_SELL-TICKET")));
        userDetails.add(user);

        CustomUserDetail admin = new CustomUserDetail("admin", encoder.encode("password"));
        admin.setRoles(List.of("ADMIN"));
        user.setPermissions(List.of(("ADMIN_GET-LOTTERY-LIST"),("ADMIN-ADD-LOTTERY"),("USER_SELL-TICKET")));
        userDetails.add(admin);
    }
    public CustomUserDetail findUserByUsername(String username) {
        return userDetails
                .stream()
                .filter(u -> u.getUsername().equals(username))
                .findFirst()
                .orElseThrow(() -> new UsernameNotFoundException("bad authentication"));
    }
}
