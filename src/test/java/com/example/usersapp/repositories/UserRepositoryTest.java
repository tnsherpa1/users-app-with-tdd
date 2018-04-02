package com.example.usersapp.repositories;

import com.example.usersapp.models.User;
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
}