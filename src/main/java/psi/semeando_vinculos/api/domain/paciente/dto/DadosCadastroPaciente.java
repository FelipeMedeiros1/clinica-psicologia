package psi.semeando_vinculos.api.domain.paciente.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import psi.semeando_vinculos.api.domain.endereco.dto.DadosEndereco;

public record DadosCadastroPaciente(
        @NotBlank
        String nome,
        @Email
        @NotBlank
        String email,
        @NotBlank
        @Pattern(regexp = "^(\\(\\d{2}\\)|\\d{2})\\s?\\d{4,5}-?\\d{4}$")
        String telefone,
        @NotBlank
        @Pattern(regexp = "\\d{3}\\.?\\d{3}\\.?\\d{3}\\-?\\d{2}")
        String cpf,
        @NotNull
        @Valid
        DadosEndereco endereco) {
}
