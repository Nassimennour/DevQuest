package com.project.devQuest.service;

import com.project.devQuest.model.Dashboard;
import com.project.devQuest.model.Quizz;
import com.project.devQuest.model.CodingChallenge;
import com.project.devQuest.repository.DashboardRepository;
import com.project.devQuest.repository.QuizzRepository;
import com.project.devQuest.repository.CodingChallengeRepository;
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
public class DashboardServiceTest {

    @InjectMocks
    private DashboardService dashboardService;

    @Mock
    private DashboardRepository dashboardRepository;

    @Mock
    private UserRepository userRepository;

    @Mock
    private QuizzRepository quizzRepository;

    @Mock
    private CodingChallengeRepository codingChallengeRepository;

    @Test
    public void whenGetDashboardByUserId_thenReturnDashboard() {
        Dashboard dashboard = new Dashboard();
        when(dashboardRepository.findByUserId(1L)).thenReturn(dashboard);

        Dashboard foundDashboard = dashboardService.getDashboardByUserId(1L);

        assertNotNull(foundDashboard);
        verify(dashboardRepository, times(1)).findByUserId(1L);
    }

    @Test
    public void whenGetAllDashboards_thenReturnDashboardList() {
        Dashboard dashboard = new Dashboard();
        when(dashboardRepository.findAll()).thenReturn(Collections.singletonList(dashboard));

        List<Dashboard> dashboardList = dashboardService.getAllDashboards();

        assertEquals(1, dashboardList.size());
        verify(dashboardRepository, times(1)).findAll();
    }

    @Test
    public void whenDeleteDashboard_thenVerifyDeletion() {
        doNothing().when(dashboardRepository).deleteById(1L);

        dashboardService.deleteDashboard(1L);

        verify(dashboardRepository, times(1)).deleteById(1L);
    }

    @Test
    public void whenGetDashboardById_thenReturnDashboard() {
        Dashboard dashboard = new Dashboard();
        when(dashboardRepository.findById(1L)).thenReturn(Optional.of(dashboard));

        Dashboard foundDashboard = dashboardService.getDashboardById(1L);

        assertNotNull(foundDashboard);
        verify(dashboardRepository, times(1)).findById(1L);
    }

    @Test
    public void whenAddSuggestedQuizz_thenReturnUpdatedDashboard() {
        Dashboard dashboard = new Dashboard();
        Quizz quizz = new Quizz();
        when(dashboardRepository.findByUserId(1L)).thenReturn(dashboard);
        when(quizzRepository.findById(1L)).thenReturn(Optional.of(quizz));
        when(dashboardRepository.save(dashboard)).thenReturn(dashboard);

        Dashboard updatedDashboard = dashboardService.addSuggestedQuizz(1L, 1L);

        assertNotNull(updatedDashboard);
        verify(dashboardRepository, times(1)).findByUserId(1L);
        verify(quizzRepository, times(1)).findById(1L);
        verify(dashboardRepository, times(1)).save(dashboard);
    }

    @Test
    public void whenAddSuggestedCodingChallenge_thenReturnUpdatedDashboard() {
        Dashboard dashboard = new Dashboard();
        CodingChallenge codingChallenge = new CodingChallenge();
        when(dashboardRepository.findByUserId(1L)).thenReturn(dashboard);
        when(codingChallengeRepository.findById(1L)).thenReturn(Optional.of(codingChallenge));
        when(dashboardRepository.save(dashboard)).thenReturn(dashboard);

        Dashboard updatedDashboard = dashboardService.addSuggestedCodingChallenge(1L, 1L);

        assertNotNull(updatedDashboard);
        verify(dashboardRepository, times(1)).findByUserId(1L);
        verify(codingChallengeRepository, times(1)).findById(1L);
        verify(dashboardRepository, times(1)).save(dashboard);
    }

    @Test
    public void whenRemoveSuggestedQuizz_thenReturnUpdatedDashboard() {
        Dashboard dashboard = new Dashboard();
        Quizz quizz = new Quizz();
        when(dashboardRepository.findByUserId(1L)).thenReturn(dashboard);
        when(quizzRepository.findById(1L)).thenReturn(Optional.of(quizz));
        when(dashboardRepository.save(dashboard)).thenReturn(dashboard);

        Dashboard updatedDashboard = dashboardService.removeSuggestedQuizz(1L, 1L);

        assertNotNull(updatedDashboard);
        verify(dashboardRepository, times(1)).findByUserId(1L);
        verify(quizzRepository, times(1)).findById(1L);
        verify(dashboardRepository, times(1)).save(dashboard);
    }

    @Test
    public void whenRemoveSuggestedCodingChallenge_thenReturnUpdatedDashboard() {
        Dashboard dashboard = new Dashboard();
        CodingChallenge codingChallenge = new CodingChallenge();
        when(dashboardRepository.findByUserId(1L)).thenReturn(dashboard);
        when(codingChallengeRepository.findById(1L)).thenReturn(Optional.of(codingChallenge));
        when(dashboardRepository.save(dashboard)).thenReturn(dashboard);

        Dashboard updatedDashboard = dashboardService.removeSuggestedCodingChallenge(1L, 1L);

        assertNotNull(updatedDashboard);
        verify(dashboardRepository, times(1)).findByUserId(1L);
        verify(codingChallengeRepository, times(1)).findById(1L);
        verify(dashboardRepository, times(1)).save(dashboard);
    }

    @Test
    public void whenUpdateDashboard_thenReturnUpdatedDashboard() {
        Dashboard dashboard = new Dashboard();
        dashboard.setId(1L);
        when(dashboardRepository.existsById(1L)).thenReturn(true);
        when(dashboardRepository.save(dashboard)).thenReturn(dashboard);

        Dashboard updatedDashboard = dashboardService.updateDashboard(dashboard);

        assertNotNull(updatedDashboard);
        verify(dashboardRepository, times(1)).existsById(1L);
        verify(dashboardRepository, times(1)).save(dashboard);
    }
}