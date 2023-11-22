package fast.bank.api.domain.user.dto;

import fast.bank.api.domain.user.model.User;

public record UserListData(Long registry, Boolean isActive) {
    public UserListData(User user) {
        this(user.getRegistry(), user.getIsActive());
    }
}
