package top.canoe0.blog.service;

import org.junit.jupiter.api.*;
import top.canoe0.blog.entity.user.RegularUser;

import javax.servlet.http.HttpSession;

import static org.junit.jupiter.api.Assertions.*;

class UserServiceTest {
    RegularUser regularUser;
    static UserService userService;

    @BeforeAll
    static void beforeAll() {
        userService = new UserService();
    }

    @AfterAll
    static void afterAll() {
        userService = null;
    }

    @BeforeEach
    void setUp() {
        regularUser = new RegularUser();
        regularUser.setAccount("test" + String.valueOf(System.currentTimeMillis()).substring(-5));
        regularUser.setPassword("test" + String.valueOf(System.currentTimeMillis()).substring(-5));
    }

    @AfterEach
    void tearDown() {
        regularUser = null;
    }

    @Test
    void getSession() {
    }

    @Test
    void setSession() {
    }

    @Test
    void saveAdmin() {
    }

    @Test
    void saveRegularUser() {
        HttpSession session = null;
        userService.saveRegularUser(regularUser);
    }

    @Test
    void listAllRegularUser() {
    }

    @Test
    void listAllAdmin() {
    }

    @Test
    void findAdminByAccount() {
    }

    @Test
    void findRegularUserByAccount() {
    }

    @Test
    void loginAdmin() {
    }

    @Test
    void loginRegularUser() {

    }
}