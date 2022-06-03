package ccc.security;

import ccc.data.AppUserRepository;
import ccc.domain.Result;
import ccc.domain.ResultType;
import ccc.domain.UserService;
import ccc.models.AppUser;
import ccc.models.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AppUserService implements UserDetailsService {
    private final AppUserRepository repository;

    private final UserService userService;
    private final PasswordEncoder encoder;

    public AppUserService(AppUserRepository repository,
                          UserService userService, PasswordEncoder encoder) {
        this.repository = repository;
        this.userService = userService;
        this.encoder = encoder;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        AppUser appUser = repository.findByUsername(username);

        if (appUser == null || !appUser.isEnabled()) {
            throw new UsernameNotFoundException(username + " not found");
        }

        return appUser;
    }

    public AppUser findByUserName(String userName){return repository.findByUsername(userName);}
    public Result<AppUser> create(String username, String password, String firstName, String middleName, String lastName, String address, String city, String state, String  zip, String  phone, String email) {
        Result<AppUser> appUserResult = validate(username);
        if(!appUserResult.isSuccess()){
            return appUserResult;
        }
        appUserResult = validatePassword(password);

        if(!appUserResult.isSuccess()){
            return appUserResult;
        }

        password = encoder.encode(password);

        User user = new User(0, firstName,middleName,lastName,address,city,state,zip,phone,email);
        Result<User> userResult = userService.addUser(user);

        if(userResult.isSuccess()){
            AppUser appUser = new AppUser(0, username, password, false, List.of("User"));
            appUserResult.setPayload(repository.create(appUser, userResult.getPayload().getUserId()));
        } else {
            for(String m: userResult.getMessages()) {
                appUserResult.addMessage(m, userResult.getType());
            }
        }

        return appUserResult;
    }

    private Result validate(String username) {
        Result result = new Result();
        if (username == null || username.isBlank()) {
            result.addMessage("username is required", ResultType.INVALID);
            return result;
        }

        if (username.length() > 50) {
            result.addMessage("username must be less than 50 characters", ResultType.INVALID);
        }

        return result;
    }

    private Result validatePassword(String password) {
        Result result = new Result();
        if (password == null || password.length() < 8) {
            result.addMessage("password must be at least 8 characters", ResultType.INVALID);
            return result;
        }

        int digits = 0;
        int letters = 0;
        int others = 0;
        for (char c : password.toCharArray()) {
            if (Character.isDigit(c)) {
                digits++;
            } else if (Character.isLetter(c)) {
                letters++;
            } else {
                others++;
            }
        }

        if (digits == 0 || letters == 0 || others == 0) {
            result.addMessage("password must contain a digit, a letter, and a non-digit/non-letter", ResultType.INVALID);
        }

        return result;
    }
}
