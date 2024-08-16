package com.project.devQuest.service;

import com.project.devQuest.dto.SolutionDTO;
import com.project.devQuest.model.CodingChallenge;
import com.project.devQuest.model.Solution;
import com.project.devQuest.model.User;
import com.project.devQuest.repository.CodingChallengeRepository;
import com.project.devQuest.repository.SolutionRepository;
import com.project.devQuest.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SolutionService {
    @Autowired
    private SolutionRepository solutionRepository;
    @Autowired
    private CodingChallengeRepository codingChallengeRepository;
    @Autowired
    private UserRepository userRepository;
    private final static Logger logger = LoggerFactory.getLogger(SolutionService.class);


    public void deleteSolution(Long id) {
        logger.info("Deleting solution with id: " + id);
        solutionRepository.deleteById(id);
    }

    public List<Solution> getAllSolutions() {
        logger.info("Fetching all solutions");
        return solutionRepository.findAll();
    }

    public List<Solution> getAllSolutionsForChallenge(Long challengeId) {
        logger.info("Fetching all solutions for challenge with id: " + challengeId);
        return solutionRepository.findByCodingChallengeId(challengeId);
    }

    public List<Solution> getAllSolutionsForUser(Long userId) {
        logger.info("Fetching all solutions for user with id: " + userId);
        return solutionRepository.findByUserId(userId);
    }

    public Solution getSolutionById(Long id) {
        logger.info("Fetching solution with id: " + id);
        return solutionRepository.findById(id).orElseThrow(() -> new RuntimeException("Solution not found"));
    }

    public Solution saveSolution(SolutionDTO solutionDTO) {
        logger.info("Adding solution: " + solutionDTO.getCode());
        Solution solution = new Solution();
        solution.setCode(solutionDTO.getCode());
        solution.setSubmissionDate(solutionDTO.getSubmissionDate());
        solution.setUser(userRepository.findById(solutionDTO.getUserId()).orElseThrow(() -> new RuntimeException("User not found")));
        solution.setCodingChallenge(codingChallengeRepository.findById(solutionDTO.getCodingChallengeId()).orElseThrow(() -> new RuntimeException("Coding challenge not found")));
        Solution savedSolution = solutionRepository.save(solution);
        // ADD CodingChallenge to user's list of solved challenges
        User user = userRepository.findById(solutionDTO.getUserId()).orElseThrow(() -> new RuntimeException("User not found"));
        CodingChallenge codingChallenge = codingChallengeRepository.findById(solutionDTO.getCodingChallengeId()).orElseThrow(() -> new RuntimeException("Coding challenge not found"));
        user.getCodingChallengeHistory().add(codingChallenge);
        return savedSolution;
    }

    public Solution updateSolution(Solution solution) {
        logger.info("Updating solution with id: " + solution.getId());
        return solutionRepository.save(solution);
    }

}
