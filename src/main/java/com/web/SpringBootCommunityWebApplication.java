package com.web;

import com.web.domain.Board;
import com.web.domain.User;
import com.web.domain.enums.BoardType;
import com.web.repository.BoardRepository;
import com.web.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;

import java.time.LocalDateTime;
import java.util.stream.IntStream;

@SpringBootApplication
public class SpringBootCommunityWebApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootCommunityWebApplication.class, args);
    }

    @Bean
    public CommandLineRunner runner(BoardRepository boardRepository, UserRepository userRepository) {
        return (args -> {
            User user = userRepository.save(User.builder()
                    .name("seung")
                    .password("pass")
                    .email("seung@gmail.com")
                    .createdDate(LocalDateTime.now())
                    .build());

            IntStream.rangeClosed(1, 200).forEach(idx ->
                    boardRepository.save(Board.builder()
                            .title("게시글"+idx)
                            .subTitle("순서"+idx)
                            .content("내용")
                            .boardType(BoardType.FREE)
                            .createdDate(LocalDateTime.now())
                            .updatedDate(LocalDateTime.now())
                            .user(user)
                            .build())
            );
        });
    }
}
