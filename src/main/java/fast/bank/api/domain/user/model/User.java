package fast.bank.api.domain.user.model;

import fast.bank.api.domain.user.dto.UserRegistrationData;
import jakarta.persistence.*;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Table(name = "Users")
@Entity(name = "users")
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
@Getter
public class User {

    @Id
    private Long registry;

    private String password;

    private Boolean isActive;

    public User(UserRegistrationData data) {
        this.registry = data.registry();
        this.password = data.password();
        this.isActive = true;
    }
}
