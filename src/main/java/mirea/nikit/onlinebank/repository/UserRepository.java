package mirea.nikit.onlinebank.repository;

import mirea.nikit.onlinebank.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
