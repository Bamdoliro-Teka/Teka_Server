package com.teka.adapter.admin.out.persistence;


import com.teka.application.admin.port.out.CheckAdminUsernamePort;
import com.teka.application.admin.port.out.FindAdminPort;
import com.teka.application.admin.port.out.SaveAdminPort;
import com.teka.domain.admin.Admin;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class AdminPersistenceAdapter implements SaveAdminPort, CheckAdminUsernamePort, FindAdminPort {

    private final AdminRepository adminRepository;

    @Override
    public void save(Admin admin) {
        AdminJpaEntity entity = AdminJpaEntity.from(admin);
        adminRepository.save(entity);
    }

    @Override
    public Optional<Admin> findByUsername(String username) {
        return adminRepository.findByUsername(username)
                .map(AdminJpaEntity::toDomain);
    }

    @Override
    public boolean existsByUsername(String username) {
        return adminRepository.existsByUsername(username);
    }
}
