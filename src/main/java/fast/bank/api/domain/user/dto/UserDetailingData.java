package fast.bank.api.domain.user.dto;

import fast.bank.api.domain.user.model.User;

public record UserDetailingData(Long registry, Boolean isActive) {
    public UserDetailingData(User user) {
        this(user.getRegistry(), user.getIsActive());
    }
}
