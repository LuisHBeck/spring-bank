package fast.bank.api.domain.account.dto;

import com.fasterxml.jackson.annotation.JsonAlias;
import jakarta.validation.constraints.NotNull;

public record AccountTransferData(
        @NotNull
        @JsonAlias({"receiver"})
        Long receiverAccNumber,

        @NotNull
        @JsonAlias({"transferring"})
        Long transferringAccNumber,

        @NotNull
        @JsonAlias({"amount"})
        Double transferAmount) {
}
