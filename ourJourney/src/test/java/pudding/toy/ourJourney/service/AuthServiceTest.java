package pudding.toy.ourJourney.service;

import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import pudding.toy.ourJourney.auth.dto.AuthResponse;
import pudding.toy.ourJourney.auth.service.AuthService;

import javax.naming.AuthenticationException;

import static org.junit.jupiter.api.Assertions.assertEquals;


@Transactional
@SpringBootTest
class AuthServiceTest {
    @Autowired
    private AuthService authService;

    @Disabled
    @Test
    void getAuthTest() throws AuthenticationException {
        AuthResponse response = authService.getAuthForAuthorize("eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ0b2tlbl90eXBlIjoiYWNjZXNzIiwiZXhwIjoxNzI2ODQ0ODkwLCJpYXQiOjE3MjY4NDMwOTAsImp0aSI6IjhlMmEyNzdhZjE5ZDQ2NGZiOTcyNDJmOTExNDZhYTE5IiwidXNlcl9pZCI6MjJ9.FGLXGsrFzdGEj2IvjwaIxoJRF7LB3rtj-57w3pJexHc");
        assertEquals(response.getUserId(), 22);
    }
}