package org.enricky.carproblemapi.domain.problem;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class ProblemService {

    @Autowired
    private ProblemRepository problemRepository;

    @Transactional
    public void saveProblem(Problem problem) {
        problemRepository.save(problem);
    }

    @Transactional
    public Problem getProblemById(UUID problemId) {
        return problemRepository.findById(problemId).orElse(null);
    }

    public Problem findById(UUID problemId) {
        return problemRepository.findById(problemId).orElse(null);
    }
}
