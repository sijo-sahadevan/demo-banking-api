package demo.banking.api.controller;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import demo.banking.api.model.Transaction;
import demo.banking.api.service.TransactionService;

@RestController
@RequestMapping("/api/retailbanking")
public class TransactionController {
	@Autowired
	TransactionService service;

	Logger logger = LoggerFactory.getLogger(TransactionController.class);

	@GetMapping("/transaction/new/{userid}/{faccount}/{toaccount}/{desc}/{amount}")
	public int makeTransfer(@PathVariable Map<String, String> params) {
		String userID = params.get("userid");
		String fromAccount = params.get("faccount");
		String toAccount = params.get("toaccount");
		String description = params.get("desc");
		BigDecimal amount = new BigDecimal(params.get("amount"));
		logger.debug("request - make transfer for the user '" + userID + "' from account '" + fromAccount
				+ "' to account '" + toAccount + "' for the amount '" + amount + "'.");
		return (int) service.doTransfer(userID, fromAccount, toAccount, description, amount);
	}

	@GetMapping("/transaction/find/{userid}/{accountid}/{sdate}/{edate}/{status}")
	public List<Transaction> getTransactions(@PathVariable Map<String, String> params) {
		String userID = params.get("userid");
		String accountNumber = params.get("accountid");
		String startDate = params.get("sdate");
		String endDate = params.get("edate");
		String status = params.get("status");

		SimpleDateFormat clientFormat = new SimpleDateFormat("yyyy-MM-dd");
		SimpleDateFormat dbFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss.SSS");
		Timestamp startDateTimestamp = null, endDateTimestamp = null;

		try {
			Date clientStartDate = clientFormat.parse(startDate);
			String dbStartDate = dbFormat.format(clientStartDate);
			Date parsedDbStartDate = dbFormat.parse(dbStartDate);
			startDateTimestamp = new Timestamp(parsedDbStartDate.getTime());

			Date clientEndDate = clientFormat.parse(endDate);
			String dbEndDate = dbFormat.format(clientEndDate);
			Date parsedDbEndDate = dbFormat.parse(dbEndDate);
			endDateTimestamp = new Timestamp(parsedDbEndDate.getTime());
		} catch (Exception e) {

		}
		logger.debug("request - fetch transactions for user '" + userID + "' & account '" + accountNumber
				+ "' between the dates '" + startDate + "' and '" + endDate + "'.");
		return (List<Transaction>) service.getTransactions(userID, accountNumber, startDateTimestamp, endDateTimestamp,
				status);
	}
}
