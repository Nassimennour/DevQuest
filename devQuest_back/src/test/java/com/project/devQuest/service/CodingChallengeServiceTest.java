package com.project.devQuest.service;
import com.project.devQuest.dto.CodingChallengeDTO;
import com.project.devQuest.model.CodingChallenge;
import com.project.devQuest.model.Technology;
import com.project.devQuest.model.User;
import com.project.devQuest.repository.CodingChallengeRepository;
import com.project.devQuest.repository.TechnologyRepository;
import com.project.devQuest.repository.UserRepository;
import com.project.devQuest.service.CodingChallengeService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class CodingChallengeServiceTest {

    @InjectMocks
    private CodingChallengeService codingChallengeService;

    @Mock
    private CodingChallengeRepository codingChallengeRepository;
    @Mock
    private TechnologyRepository  technologyRepository;
    @Mock
    private UserRepository userRepository;

    @Test
    public void whenGetAllCodingChallenges_thenReturnCodingChallengeList() {
        CodingChallenge codingChallenge = new CodingChallenge();
        when(codingChallengeRepository.findAll()).thenReturn(Collections.singletonList(codingChallenge));

        List<CodingChallenge> codingChallengeList = codingChallengeService.getAllCodingChallenges();

        assertEquals(1, codingChallengeList.size());
        verify(codingChallengeRepository, times(1)).findAll();
    }

    @Test
    public void whenGetCodingChallengeById_thenReturnCodingChallenge() {
        CodingChallenge codingChallenge = new CodingChallenge();
        when(codingChallengeRepository.findById(1L)).thenReturn(Optional.of(codingChallenge));

        Optional<CodingChallenge> foundCodingChallenge = codingChallengeService.getCodingChallengeById(1L);

        assertNotNull(foundCodingChallenge);
        verify(codingChallengeRepository, times(1)).findById(1L);
    }

    @Test
    public void whenCreateCodingChallenge_thenReturnSavedCodingChallenge() {
        CodingChallengeDTO codingChallengeDTO = new CodingChallengeDTO();
        codingChallengeDTO.setTitle("Test");
        codingChallengeDTO.setDifficulty("EASY");
        codingChallengeDTO.setCreatorId(1L);
        codingChallengeDTO.setTechnologyId(1L);
        CodingChallenge codingChallenge = new CodingChallenge();
        when(technologyRepository.findById(1L)).thenReturn(Optional.of(new Technology()));
        when(userRepository.findById(1L)).thenReturn(Optional.of(new User()));
        when(codingChallengeRepository.save(any(CodingChallenge.class))).thenReturn(codingChallenge);

        CodingChallenge savedCodingChallenge = codingChallengeService.saveCodingChallenge(codingChallengeDTO);

        assertNotNull(savedCodingChallenge);
        verify(codingChallengeRepository, times(1)).save(any(CodingChallenge.class));
    }

    @Test
    public void whenUpdateCodingChallenge_thenReturnUpdatedCodingChallenge() {
        CodingChallengeDTO codingChallengeDTO = new CodingChallengeDTO();
        codingChallengeDTO.setTitle("Test");
        codingChallengeDTO.setDifficulty("EASY");
        codingChallengeDTO.setCreatorId(1L);
        codingChallengeDTO.setTechnologyId(1L);
        when(technologyRepository.findById(1L)).thenReturn(Optional.of(new Technology()));
        when(userRepository.findById(1L)).thenReturn(Optional.of(new User()));
        CodingChallenge codingChallenge = new CodingChallenge();
        when(codingChallengeRepository.save(codingChallenge)).thenReturn(codingChallenge);

        CodingChallenge updatedCodingChallenge = codingChallengeService.saveCodingChallenge(codingChallengeDTO);

        assertNotNull(updatedCodingChallenge);
        verify(codingChallengeRepository, times(1)).save(codingChallenge);
    }

    @Test
    public void whenDeleteCodingChallenge_thenVerifyDeletion() {
        doNothing().when(codingChallengeRepository).deleteById(1L);

        codingChallengeService.deleteCodingChallenge(1L);

        verify(codingChallengeRepository, times(1)).deleteById(1L);
    }
}