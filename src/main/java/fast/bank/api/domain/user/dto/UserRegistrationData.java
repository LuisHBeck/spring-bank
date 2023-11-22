package fast.bank.api.domain.user.dto;

import com.fasterxml.jackson.annotation.JsonAlias;
import jakarta.validation.constraints.*;

public record UserRegistrationData(

        @JsonAlias({"id"})
        Long registry,
        @NotBlank
        String password
) {
}
