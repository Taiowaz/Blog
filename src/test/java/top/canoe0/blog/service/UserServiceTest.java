package top.canoe0.blog.service;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import top.canoe0.blog.entity.user.Admin;
import top.canoe0.blog.entity.user.RegularUser;

import static org.junit.jupiter.api.Assertions.*;

class UserServiceTest {
    static UserService userService;

    @BeforeAll
    static void beforeAll() {
        userService = new UserService();
    }

    @BeforeEach
    void setUp() {
        userService = null;
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void findAdminById() {
        Admin admin = userService.findAdminById(76);
        System.out.println("admin = " + admin);
    }

    @Test
    void findRegularUserById() {
        RegularUser regularUser = userService.findRegularUserById(1);
        System.out.println("regularUser = " + regularUser);
    }
}