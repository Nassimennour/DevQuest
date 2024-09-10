package com.project.devQuest.service;

import com.project.devQuest.dto.SolutionDTO;
import com.project.devQuest.model.CodingChallenge;
import com.project.devQuest.model.Solution;
import com.project.devQuest.model.User;
import com.project.devQuest.model.UserProgress;
import com.project.devQuest.repository.CodingChallengeRepository;
import com.project.devQuest.repository.SolutionRepository;
import com.project.devQuest.repository.UserProgressRepository;
import com.project.devQuest.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class SolutionServiceTest {

    @InjectMocks
    private SolutionService solutionService;

    @Mock
    private SolutionRepository solutionRepository;

    @Mock
    private CodingChallengeRepository codingChallengeRepository;

    @Mock
    private UserRepository userRepository;

    @Mock
    private UserProgressRepository userProgressRepository;

    @Test
    public void whenDeleteSolution_thenVerifyDeletion() {
        doNothing().when(solutionRepository).deleteById(1L);

        solutionService.deleteSolution(1L);

        verify(solutionRepository, times(1)).deleteById(1L);
    }

    @Test
    public void whenGetAllSolutions_thenReturnSolutionList() {
        Solution solution = new Solution();
        when(solutionRepository.findAll()).thenReturn(Collections.singletonList(solution));

        List<Solution> solutionList = solutionService.getAllSolutions();

        assertEquals(1, solutionList.size());
        verify(solutionRepository, times(1)).findAll();
    }

    @Test
    public void whenGetAllSolutionsForChallenge_thenReturnSolutionList() {
        Solution solution = new Solution();
        when(solutionRepository.findByCodingChallengeId(1L)).thenReturn(Collections.singletonList(solution));

        List<Solution> solutionList = solutionService.getAllSolutionsForChallenge(1L);

        assertEquals(1, solutionList.size());
        verify(solutionRepository, times(1)).findByCodingChallengeId(1L);
    }

    @Test
    public void whenGetAllSolutionsForUser_thenReturnSolutionList() {
        Solution solution = new Solution();
        when(solutionRepository.findByUserId(1L)).thenReturn(Collections.singletonList(solution));

        List<Solution> solutionList = solutionService.getAllSolutionsForUser(1L);

        assertEquals(1, solutionList.size());
        verify(solutionRepository, times(1)).findByUserId(1L);
    }

    @Test
    public void whenGetSolutionById_thenReturnSolution() {
        Solution solution = new Solution();
        when(solutionRepository.findById(1L)).thenReturn(Optional.of(solution));

        Solution foundSolution = solutionService.getSolutionById(1L);

        assertNotNull(foundSolution);
        verify(solutionRepository, times(1)).findById(1L);
    }

    @Test
    public void whenSaveSolution_thenReturnSavedSolution() {
        SolutionDTO solutionDTO = new SolutionDTO();
        solutionDTO.setCode("code");
        solutionDTO.setSubmissionDate(LocalDateTime.now());
        solutionDTO.setUserId(1L);
        solutionDTO.setCodingChallengeId(1L);

        User user = new User();
        user.setCodingChallengeHistory(new ArrayList<>());
        CodingChallenge codingChallenge = new CodingChallenge();
        Solution solution = new Solution();
        solution.setCode("code");

        when(userRepository.findById(1L)).thenReturn(Optional.of(user));
        when(codingChallengeRepository.findById(1L)).thenReturn(Optional.of(codingChallenge));
        when(solutionRepository.save(any(Solution.class))).thenReturn(solution);

        Solution savedSolution = solutionService.saveSolution(solutionDTO);

        assertNotNull(savedSolution);
        assertEquals("code", savedSolution.getCode());
        verify(userRepository, times(1)).findById(1L);
        verify(codingChallengeRepository, times(1)).findById(1L);
        verify(solutionRepository, times(1)).save(any(Solution.class));
    }


}