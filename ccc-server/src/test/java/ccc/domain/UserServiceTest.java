package ccc.domain;


import ccc.data.UserRepository;
import ccc.models.User;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;


@SpringBootTest
class UserServiceTest {

    @Autowired
    UserService service;

    @MockBean
    UserRepository repository;

    @Test
    void shouldFindByUserId(){

        User user = makeUser();

        Mockito.when(repository.findByUserId(1)).thenReturn(user);

        Result<User> result = service.findByUserId(1);
        assertEquals(user, result.getPayload());
    }


    @Test
    void shouldAdd(){
        User user = makeUser();
        User newUser = makeUser();
        newUser.setUserId(0);

        Mockito.when(repository.add(newUser)).thenReturn(user);

        Result<User> result = service.addUser(newUser);
        assertEquals(ResultType.SUCCESS, result.getType());
        assertEquals(user, result.getPayload());
    }

    @Test
    void shouldUpdate(){
        User user = makeUser();
        user.setUserId(8);

        Mockito.when(repository.findByUserId(8)).thenReturn(user);
        Mockito.when(repository.update(any())).thenReturn(true);

        Result<User> result = service.update(user);
        assertEquals(ResultType.SUCCESS, result.getType());
    }



    User makeUser(){
        User user = new User(13,"Tawfik","E","El Mouttaqi",
                "320 Washington St","Malden","MA","02148","6179439020",
                "tawfik@test.com");

        return user;
    }




}