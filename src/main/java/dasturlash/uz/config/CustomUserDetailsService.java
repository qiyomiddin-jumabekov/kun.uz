package dasturlash.uz.config;

import dasturlash.uz.entity.Profile;
import dasturlash.uz.enums.ProfileRoles;
import dasturlash.uz.repository.ProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CustomUserDetailsService implements UserDetailsService {
    @Autowired
    private ProfileRepository profileRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // username

        Optional<Profile> optional = profileRepository.findByUsername(username);
        if (optional.isEmpty()) {
            throw new UsernameNotFoundException(username);
        }
        Profile profile = optional.get();

        ProfileRoles role = profileRepository.getProfileRoleById(profile.getId());
        return new CustomUserDetails(profile.getId(), profile.getUsername(), profile.getPassword(), role);
    }
}
