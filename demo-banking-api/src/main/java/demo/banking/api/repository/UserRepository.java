package demo.banking.api.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import demo.banking.api.model.User;

public interface UserRepository extends JpaRepository<User, String> {
	@Query("SELECT u FROM User u WHERE u.userID =?1 AND u.password=?2")
	User validateUser(String userID, String password);

	User findByUserID(String userID);

	@Transactional
	@Modifying
	@Query("UPDATE User u set u.password=?2 WHERE u.userID =?1")
	int updatePassword(String userID, String newPassword);
}
