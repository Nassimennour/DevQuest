package com.project.devQuest.service;

import com.project.devQuest.converter.UserDTOConverter;
import com.project.devQuest.dto.ChangePasswordDTO;
import com.project.devQuest.dto.UserDTO;
import com.project.devQuest.model.Dashboard;
import com.project.devQuest.model.User;
import com.project.devQuest.model.VerificationToken;
import com.project.devQuest.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

    @InjectMocks
    private UserService userService;

    @Mock
    private UserRepository userRepository;
    @Mock
    private UserDTOConverter userDTOConverter;
    @Mock
    private PasswordEncoder passwordEncoder;
    @Mock
    private EmailService emailService;
    @Mock
    private VerificationTokenService verificationTokenService;

    @Test
    public void whenSave_thenReturnUserDTO() {
        User user = new User();
        user.setUsername("user");
        user.setPassword("password123");
        user.setEmail("user@email.com");
        VerificationToken token = new VerificationToken();
        when(passwordEncoder.encode(user.getPassword())).thenReturn("encodedPassword");
        when(userRepository.save(user)).thenReturn(user);
        when(userDTOConverter.convertToDTO(user)).thenReturn(new UserDTO());
        when(verificationTokenService.createVerificationToken(user)).thenReturn(token);
        doNothing().when(emailService).sendVerificationEmail(user.getEmail(), anyString());

        UserDTO savedUserDTO = userService.save(user);

        assertNotNull(savedUserDTO);
        verify(userRepository, times(1)).save(user);
        verify(emailService, times(1)).sendVerificationEmail(anyString(), anyString());
    }

    @Test
    public void whenFindByUsername_thenReturnUserDTO() {
        User user = new User();
        user.setUsername("user");
        user.setEmail("user@mail.com");
        user.setPassword("password123");
        when(userRepository.findByUsername("user")).thenReturn(Optional.of(user));
        when(userDTOConverter.convertToDTO(user)).thenReturn(new UserDTO());

        UserDTO userDTO = userService.findByUsername("user");

        assertNotNull(userDTO);
        verify(userRepository, times(1)).findByUsername("user");
    }

    @Test
    public void whenExistsByUsername_thenReturnTrue() {
        when(userRepository.existsByUsername("user")).thenReturn(true);

        boolean exists = userService.existsByUsername("user");

        assertTrue(exists);
        verify(userRepository, times(1)).existsByUsername("user");
    }

    @Test
    public void whenFindByEmail_thenReturnUserDTO() {
        User user = new User();
        user.setEmail("user@email.com");
        user.setPassword("password123");
        user.setUsername("user");
        when(userRepository.findByEmail("user@email.com")).thenReturn(Optional.of(user));
        when(userDTOConverter.convertToDTO(user)).thenReturn(new UserDTO());

        UserDTO userDTO = userService.findByEmail("user@email.com");

        assertNotNull(userDTO);
        verify(userRepository, times(1)).findByEmail("user@email.com");
    }

    @Test
    public void whenFindAll_thenReturnUserDTOList() {
        User user = new User();
        user.setEmail("user@email.com");
        user.setPassword("password123");
        user.setUsername("user");
        when(userRepository.findAll()).thenReturn(Collections.singletonList(user));
        when(userDTOConverter.convertToDTO(user)).thenReturn(new UserDTO());

        List<UserDTO> userDTOList = userService.findAll();

        assertEquals(1, userDTOList.size());
        verify(userRepository, times(1)).findAll();
    }

    @Test
    public void whenFindById_thenReturnUserDTO() {
        User user = new User();
        user.setEmail("user@email.com");
        user.setPassword("password123");
        user.setUsername("user");
        when(userRepository.findById(1L)).thenReturn(Optional.of(user));
        when(userDTOConverter.convertToDTO(user)).thenReturn(new UserDTO());

        Optional<UserDTO> userDTO = userService.findById(1L);

        assertTrue(userDTO.isPresent());
        verify(userRepository, times(1)).findById(1L);
    }

    @Test
    public void whenDeleteById_thenVerifyDeletion() {
        doNothing().when(userRepository).deleteById(1L);

        userService.deleteById(1L);

        verify(userRepository, times(1)).deleteById(1L);
    }

    @Test
    public void whenVerifyToken_thenUserIsVerified() {
        User user = new User();
        user.setEmail("user@email.com");
        user.setPassword("password123");
        user.setUsername("user");
        VerificationToken token = new VerificationToken();
        token.setUser(user);
        token.setExpiryDate(new Date(System.currentTimeMillis() + 10000));
        when(verificationTokenService.getVerificationToken("token")).thenReturn(token);
        when(userRepository.save(user)).thenReturn(user);

        userService.verifyToken("token");

        assertTrue(user.isVerified());
        verify(userRepository, times(1)).save(user);
    }

    @Test
    public void whenDeleteAll_thenVerifyDeletion() {
        doNothing().when(userRepository).deleteAll();

        userService.deleteAll();

        verify(userRepository, times(1)).deleteAll();
    }

    @Test
    public void whenExistsById_thenReturnTrue() {
        when(userRepository.existsById(1L)).thenReturn(true);

        boolean exists = userService.existsById(1L);

        assertTrue(exists);
        verify(userRepository, times(1)).existsById(1L);
    }

    @Test
    public void whenUpdate_thenReturnUpdatedUserDTO() {
        UserDTO userDTO = new UserDTO();
        User user = new User();
        user.setEmail("user@email.com");
        user.setPassword("password123");
        user.setUsername("user");
        when(userDTOConverter.convertToEntity(userDTO)).thenReturn(user);
        when(userRepository.save(user)).thenReturn(user);

        UserDTO updatedUserDTO = userService.update(userDTO);

        assertNotNull(updatedUserDTO);
        verify(userRepository, times(1)).save(user);
    }

    @Test
    public void whenChangePassword_thenPasswordIsChanged() {
        ChangePasswordDTO changePasswordDTO = new ChangePasswordDTO();
        changePasswordDTO.setUsername("user");
        changePasswordDTO.setCurrentPassword("currentPassword");
        changePasswordDTO.setNewPassword("newPassword");

        User user = new User();
        user.setPassword("encodedCurrentPassword");
        when(userRepository.findByUsername("user")).thenReturn(Optional.of(user));
        when(passwordEncoder.matches("currentPassword", "encodedCurrentPassword")).thenReturn(true);
        when(passwordEncoder.encode("newPassword")).thenReturn("encodedNewPassword");
        when(userRepository.save(user)).thenReturn(user);

        userService.changePassword(changePasswordDTO);

        assertEquals("encodedNewPassword", user.getPassword());
        verify(userRepository, times(1)).save(user);
    }
}