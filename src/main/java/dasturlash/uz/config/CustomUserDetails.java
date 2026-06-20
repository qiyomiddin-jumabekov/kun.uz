package dasturlash.uz.config;

import dasturlash.uz.entity.Profile;
import dasturlash.uz.enums.ProfileRoles;
import lombok.Getter;
import lombok.Setter;
import org.jspecify.annotations.Nullable;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Getter
@Setter
public class CustomUserDetails implements UserDetails {

    private String username;
    private String password;
    private Integer id;
    private List<ProfileRoles> role;

    public CustomUserDetails(Profile profile, List<ProfileRoles> role) {
        this.id = profile.getId();
        this.password = profile.getPassword();
        this.username = profile.getUsername();
        this.role = role;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> authorities = new ArrayList<>();
        for (ProfileRoles profileRoles : role) {
            authorities.add(new SimpleGrantedAuthority(profileRoles.name()));
        }
        return authorities;
    }

    @Override
    public @Nullable String getPassword() {
        return password;
    }


    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

}
