package fast.bank.api.domain.card.dto;

import com.fasterxml.jackson.annotation.JsonAlias;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CardTransactionData(
        @NotNull
        @JsonAlias({"transaction_amount", "value"})
        Double amount
) {
}
