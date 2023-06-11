package com.sharonghranui.roomgenius;

import com.sharonghranui.roomgenius.data.entity.User;
import com.sharonghranui.roomgenius.data.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@RequiredArgsConstructor
@SpringBootApplication
public class RoomGeniusApplication {
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    public static void main(String[] args) {
        SpringApplication.run(RoomGeniusApplication.class, args);
    }

    @Transactional
    @EventListener(ApplicationReadyEvent.class)
    public void createUser () {
        var elroy = userRepository.findByUsername("elroy")
                .orElseGet(() -> {
                    var user = new User();
                    user.setPassword(passwordEncoder.encode("password"));
                    user.setUsername("elroy");
                    userRepository.save(user);
                    return user;
                });
        log.info("Created user: {}", elroy.getUsername());
    }
}
