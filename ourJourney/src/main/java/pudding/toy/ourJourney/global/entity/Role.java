package pudding.toy.ourJourney.global.entity;

import lombok.Getter;

@Getter
public enum Role {
    ROLE_USER("사용자"),
    ROLE_ADMIN("어드민");
    String role;

    Role(String role) {
        this.role = role;
    }
}
