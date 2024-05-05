package in.naman.springtutorials.controllers;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class UserControllerTest {
    @Autowired
    private UserController userController;

    @BeforeEach
    void setUp() {
    }

    @Test
    void authenticatedUser() {
    }

    @Test
    void listUsers() {
    }
}