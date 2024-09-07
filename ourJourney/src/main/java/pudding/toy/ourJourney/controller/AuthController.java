package pudding.toy.ourJourney.controller;


import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import pudding.toy.ourJourney.common.response.BaseResponse;
import pudding.toy.ourJourney.dto.auth.ProfileAuthResponseDto;
import pudding.toy.ourJourney.service.AuthService;

@RestController
@RequiredArgsConstructor
public class AuthController {
    @Autowired
    AuthService authService;


}
