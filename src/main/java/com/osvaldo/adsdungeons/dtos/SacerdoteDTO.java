package com.osvaldo.adsdungeons.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public record SacerdoteDTO(@NotBlank String nome, @NotNull Integer vida, @NotNull Character type, Integer mana) {}
