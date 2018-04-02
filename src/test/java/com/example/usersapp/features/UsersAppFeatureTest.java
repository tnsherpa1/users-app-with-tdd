package com.example.usersapp.features;

import com.example.usersapp.models.User;
import com.example.usersapp.repositories.UserRepository;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.stream.Stream;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;
import static io.restassured.http.ContentType.JSON;
import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.is;


@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class UsersAppFeatureTest {
    @Autowired
    private UserRepository userRepository;

    @Before
    public void setUp() {
        userRepository.deleteAll();
    }

    @After
    public void tearDown() {
        userRepository.deleteAll();
    }

    @Test
    public void shouldAllowFullCrudFunctionalityForAUser() throws Exception {
        User firstUser = new User(
                "someone",
                "Ima",
                "Person"
        );
        User secondUser = new User(
                "someone_else",
                "Someone",
                "Else"
        );

        Stream.of(firstUser, secondUser)
                .forEach(user -> {
                    userRepository.save(user);
                });

        when()
                .get("http://localhost:8080/users/")
        .then()
                .statusCode(is(200))
                .and().body(containsString("Someone"))
                .and().body(containsString("Else"));

        User userNew = new User(
                "new_user",
                "Not",
                "Yet Created"
        );
        given()
                .contentType(JSON)
                .and().body(userNew)
                .when()
                .post("http://localhost:8080/users")
                .then()
                .statusCode(is(200))
                .and().body(containsString("new_user"));

        when()
                .get("http://localhost:8080/users/")
                .then()
                .statusCode(is(200))
                .and().body(containsString("someone"))
                .and().body(containsString("Else"))
                .and().body(containsString("Yet Created"));

        when()
                .get("http://localhost:8080/users/" + secondUser.getId())
                .then()
                .statusCode(is(200))
                .and().body(containsString("Someone"))
                .and().body(containsString("Else"));

        secondUser.setFirstName("updated_name");

        given()
                .contentType(JSON)
                .and().body(secondUser)
                .when()
                .patch("http://localhost:8080/users/" + secondUser.getId())
                .then()
                .statusCode(is(200))
                .and().body(containsString("updated_name"));
    }
}
