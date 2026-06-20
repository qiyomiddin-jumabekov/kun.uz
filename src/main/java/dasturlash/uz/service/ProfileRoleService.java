package dasturlash.uz.service;

import dasturlash.uz.entity.ProfileRole;
import dasturlash.uz.enums.ProfileRoles;
import dasturlash.uz.repository.ProfileRoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProfileRoleService {

    @Autowired
    private ProfileRoleRepository profileRoleRepository;


    public void createProfileRole(Integer profileId, ProfileRoles profileRole) {
        ProfileRole profileRoleEntity = new ProfileRole();
        profileRoleEntity.setProfileId(profileId);
        profileRoleEntity.setRole(profileRole);
        profileRoleRepository.save(profileRoleEntity);
    }

}
