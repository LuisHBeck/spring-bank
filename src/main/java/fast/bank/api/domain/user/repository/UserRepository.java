package fast.bank.api.domain.user.repository;

import fast.bank.api.domain.user.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UserRepository extends JpaRepository<User, Long> {
    Page<User> findAllByIsActiveTrue(Pageable pagination);

    boolean existsByRegistryAndIsActiveTrue(Long registry);
}
