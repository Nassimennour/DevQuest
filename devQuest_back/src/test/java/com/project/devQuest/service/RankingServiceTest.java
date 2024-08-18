package com.project.devQuest.service;
import com.project.devQuest.dto.RankingDTO;
import com.project.devQuest.model.Ranking;
import com.project.devQuest.model.User;
import com.project.devQuest.repository.RankingRepository;
import com.project.devQuest.repository.UserRepository;
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
public class RankingServiceTest {

    @InjectMocks
    private RankingService rankingService;

    @Mock
    private RankingRepository rankingRepository;

    @Mock
    private UserRepository userRepository;

    @Test
    public void whenGetAllRankings_thenReturnRankingList() {
        Ranking ranking = new Ranking();
        when(rankingRepository.findAll()).thenReturn(Collections.singletonList(ranking));

        List<Ranking> rankingList = rankingService.getAllRankings();

        assertEquals(1, rankingList.size());
        verify(rankingRepository, times(1)).findAll();
    }

    @Test
    public void whenCreateRanking_thenReturnSavedRanking() {
        RankingDTO rankingDTO = new RankingDTO();
        rankingDTO.setUserId(1L);
        rankingDTO.setScore(100);
        rankingDTO.setLevel(1);
        rankingDTO.setPosition(1);

        Ranking ranking = new Ranking();
        when(userRepository.findById(1L)).thenReturn(Optional.of(new User()));
        when(rankingRepository.save(any(Ranking.class))).thenReturn(ranking);

        Ranking savedRanking = rankingService.createRanking(rankingDTO);

        assertNotNull(savedRanking);
        verify(userRepository, times(1)).findById(1L);
        verify(rankingRepository, times(1)).save(any(Ranking.class));
    }

    @Test
    public void whenUpdateRanking_thenReturnUpdatedRanking() {
        Ranking ranking = new Ranking();
        ranking.setId(1L);
        when(rankingRepository.save(ranking)).thenReturn(ranking);
        when((rankingRepository.existsById(1L))).thenReturn(true);
        Ranking updatedRanking = rankingService.updateRanking(ranking);

        assertNotNull(updatedRanking);
        verify(rankingRepository, times(1)).save(ranking);
    }

    @Test
    public void whenDeleteRanking_thenVerifyDeletion() {
        doNothing().when(rankingRepository).deleteById(1L);
        when(rankingRepository.existsById(1L)).thenReturn(true);
        rankingService.deleteRanking(1L);

        verify(rankingRepository, times(1)).deleteById(1L);
    }

    @Test
    public void whenExistsById_thenReturnTrue() {
        when(rankingRepository.existsById(1L)).thenReturn(true);

        boolean exists = rankingService.existsById(1L);

        assertTrue(exists);
        verify(rankingRepository, times(1)).existsById(1L);
    }

    @Test
    public void whenGetRankingById_thenReturnRanking() {
        Ranking ranking = new Ranking();
        when(rankingRepository.findById(1L)).thenReturn(Optional.of(ranking));

        Ranking foundRanking = rankingService.getRankingById(1L);

        assertNotNull(foundRanking);
        verify(rankingRepository, times(1)).findById(1L);
    }

    @Test
    public void whenGetRankingByUserId_thenReturnRanking() {
        Ranking ranking = new Ranking();
        when(rankingRepository.findByUserId(1L)).thenReturn(Optional.of(ranking));

        Optional<Ranking> foundRanking = rankingService.getRankingByUserId(1L);

        assertTrue(foundRanking.isPresent());
        verify(rankingRepository, times(1)).findByUserId(1L);
    }
}