package com.project.devQuest.repository;

import com.project.devQuest.model.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class UserRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;
    @Autowired
    private UserRepository userRepository;

    @Test
    public void whenFindByUsername_thenReturnUser() {
        // GIven
        User user = new User();
        user.setUsername("test");
        user.setEmail("test@mail.com");
        user.setPassword("test102030");
        entityManager.persistAndFlush(user);
        // When
        Optional<User> foundUser = userRepository.findByUsername(user.getUsername());
        // Then
        assertTrue(foundUser.isPresent());
        assertEquals(user.getUsername(), foundUser.get().getUsername());
    }

    @Test
    public void whenFindByEmail_thenReturnUser() {
        // Given
        User user = new User();
        user.setUsername("test");
        user.setEmail("test@mail.com");
        user.setPassword("test102030");
        entityManager.persistAndFlush(user);
        // When
        Optional<User> foundUser = userRepository.findByEmail(user.getEmail());
        // then
        assertTrue(foundUser.isPresent());
        assertEquals((foundUser.get().getEmail()), user.getEmail());
    }
}
