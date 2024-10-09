package pudding.toy.ourJourney.global.entity;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDateTime;

@MappedSuperclass
@Getter
public abstract class BaseTimeEntity {
    @CreatedDate
    @Column(name = "created_at")
    private LocalDateTime createdAt = LocalDateTime.now(); // 생성 시간

    @LastModifiedDate
    @Column(name = "updated_at")
    private LocalDateTime updatedAt = LocalDateTime.now(); //마지막 수정 시간

    @Column(name = "deleted_at")
    private LocalDateTime deletedAt;

    public void remove() {
        this.deletedAt = LocalDateTime.now();
    }

    public void remove(LocalDateTime deletedAt) {
        this.deletedAt = deletedAt;
    }

}
