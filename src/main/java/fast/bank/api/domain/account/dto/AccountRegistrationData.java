package fast.bank.api.domain.account.dto;

import com.fasterxml.jackson.annotation.JsonAlias;
import fast.bank.api.domain.account.model.AccountType;
import fast.bank.api.domain.user.model.User;
import jakarta.validation.constraints.NotNull;

public record AccountRegistrationData(
        @NotNull
        @JsonAlias({"user","user_id"})
        Long userRegistry,

        @NotNull
        @JsonAlias({"type"})
        AccountType accountType
) {
}
