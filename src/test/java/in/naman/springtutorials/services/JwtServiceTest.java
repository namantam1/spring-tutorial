package in.naman.springtutorials.services;

import in.naman.springtutorials.models.entities.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.lang.reflect.Field;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class JwtServiceTest {
    private static final String KEY = "451db7fc3308287a164d93fd94c5c4f252915dfeca394e118608ed56091156d3";
    @InjectMocks
    JwtService jwtService;

    @BeforeEach
    void setUp() throws NoSuchFieldException, IllegalAccessException {
        Field secretKeyField = jwtService.getClass().getDeclaredField("secretKey");
        secretKeyField.setAccessible(true);
        secretKeyField.set(jwtService, KEY);

        Field jwtExpirationField = jwtService.getClass().getDeclaredField("jwtExpiration");
        jwtExpirationField.setAccessible(true);
        jwtExpirationField.set(jwtService, 3600);
    }

    @Test
    void extractUserName() {
        User user = new User();
        user.setEmail("email");

        String token = jwtService.generateToken(user);
        String expectedEmail = jwtService.extractUserName(token);

        assertEquals(user.getEmail(), expectedEmail);
    }

    @Test
    void isTokenValid() {
        User user = new User();
        user.setEmail("email");

        String token = jwtService.generateToken(user);
        assertTrue(jwtService.isTokenValid(token, user));
    }
}