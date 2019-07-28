package demo.banking.api.service;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import demo.banking.api.model.Transaction;
import demo.banking.api.repository.TransactionRepository;

@Component
public class TransactionService {

	@Autowired
	TransactionRepository repository;

	private static final String defaultStatus = "SCHEDULED";

	public int doTransfer(String userID, String fromAccount, String toAccount, String description, BigDecimal amount) {
		String transactionID = "TR" + System.currentTimeMillis();
		Timestamp currentDateTime = new Timestamp(System.currentTimeMillis());

		Transaction transaction = new Transaction();
		transaction.setTransactionID(transactionID);
		transaction.setUserID(userID);
		transaction.setFromAccount(fromAccount);
		transaction.setToAccount(toAccount);
		transaction.setDate(currentDateTime);
		transaction.setDescription(description);
		transaction.setAmount(amount);
		transaction.setStatus(defaultStatus);

		if (repository.save(transaction) != null) {
			return 1;
		} else {
			return 0;
		}
	}

	public List<Transaction> getTransactions(String userID, String accountNumber, Timestamp startDate,
			Timestamp endDate, String status) {
		return repository.getTransactions(userID, accountNumber, startDate, endDate, status);
	}
}
