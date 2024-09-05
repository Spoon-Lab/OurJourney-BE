package pudding.toy.ourJourney.entity;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDateTime;

@MappedSuperclass
public abstract class BaseTimeEntity {
    @CreatedDate @Column(name ="create_at")
    private LocalDateTime createdAt; // 생성 시간
    @LastModifiedDate @Column(name = "updated_at")
    private LocalDateTime updateAt; //마지막 수정 시간
    @Column(name = "deleted_at")
    private LocalDateTime deletedAt;
    public void setDeletedAt(LocalDateTime deletedAt){
        this.deletedAt = deletedAt;
    }

}
