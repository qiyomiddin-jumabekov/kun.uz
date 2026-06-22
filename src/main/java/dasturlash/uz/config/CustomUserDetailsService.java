package dasturlash.uz.config;

import dasturlash.uz.entity.Profile;
import dasturlash.uz.entity.ProfileRole;
import dasturlash.uz.enums.ProfileRoles;
import dasturlash.uz.repository.ProfileRepository;
import dasturlash.uz.repository.ProfileRoleRepository;
import org.jspecify.annotations.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private ProfileRepository profileRepository;

    @Autowired
    private ProfileRoleRepository profileRoleRepository;


    @Override
    public UserDetails loadUserByUsername(@NonNull String username) throws UsernameNotFoundException {
        Optional<Profile> optional = profileRepository.findByUsername(username);

        if (optional.isEmpty()) {
            throw new UsernameNotFoundException(username);
        }
        Profile profile = optional.get();

        List<ProfileRoles> roles = profileRoleRepository.getRolesByProfileId(profile.getId());

        return new CustomUserDetails(profile, roles);
    }


}
