package com.ibm.training.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.ibm.training.bean.Bean;

@Repository("dao")
public class DaoClass implements DaoInterface {
	
	DataSource dataSource;
	NamedParameterJdbcTemplate jdbcTemplate;
	
	@Autowired
	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
		jdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
	}
	
	public boolean addAccountDetails(Bean b) {
		String nameQry = "INSERT INTO account_details VALUES (:accNum, :name, :balance, :phone)";
		if(jdbcTemplate.update(nameQry, new MapSqlParameterSource("accNum", b.getAccountNumber()).addValue("name", b.getName()).addValue("balance", b.getBalance()).addValue("phone", b.getPhoneNumber())) > 0)
			return true;
		return false;
	}

	public boolean updatePhoneNumber(Bean b) {
		String updateNumber = "UPDATE account_details SET PhoneNumber = :phone WHERE AccountNumber = :accNum";
		if(jdbcTemplate.update(updateNumber, new MapSqlParameterSource("phone", b.getPhoneNumber()).addValue("accNum", b.getAccountNumber())) > 0)
			return true;
		return false;
	}

	public boolean updateName(Bean b) {
		String updateName = "UPDATE account_details SET Name = :name WHERE AccountNumber = :accNum";
		if(jdbcTemplate.update(updateName, new MapSqlParameterSource("name", b.getName()).addValue("accNum", b.getAccountNumber())) > 0)
			return true;
		return false;
	}

	public Long Balance(Bean b) {
		String fetchBalance = "SELECT Balance FROM account_details WHERE PhoneNumber = :phone";
		b.setBalance(jdbcTemplate.queryForObject(fetchBalance, new MapSqlParameterSource("phone", b.getPhoneNumber()), Long.class));
		return b.getBalance();
	}

	public boolean depositMoney(Bean b) {		
		String depositQry = "INSERT INTO transaction_details (AccountNumber, transactionAmount, transactionTime) VALUES (:accNum, :transAmount, :transTime)";
		
		String getAccountNumber = "SELECT AccountNumber FROM account_details WHERE PhoneNumber = :phone";
		
		String UpdateAccBalanceQry = "UPDATE account_details"
				+ " SET account_details.Balance = account_details.Balance + :transAmount WHERE AccountNumber = :accNum";
		
		b.setAccountNumber(jdbcTemplate.queryForObject(getAccountNumber, new MapSqlParameterSource("phone", b.getPhoneNumber()), Integer.class));
		int n = 0;
		
		if(jdbcTemplate.update(depositQry, new MapSqlParameterSource("accNum", b.getAccountNumber()).addValue("transAmount", b.getTransactionAmount()).addValue("transTime", b.getTransactionTime())) > 0) {
			if(jdbcTemplate.update(UpdateAccBalanceQry, new MapSqlParameterSource("accNum", b.getAccountNumber()).addValue("transAmount", b.getTransactionAmount())) > 0)
				n++;
		}
		if(n==1)
			return true;
		else
			return false;
	}

	public int withdrawMoney(Bean b) {
		String withdrawQry = "INSERT INTO transaction_details (AccountNumber, transactionAmount, transactionTime) VALUES (:accNum, :transAmount, :transTime)";
		
		String getAccountNumber = "SELECT AccountNumber FROM account_details WHERE PhoneNumber = :phone";
		
		String UpdateAccBalanceQry = "UPDATE account_details "
				+ "SET account_details.Balance = account_details.Balance - :transAmount WHERE AccountNumber = :accNum";
		
		b.setAccountNumber(jdbcTemplate.queryForObject(getAccountNumber, new MapSqlParameterSource("phone", b.getPhoneNumber()), Integer.class));
		int n = 0;
		
		if(Balance(b) >= b.getTransactionAmount()) {
			if(jdbcTemplate.update(withdrawQry, new MapSqlParameterSource("accNum", b.getAccountNumber()).addValue("transAmount", -b.getTransactionAmount()).addValue("transTime", b.getTransactionTime())) > 0) {
				n++;
				if(jdbcTemplate.update(UpdateAccBalanceQry, new MapSqlParameterSource("accNum", b.getAccountNumber()).addValue("transAmount", b.getTransactionAmount())) > 0)
					n++;
			}
		}
		else
			n--;
		return n;
	}

	public List<Bean> transactionDetails(Bean b) {
		String queryGet = "SELECT * FROM transaction_details WHERE AccountNumber = :accNum";
		
		b.setAccountNumber(jdbcTemplate.queryForObject("SELECT AccountNumber FROM account_details WHERE PhoneNumber = :phone", new MapSqlParameterSource("phone", b.getPhoneNumber()) , Integer.class));
		return jdbcTemplate.query(queryGet, new MapSqlParameterSource("accNum", b.getAccountNumber()), new UserMapper());
	}
	
	class UserMapper implements RowMapper<Bean>{

		public Bean mapRow(ResultSet rs, int rowNum) throws SQLException {
			Bean trans = new Bean();
			trans.setTransactionTime(rs.getString("transactionTime"));
			trans.setAccountNumber(rs.getInt("AccountNumber"));
			trans.setTransactionAmount(rs.getLong("transactionAmount"));
			trans.setTransactionNumber(rs.getInt("Transaction Number"));
			return trans;
		}
	}
	
	public boolean checkNumber(Bean b) {
		String checkQry = "SELECT COUNT(*) FROM account_details WHERE PhoneNumber = :phone";
		if(jdbcTemplate.queryForObject(checkQry, new MapSqlParameterSource("phone", b.getPhoneNumber()), Integer.class) < 1)
			return false;
		return true;
	}
	
	public String getCurrentTime() {
		DateFormat df = new SimpleDateFormat("dd/MM/yy HH:mm:ss");
	    Calendar calobj = Calendar.getInstance();
	    return(df.format(calobj.getTime()));
	}
}