package demo.banking.api.repository;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import demo.banking.api.model.Transaction;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, String> {

	@Query("SELECT t FROM Transaction t WHERE t.userID=?1 AND (t.fromAccount=?2 OR t.toAccount=?2) AND t.date BETWEEN ?3 AND ?4 AND status=?5")
	public List<Transaction> getTransactions(String userID, String accountNumber, Timestamp startDate,
			Timestamp endDate, String status);

}
