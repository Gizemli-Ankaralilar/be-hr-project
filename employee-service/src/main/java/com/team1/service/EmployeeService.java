package com.team1.service;


import com.team1.dto.request.RegisterRequestEmployeeDto;
import com.team1.exception.EmployeeManagerException;
import com.team1.exception.ErrorType;
import com.team1.mapper.IEmployeeMapper;
import com.team1.repository.entity.Employee;
import com.team1.repository.entity.IEmployeeRepository;
import com.team1.utility.ServiceManager;
import org.springframework.stereotype.Service;


@Service
public class EmployeeService extends ServiceManager<Employee, Long> {

    private final IEmployeeRepository employeeRepository;

    public EmployeeService(IEmployeeRepository employeeRepository) {
        super(employeeRepository);
        this.employeeRepository = employeeRepository;
    }

    public Boolean register(RegisterRequestEmployeeDto dto) {
        try {
            Employee employee = IEmployeeMapper.INSTANCE.toEmployee(dto);
            save(employee);
            return true;
        }catch (Exception e) {
            throw new EmployeeManagerException(ErrorType.BAD_REQUEST);
        }
    }
}
