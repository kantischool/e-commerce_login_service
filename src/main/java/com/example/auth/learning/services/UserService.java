package com.example.auth.learning.services;

import com.example.auth.learning.dtos.UserSignUpDto;
import com.example.auth.learning.model.Token;
import com.example.auth.learning.model.User;
import com.example.auth.learning.repository.TokenRepository;
import com.example.auth.learning.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Date;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private TokenRepository tokenRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public User singUp(UserSignUpDto userSignUpDto) {

        User user = new User();

        user.setEmail(userSignUpDto.getEmail());
        user.setName(userSignUpDto.getName());
        user.setPassword(bCryptPasswordEncoder.encode(userSignUpDto.getPassword()));

        return userRepository.save(user);
    }

    public Token login(String email, String password) {

        Optional<User> optionalUser = userRepository.findByEmail(email);

        if (optionalUser.isEmpty()) {
            throw new RuntimeException("user does not exist");
        }

        User user = optionalUser.get();
        if (!bCryptPasswordEncoder.matches(password, user.getPassword())) {
            throw new RuntimeException("Invalid password");
        }

        Token token = new Token();
        token.setUser(user);
        token.setValue(UUID.randomUUID().toString());

        Date expisredDate = getExpiredDate();
        token.setExpiredAt(expisredDate);

        return tokenRepository.save(token);
    }

    private Date getExpiredDate() {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DAY_OF_MONTH, 30);
        return calendar.getTime();
    }

    public void logout(String token) {
        Optional<Token> optionalToken = tokenRepository.findByValueAndIsDeletedEquals(token, false);

        if (optionalToken.isEmpty()) {
            throw new RuntimeException("Invalid token");
        }

        Token tokenValue = optionalToken.get();
        tokenValue.setDeleted(true);
        tokenRepository.save(tokenValue);
    }

    public boolean validateToken(String token) {
      Optional<Token> optionalToken = tokenRepository.findByValueAndIsDeletedEqualsAndExpiredAtGreaterThan(token, false, new Date());

        return optionalToken.isPresent();
    }
}
