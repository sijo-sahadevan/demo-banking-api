package demo.banking.api.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import demo.banking.api.model.Account;

@Repository
public interface AccountRepository extends JpaRepository<Account, String> {
	List<Account> findByUserID(String userID);

	@Transactional
	@Modifying
	@Query("UPDATE Account a set a.nickName=?3 WHERE a.userID =?1 AND a.accountNumber=?2")
	public void createORUpdateAccountNickName(String userID, String accountID, String nickName);
}
