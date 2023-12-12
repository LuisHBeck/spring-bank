package fast.bank.api.domain.account.dto;

import com.fasterxml.jackson.annotation.JsonAlias;
import jakarta.validation.constraints.NotNull;

public record AccountTransactionRequestData(
        @NotNull
        @JsonAlias({"receiver"})
        Long receiverAccNumber,

        @NotNull
        @JsonAlias({"sender"})
        Long senderAccNumber,

        @NotNull
        @JsonAlias({"amount"})
        Double transferAmount) {
}
