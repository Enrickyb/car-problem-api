package org.enricky.carproblemapi.domain.problem;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record ProblemDTO(@NotBlank String name, @NotBlank String description, @NotBlank String severity, @NotNull int recall ){}

