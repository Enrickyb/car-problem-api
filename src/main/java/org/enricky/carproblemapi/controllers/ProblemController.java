package org.enricky.carproblemapi.controllers;

import jakarta.validation.Valid;
import org.enricky.carproblemapi.domain.problem.Problem;
import org.enricky.carproblemapi.domain.problem.ProblemDTO;
import org.enricky.carproblemapi.domain.problem.ProblemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/problems")
public class ProblemController {

    @Autowired
    private ProblemService problemService;

    @GetMapping
    public ResponseEntity<List<Problem>> getAllProblems() {
        List<Problem> problems = problemService.getAllProblems();
        return ResponseEntity.ok(problems);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Problem> getProblemById(@PathVariable UUID id) {
        Problem problem = problemService.getProblemById(id);
        return ResponseEntity.ok(problem);
    }

    @PostMapping("/create")
    public ResponseEntity<String> createProblem(@Valid @RequestBody ProblemDTO problemDTO) {
        Problem problem = new Problem();
        problem.setName(problemDTO.name());
        problem.setRecall(problemDTO.recall());
        problem.setDescription(problemDTO.description());
        problem.setSeverity(problemDTO.severity());
        problemService.saveProblem(problem);
        return ResponseEntity.ok("Problema criado com sucesso.");
    }

    // Adicione métodos adicionais conforme necessário para atualização, exclusão, etc.
}
