package com.ezgroceries.shoppinglist;





import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;


@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    /**
     * Get total user count present in user table.
     * @return
     */
    public boolean checkUsersDataExistInDB() {
        return userRepository.count() > 0;
    }

    /**
     * Save user with Role in database
     * @param users
     */
    public void saveAll(final List<User> users) {
        userRepository.saveAll(users);
    }

    /**
     * It returns all users from the User Table from Database.
     * @return
     */
    public List<User> getUsers() {
        System.out.println("Fetching all users from the db");
        final List<User> users = userRepository.findAll();
        if (CollectionUtils.isEmpty(users)) {

            System.out.println("No users in DB!");
            return Collections.emptyList();
        }
        return users;
    }
}

