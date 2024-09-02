package pudding.toy.ourJourney.entity;

import jakarta.persistence.MappedSuperclass;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDateTime;

@MappedSuperclass
public abstract class BaseTimeEntity {
    @CreatedDate
    private LocalDateTime createdAt; // 생성 시간
    @LastModifiedDate
    private LocalDateTime updateAt; //마지막 수정 시간

}
