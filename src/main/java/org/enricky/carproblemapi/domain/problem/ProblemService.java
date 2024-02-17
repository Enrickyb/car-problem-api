package org.enricky.carproblemapi.domain.problem;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ProblemService {

    @Autowired
    private ProblemRepository problemRepository;

    public List<Problem> getAllProblems() {
        return problemRepository.findAll();
    }

    public Problem getProblemById(UUID id) {
        return problemRepository.findById(id).orElse(null);
    }

    public Optional<Problem> findById(UUID id){
        return problemRepository.findById(id);
    }

    public void saveProblem(Problem problem) {
        problemRepository.save(problem);
    }

    // Adicione métodos adicionais conforme necessário
}
