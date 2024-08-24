package com.project.devQuest.service;

import com.project.devQuest.dto.QuizzDTO;
import com.project.devQuest.model.Difficulty;
import com.project.devQuest.model.Quizz;
import com.project.devQuest.repository.QuizzRepository;
import com.project.devQuest.repository.TechnologyRepository;
import com.project.devQuest.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class QuizzService {

    @Autowired
    private QuizzRepository quizzRepository;
    @Autowired
    private TechnologyRepository technologyRepository;
    @Autowired
    private UserRepository userRepository;

    public Optional<Quizz> findById(Long id){
        log.info("Finding quizz by id: {}", id);
        return quizzRepository.findById(id);
    }

    public Boolean existsById(Long id){
        log.info("Checking if quizz exists by id: {}", id);
        return quizzRepository.existsById(id);
    }

    public List<Quizz> getAllQuizzes(){
        log.info("Finding all quizzes");
        return quizzRepository.findAll();
    }

    public Quizz createQuizz(QuizzDTO quizzDTO){
        log.info("Creating quizz: {}", quizzDTO.getTitle());
        Quizz quizz = new Quizz();
        quizz.setTitle(quizzDTO.getTitle());
        quizz.setTechnology(technologyRepository.findById(quizzDTO.getTechnologyId()).orElseThrow(() -> new IllegalArgumentException("Technology not found")));
        quizz.setCreator(userRepository.findById(quizzDTO.getCreatorId()).orElseThrow(() -> new IllegalArgumentException("User not found")));
        quizz.setDifficulty(quizzDTO.getDifficulty());
        quizz.setOverview(quizzDTO.getOverview());
        quizz.setDuration(quizzDTO.getDuration());
        quizz.setCreationDate(quizzDTO.getCreationDate());
        return quizzRepository.save(quizz);
    }

    public Quizz updateQuizz(Quizz quizz){
        if (!quizzRepository.existsById(quizz.getId())) {
            throw new IllegalArgumentException("Quizz not found");
        }
        log.info("Updating quizz: {}", quizz.getTitle());
        return quizzRepository.save(quizz);
    }

    public void deleteQuizz(Long id){
        log.info("Deleting quizz by id: {}", id);
        quizzRepository.deleteById(id);
    }

    public void deleteAllQuizzes(){
        log.info("Deleting all quizzes");
        quizzRepository.deleteAll();
    }

    public List<Quizz> getQuizzesByTechnologyId(Long technologyId){
        log.info("Finding quizzes by technology id: {}", technologyId);
        return quizzRepository.findByTechnologyId(technologyId);
    }

    public List<Quizz> getQuizzesByCreatorId(Long creatorId){
        log.info("Finding quizzes by creator id: {}", creatorId);
        return quizzRepository.findByCreatorId(creatorId);
    }

    public List<Quizz> getQuizzesByTitle(String title){
        log.info("Finding quizzes by title: {}", title);
        return quizzRepository.findByTitle(title);
    }

    public Quizz UpdateQuizz(Quizz quizz){
        log.info("Updating quizz: {}", quizz.getTitle());
        return quizzRepository.save(quizz);
    }

    public List<Quizz> getQuizzesByDifficulty(Difficulty difficulty){
        List<Quizz> quizzes = quizzRepository.findAll();
        return quizzes.stream().filter(quizz -> quizz.getDifficulty().equals(difficulty)).toList();
    }

    // Number of quizzes
    public long count(){
        return quizzRepository.count();
    }
}
