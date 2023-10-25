package com.team1.mapper;

import com.team1.dto.request.RegisterRequestEmployeeDto;
import com.team1.repository.entity.Employee;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-10-25T09:39:24+0300",
    comments = "version: 1.5.5.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-8.2.jar, environment: Java 17.0.8 (Oracle Corporation)"
)
@Component
public class IEmployeeMapperImpl implements IEmployeeMapper {

    @Override
    public Employee toEmployee(RegisterRequestEmployeeDto dto) {
        if ( dto == null ) {
            return null;
        }

        Employee.EmployeeBuilder<?, ?> employee = Employee.builder();

        employee.companyId( dto.getCompanyId() );
        employee.fieldOfWork( dto.getFieldOfWork() );
        employee.role( dto.getRole() );

        return employee.build();
    }
}
