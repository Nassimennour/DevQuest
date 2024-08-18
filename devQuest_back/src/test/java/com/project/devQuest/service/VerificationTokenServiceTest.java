package com.project.devQuest.service;
import com.project.devQuest.model.User;
import com.project.devQuest.model.VerificationToken;
import com.project.devQuest.repository.VerificationTokenRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class VerificationTokenServiceTest {

    @InjectMocks
    private VerificationTokenService verificationTokenService;

    @Mock
    private VerificationTokenRepository verificationTokenRepository;

    @Test
    public void whenCreateVerificationToken_thenReturnSavedToken() {
        User user = new User();
        VerificationToken token = new VerificationToken();
        when(verificationTokenRepository.save(any(VerificationToken.class))).thenReturn(token);

        VerificationToken savedToken = verificationTokenService.createVerificationToken(user);

        assertNotNull(savedToken);
        verify(verificationTokenRepository, times(1)).save(any(VerificationToken.class));
    }

    @Test
    public void whenGetVerificationToken_thenReturnToken() {
        VerificationToken token = new VerificationToken();
        when(verificationTokenRepository.findByToken("test-token")).thenReturn(token);

        VerificationToken foundToken = verificationTokenService.getVerificationToken("test-token");

        assertNotNull(foundToken);
        verify(verificationTokenRepository, times(1)).findByToken("test-token");
    }
}
