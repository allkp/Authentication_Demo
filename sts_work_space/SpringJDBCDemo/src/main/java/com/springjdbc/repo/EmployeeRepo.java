package com.springjdbc.repo;


import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.springjdbc.model.Employee;

@Repository
public class EmployeeRepo {

    private final JdbcTemplate jdbcTemplate;

    public EmployeeRepo(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public int save(Employee employee) {
        String sql = "INSERT INTO employees (name, department) VALUES (?, ?)";
        return jdbcTemplate.update(sql, employee.getName(), employee.getDepartment());
    }

    public List<Employee> findAll() {
        String sql = "SELECT * FROM employees";
        return jdbcTemplate.query(sql, (rs, rowNum) -> {
            Employee employee = new Employee();
            employee.setId(rs.getLong("id"));
            employee.setName(rs.getString("name"));
            employee.setDepartment(rs.getString("department"));
            return employee;
        });
    }

    @SuppressWarnings("deprecation")
	public Employee findById(Long id) {
        String sql = "SELECT * FROM employees WHERE id = ?";
        List<Employee> employee = jdbcTemplate.query(sql, new Object[]{id}, new EmployeeRowMapper());
        
        return employee.isEmpty() ? null : employee.get(0);
        
    }
    
    // RowMapper to map ResultSet to Employee
    private static class EmployeeRowMapper implements RowMapper<Employee> {
        @Override
        public Employee mapRow(ResultSet rs, int rowNum) throws SQLException {
            Employee employee = new Employee();
            employee.setId(rs.getLong("id"));
            employee.setName(rs.getString("name"));
            employee.setDepartment(rs.getString("department"));
            return employee;
        }
    }

    public int update(Employee employee) {
        String sql = "UPDATE employees SET name = ?, department = ? WHERE id = ?";
        return jdbcTemplate.update(sql, employee.getName(), employee.getDepartment(), employee.getId());
    }

    public int delete(Long id) {
        String sql = "DELETE FROM employees WHERE id = ?";
        return jdbcTemplate.update(sql, id);
    }
}
