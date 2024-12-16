package com.wg.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.wg.constants.UserConstants;
import com.wg.exceptions.GeneralException;
import com.wg.model.Role;
import com.wg.model.User;
import com.wg.repository.interfaces.InterfaceUserDAO;

@Repository
public class UserDAO extends GenericDAO<User> implements InterfaceUserDAO {
	public UserDAO() {
	}

	@Override
	public User mapResultSetToEntity(ResultSet resultSet) throws SQLException {
		User user = new User();
		user.setUserId(resultSet.getString(UserConstants.USER_ID_COLUMN));
		user.setName(resultSet.getString(UserConstants.NAME_COLUMN));
		user.setEmail(resultSet.getString(UserConstants.EMAIL_COLUMN));
		user.setUsername(resultSet.getString(UserConstants.USERNAME_COLUMN));
		user.setPassword(resultSet.getString(UserConstants.PASSWORD_COLUMN));
		user.setAge(resultSet.getInt(UserConstants.AGE_COLUMN));
		user.setGender(resultSet.getString(UserConstants.GENDER_COLUMN));
		user.setContactNumber(resultSet.getString(UserConstants.CONTACT_NO_COLUMN));
		user.setAddress(resultSet.getString(UserConstants.ADDRESS_COLUMN));
		user.setRole(Role.valueOf(resultSet.getString(UserConstants.ROLE_COLUMN)));
		user.setDOB(resultSet.getDate(UserConstants.DOB_COLUMN).toLocalDate());
		user.setStandard(resultSet.getInt(UserConstants.STANDARD_COLUMN));
		user.setRollNo(resultSet.getString(UserConstants.ROLL_NO_COLUMN));
		user.setMentorOf(resultSet.getInt(UserConstants.MENTOR_OF_COLUMN));
		return user;
	}

	@Override
	public boolean addUser(User user) throws SQLException, ClassNotFoundException {
		String sqlQuery = String.format(
				"INSERT INTO User (UserId, username, name, age, password, email, role, dob, contactNumber, standard, gender, rollNo, mentorOf) VALUES ('%s','%s','%s','%s','%s','%s','%s', '%s','%s','%s','%s', '%s','%s')",
				user.getUserId(), user.getUsername(), user.getName(), user.getAge(), user.getPassword(),
				user.getEmail(), user.getRole().toString(), user.getDOB(), user.getContactNumber(), user.getStandard(),
				user.getGender(), user.getRollNo(), user.getMentorOf());

		return executeQuery(sqlQuery);
	}

	@Override
	public User getUserById(String userId) throws SQLException, ClassNotFoundException {
		String selectSQL = "SELECT * FROM User WHERE userid = \"" + userId + "\"";
		return executeGetQuery(selectSQL);
	}

	@Override
	public User getUserByUsername(String username) {
		String selectSQL = "SELECT * FROM User WHERE username = \"" + username + "\"";
		try {
			return executeGetQuery(selectSQL);
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<User> getClassDetails(int standard) throws SQLException, ClassNotFoundException {
		String selectSQL = "SELECT * FROM User WHERE standard = \"" + standard + "\"";
		return executeGetAllQuery(selectSQL);
	}

	@Override
	public boolean deleteUser(String id) throws SQLException, ClassNotFoundException {
		String selectSQL = "DELETE FROM User WHERE userid = \"" + id + "\"";
		return executeQuery(selectSQL);
	}

	@Override
	public int getTotalUserCount() throws ClassNotFoundException, SQLException {
		String selectSQL = "SELECT COUNT(*) FROM User";
		return executeCountQuery(selectSQL);
	}

	@Override
	public List<User> getAllUser(int pageNumber, int pageSize) throws SQLException, ClassNotFoundException {
		// Calculate the offset
		int offset = (pageNumber - 1) * pageSize;
		// Build the SQL query string dynamically with LIMIT and OFFSET
		String selectSQL = String.format("SELECT * FROM User LIMIT %d OFFSET %d", pageSize, offset);
		// Pass the generated SQL query to the executeGetAllQuery method
		List<User> users = executeGetAllQuery(selectSQL);
		if (users == null || users.isEmpty()) {
			return null;
		}
		return users;
	}

	@Override
	public boolean updateUser(String userId, User user) {
		String sqlQuery = String.format(
				"UPDATE User SET name = '%s', " + "dob = '%s', " + "contactNumber = '%s', " + "password = '%s', "
						+ "standard = '%s', " + "address = '%s', " + "username = '%s', " + "age = '%s', "
						+ "email = '%s', " + "gender = '%s', " + "rollNo = '%s', " + "mentorOf = '%s' "
						+ "WHERE userId = '%s';",
				user.getName(), user.getDOB(), user.getContactNumber(), user.getPassword(), user.getStandard(),
				user.getAddress(), user.getUsername(), user.getAge(), user.getEmail(), user.getGender(), user.getRole(),
				user.getMentorOf(), userId);
		Boolean flag = null;
		try {
			flag = executeQuery(sqlQuery);
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			throw new GeneralException(e.getMessage());
		}
		return flag;
	}
}