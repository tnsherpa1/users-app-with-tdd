package com.example.usersapp.repositories;

import com.example.usersapp.models.User;
import com.google.common.collect.Iterables;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class UserRepositoryTest {
    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private UserRepository userRepository;

    @Before
    public void setUp() {

        User firstUser = new User(
                "user_name",
                "some_firstname",
                "some_lastname"
        );

        User secondUser = new User(
                "second_username",
                "second_firstname",
                "second_lastname"
        );

        entityManager.persist(firstUser);
        entityManager.persist(secondUser);
        entityManager.flush();

    }

    @Test
    public void findAll_returnsAllUsersFromDb() {
        Iterable<User> usersFromDb =  userRepository.findAll();
        long size = usersFromDb.spliterator().getExactSizeIfKnown();
        assertThat(size, is(2L));
    }

    @Test
    public void findAll_returnsUserName() {
        Iterable<User> usersFromDb = userRepository.findAll();
        String username = Iterables.get(usersFromDb,0).getUserName();
        assertThat(username,is("user_name" ));
    }

    @Test
    public void findAll_returnsFirstName() {
        Iterable<User> usersFromDb = userRepository.findAll();
        String firstname = Iterables.get(usersFromDb,0).getFirstName();
        assertThat(firstname,is("some_firstname" ));
    }

    @Test
    public void findAll_returnsLastName() {
        Iterable<User> usersFromDb = userRepository.findAll();
        String lastname = Iterables.get(usersFromDb,1).getLastName();
        assertThat(lastname,is("second_lastname" ));
    }
}