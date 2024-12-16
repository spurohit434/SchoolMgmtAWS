package com.wg.services;

import java.sql.SQLException;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wg.dto.FeeResponse;
import com.wg.exceptions.FeeNotAddedException;
import com.wg.exceptions.GeneralException;
import com.wg.exceptions.NotFoundExceptions;
import com.wg.helper.LoggingUtil;
import com.wg.model.Fee;
import com.wg.model.Transactions;
import com.wg.model.User;
import com.wg.repository.interfaces.InterfaceFeeDAO;
import com.wg.repository.interfaces.InterfaceUserDAO;

@Service
public class FeeService {
	private InterfaceFeeDAO feeDAO;
	private InterfaceUserDAO userDAO;
	Logger logger = LoggingUtil.getLogger(FeeService.class);

	@Autowired
	public FeeResponse response;

	public FeeService() {
	}

	@Autowired
	public FeeService(InterfaceFeeDAO feeDAO, InterfaceUserDAO userDAO) {
		this.feeDAO = feeDAO;
		this.userDAO = userDAO;
	}

	public double payFees(String userId) {
		try {
			Fee fees = null;
			fees = feeDAO.checkFees(userId);
			if (fees == null) {
				throw new GeneralException("Fees not added yet for user: " + userId);
			}
			double fine = 0;
			double feesAmount = fees.getFeeAmount();
			LocalDate deadLine = fees.getDeadline();
			LocalDate currentDate = LocalDate.now();
			if (currentDate.isAfter(deadLine)) {
				long overdueDays = ChronoUnit.DAYS.between(deadLine, currentDate);
				fine = overdueDays * 5.0;
			}
			System.out.println("The fine is " + fine);
			double totalFees = feesAmount + fine;
			System.out.println("Total Payalbe amount is: " + totalFees);
			boolean flag = feeDAO.payFees(userId);
			String notes = "fees paid by user";
			Transactions transaction = new Transactions(userId, totalFees, notes);
			boolean flag1 = feeDAO.addTransaction(transaction);
			if (flag == true) {
				return totalFees;
			} else {
				throw new GeneralException("Fees can not be paid for user: " + userId);
			}
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}

	public double checkFees(String userId) {
		try {
			Fee fees = feeDAO.checkFees(userId);
			if (fees == null) {
				throw new FeeNotAddedException("Fee not added yet for " + userId);
			} else {
				return fees.getFeeAmount();
			}
		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		}
		return 0;
	}

	public FeeResponse addFees(Fee fee, String id) {
		User user = null;
		fee.setStudentId(id);
		try {
			user = userDAO.getUserById(id);
			if (user == null) {
				throw new NotFoundExceptions("Student Does not Exist for Id " + id);
			}
			boolean flag = feeDAO.insertFees(fee);
			if (flag == true) {
				response.setFeeAdded(true);
				response.setFeeUpdated(false);
				response.setFeeAmount(fee.getFeeAmount());
				response.setFine(fee.getFine());
				response.setStudentId(id);
				return response;
			}
		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		}
		response.setFeeAdded(false);
		response.setFeeUpdated(false);
		response.setFeeAmount(0.0);
		response.setFine(0.0);
		response.setStudentId(id);
		return response;
	}

	public FeeResponse updateFees(Fee fee, String id) {
		User user = null;
		fee.setStudentId(id);
		boolean flag = false;
		try {
			user = userDAO.getUserById(id);
			if (user == null) {
				throw new NotFoundExceptions("Student Does not Exist for Id " + id);
			}
			flag = feeDAO.updateFees(fee);
		} catch (ClassNotFoundException |

				SQLException e) {
			e.printStackTrace();
		}
		if (flag == true) {
			System.out.println("Fees successfully updated");
			response.setFeeAdded(false);
			response.setFeeUpdated(true);
			response.setFeeAmount(fee.getFeeAmount());
			response.setFine(fee.getFine());
			response.setStudentId(fee.getStudentId());
			return response;
		}
		response.setFeeAdded(false);
		response.setFeeUpdated(false);
		response.setFeeAmount(0.0);
		response.setFine(0.0);
		response.setStudentId(fee.getStudentId());
		return response;
	}

	public double calculateFine(String userId) {
		try {
			Fee fee = null;
			fee = feeDAO.calculateFine(userId);
			if (fee == null) {
				System.out.println("No fine applicable as fees not added yet");
				return 0;
			}
			double fine = 0;
			double feeAmount = fee.getFeeAmount();
			LocalDate deadLine = fee.getDeadline();
			LocalDate currentDate = LocalDate.now();

			if (currentDate.isAfter(deadLine)) {
				long overdueDays = ChronoUnit.DAYS.between(deadLine, currentDate);
				fine = overdueDays * 5.0;
			}

			System.out.println("The feeAmount is " + feeAmount);
			System.out.println("The deadline is " + deadLine);
			System.out.println("The fine is " + fine);

			return fine;

		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		}

		return 0;
	}
}