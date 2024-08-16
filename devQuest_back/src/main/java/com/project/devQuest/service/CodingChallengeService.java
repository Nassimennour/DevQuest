package com.project.devQuest.service;

import com.project.devQuest.dto.CodingChallengeDTO;
import com.project.devQuest.model.CodingChallenge;
import com.project.devQuest.model.Difficulty;
import com.project.devQuest.repository.CodingChallengeRepository;
import com.project.devQuest.repository.TechnologyRepository;
import com.project.devQuest.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CodingChallengeService {
    @Autowired
    private CodingChallengeRepository codingChallengeRepository;
    @Autowired
    private TechnologyRepository technologyRepository;
    @Autowired
    private UserRepository userRepository;
    private static final Logger logger = LoggerFactory.getLogger(CodingChallengeService.class);

    public List<CodingChallenge> getAllCodingChallenges() {
        logger.info("Fetching all coding challenges");
        return codingChallengeRepository.findAll();
    }

    public Optional<CodingChallenge> getCodingChallengeById(long id) {
        logger.info("Fetching coding challenge with id: " + id);
        return codingChallengeRepository.findById(id);
    }

    public List<CodingChallenge> getCodingChallengesByTechnologyId(long technologyId) {
        logger.info("Fetching coding challenges by technology id: " + technologyId);
        return codingChallengeRepository.findByTechnologyId(technologyId);
    }

    public List<CodingChallenge> getCodingChallengesByCreatorId(long creatorId) {
        logger.info("Fetching coding challenges by creator id: " + creatorId);
        return codingChallengeRepository.findByCreatorId(creatorId);
    }

    public List<CodingChallenge> getCodingChallengesByDifficulty(String difficulty) {
        logger.info("Fetching coding challenges by difficulty: " + difficulty);
        return codingChallengeRepository.findByDifficulty(Difficulty.valueOf(difficulty));
    }

    public List<CodingChallenge> getCodingChallengesByCreatorIdAndTechnologyId(long creatorId, long technologyId) {
        logger.info("Fetching coding challenges by creator id: " + creatorId + " and technology id: " + technologyId);
        return codingChallengeRepository.findByCreatorIdAndTechnologyId(creatorId, technologyId);
    }

    public CodingChallenge saveCodingChallenge(CodingChallengeDTO codingChallengeDTO) {
        logger.info("Saving coding challenge");
        CodingChallenge codingChallenge = new CodingChallenge();
        codingChallenge.setTitle(codingChallengeDTO.getTitle());
        codingChallenge.setDescription(codingChallengeDTO.getDescription());
        codingChallenge.setDifficulty(Difficulty.valueOf(codingChallengeDTO.getDifficulty()));
        codingChallenge.setTechnology(technologyRepository.findById(codingChallengeDTO.getTechnologyId()).orElseThrow(() -> new IllegalArgumentException("Technology not found")));
        codingChallenge.setCreator(userRepository.findById(codingChallengeDTO.getCreatorId()).orElseThrow(() -> new IllegalArgumentException("User not found")));
        return codingChallengeRepository.save(codingChallenge);
    }

    public void deleteCodingChallenge(long id) {
        logger.info("Deleting coding challenge with id: " + id);
        codingChallengeRepository.deleteById(id);
    }


}
