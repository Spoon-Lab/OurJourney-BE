package pudding.toy.ourJourney.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import pudding.toy.ourJourney.dto.auth.ProfileAuthRequest;

import java.util.ArrayList;
import java.util.Arrays;

@SpringBootTest
public class NickNameRandomServiceTest {
    @Autowired
    ProfileService profileService;
    @Test
    void randomTest(){
        ArrayList<String> randoms = new ArrayList<>(
                Arrays.asList("예쁜", "졸린", "작은", "큰", "빠른", "따뜻한", "밝은", "산뜻한", "사랑스러운", "행복한", "귀여운")
        );
        int randomIndex = (int)(Math.random()*randoms.size());
        System.out.println(randoms.get(randomIndex)+"푸딩"+(int)(Math.random()*1000)+"번");
    }
    @Test
    void createProfile(){
        ProfileAuthRequest profileAuthRequest = new ProfileAuthRequest(3L);
        System.out.println(profileService.createProfile(profileAuthRequest).getNickName());

    }
}
