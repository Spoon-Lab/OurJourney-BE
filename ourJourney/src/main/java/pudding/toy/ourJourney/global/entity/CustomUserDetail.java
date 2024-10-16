package pudding.toy.ourJourney.global.entity;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import pudding.toy.ourJourney.profile.entity.Profile;

import java.util.ArrayList;
import java.util.Collection;

public class CustomUserDetail implements UserDetails {
    private final Profile profile;

    public CustomUserDetail(Profile profile) {
        this.profile = profile;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        ArrayList<GrantedAuthority> auth = new ArrayList<GrantedAuthority>();
        auth.add(new SimpleGrantedAuthority(Role.ROLE_USER.getRole()));
        return auth;
    }

    @Override
    public String getPassword() {
        return null;
    }

    @Override
    public String getUsername() {
        return Long.toString(profile.getId());
    }
}
