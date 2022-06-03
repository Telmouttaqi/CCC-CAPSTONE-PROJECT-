package ccc.data;

import ccc.models.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class UserJdbcTemplateRepositoryTest {

    @Autowired
    UserJdbcTemplateRepository repository;

    @Autowired
    KnownGoodState knownGoodState;

    @BeforeEach
    void setup() {
        knownGoodState.set();
    }

    @Test
    void shouldFindAll() {
        List<User> users = repository.findAllUsers();
        assertNotNull(users);
        assertEquals(users.size(),11);
    }


    @Test
    void shouldAddUser() {
        User user = makeUser();
        User actual = repository.add(user);
        assertNotNull(actual);

    }


    @Test
    void ShouldNotFindNotExistingId(){
        User user = repository.findByUserId(19);
        assertNull(user);
    }



    User makeUser(){
        User user = new User(13,"Tawfik","E","El Mouttaqi",
                "320 Washington St","Malden","MA","02148","6179439020",
                "tawfik@test.com");

        return user;
    }



}