package com.ezgroceries.shoppinglist;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Component
public class CreateUserData implements CommandLineRunner {

    @Autowired
    UserService userService;
    @Autowired
    BCryptPasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) {
        addUsers();
    }

    /**
     * Create users if not exist in DB
     */
    private void addUsers() {
        if (!userService.checkUsersDataExistInDB()) {
            System.out.println("Save Users");
            userService.saveAll(createUsers());
        } else {
            System.out.println("Users are available in DB!");
        }
    }

    /**
     * Create User with Roles for testing purpose
     * @return List
     */
    private List<User> createUsers() {
        final List<User> users = new ArrayList<>();
        users.add(create("John",
                Collections.singletonList(Role.ROLE_CLIENT),"John"));
        users.add(create("Simon",
                Collections.singletonList(Role.ROLE_ADMIN),"Simon"));
        users.add(create("Joe",
                Collections.singletonList(Role.ROLE_SUPERVISOR),"Joe"));
        users.add(create("Brinda",
                Arrays.asList(Role.ROLE_ADMIN, Role.ROLE_SUPERVISOR),"Brinda"));
        return users;
    }

    private User create(final String userName, final List<Role> roles, final String password) {
        return User.builder()
                .userName(userName)
                .password(passwordEncoder.encode(password))
                .active(true)
                .roles(roles)
                .build();
    }
}
