package ccc.data;

import ccc.models.AppUser;
import org.springframework.transaction.annotation.Transactional;

public interface AppUserRepository {
    @Transactional
    AppUser findByUsername(String username);

    @Transactional
    AppUser create(AppUser user, int userId);

    @Transactional
    void update(AppUser user);
}
