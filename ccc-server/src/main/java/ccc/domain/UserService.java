package ccc.domain;

import ccc.data.UserRepository;

import ccc.models.Event;
import ccc.models.User;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class UserService {

    private final UserRepository repository;

    public UserService(UserRepository repository) {
        this.repository = repository;
    }

    public List<User> findAll() {
        return repository.findAllUsers();
    }

    public Result<User> findByUserId(int userId){

        Result<User> result = new Result<>();
        User user = repository.findByUserId(userId);
        if (user == null) {
            String msg = String.format("Unable to find user with id %d.", userId);
            result.addMessage(msg, ResultType.NOT_FOUND);
        } else {
            result.setPayload(user);
        }

        return result;
    }

    public Result<User> addUser(User user){
        Result<User> result = validate(user);
        if (!result.isSuccess()) {
            return result;
        }

        if (user.getUserId() != 0) {
            result.addMessage("User Id cannot be set for `add` operation", ResultType.INVALID);
            return result;
        }

        user = repository.add(user);
        result.setPayload(user);
        return result;
    }



    public Result<User> update(User user) {
        Result<User> result = validate(user);
        if (!result.isSuccess()) {
            return result;
        }

        if (user.getUserId() <= 0) {
            result.addMessage("User id must be set for `update` operation", ResultType.INVALID);
            return result;
        }

        if (!repository.update(user)) {
            String msg = String.format("userId: %s, not found", user.getUserId());
            result.addMessage(msg, ResultType.NOT_FOUND);
        }

        return result;
    }

    public boolean deleteById(int userId) {
        return repository.deleteById(userId);
    }

    private Result<User> validate(User user){
        Result<User> result = new Result<>();

        if (user == null) {
            result.addMessage("User Cannot be null", ResultType.INVALID);
            return result;
        }

        if (Validations.isNullOrBlank(user.getFirstName())) {
            result.addMessage("User first name is required. ", ResultType.INVALID);
        }

        if (Validations.isNullOrBlank(user.getLastName())) {
            result.addMessage("User last name is required. ", ResultType.INVALID);
        }

        if (Validations.isNullOrBlank(user.getUserAddress())) {
            result.addMessage("User address is required. ", ResultType.INVALID);
        }

        if (Validations.isNullOrBlank(user.getUserCity())) {
            result.addMessage("User city is required. ", ResultType.INVALID);
        }
        if (Validations.isNullOrBlank(user.getUserState())) {
            result.addMessage("User state is required. ", ResultType.INVALID);
        }
        if (Validations.isNullOrBlank(user.getUserZip())) {
            result.addMessage("User zip code is required. ", ResultType.INVALID);
        }
        if (Validations.isNullOrBlank(user.getUserPhone())) {
            result.addMessage("User phone number is required. ", ResultType.INVALID);
        }
        if (Validations.isNullOrBlank(user.getUserEmail())) {
            result.addMessage("User email is required. ", ResultType.INVALID);
        }

        return result;
    }


}