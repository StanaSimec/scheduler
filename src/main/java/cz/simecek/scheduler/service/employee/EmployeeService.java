package cz.simecek.scheduler.service.employee;

import cz.simecek.scheduler.dto.EmployeeDTO;
import cz.simecek.scheduler.model.Employee;

import java.util.List;

public interface EmployeeService {
    List<EmployeeDTO> getAll();

    EmployeeDTO save(EmployeeDTO employeeDTO);

    EmployeeDTO getDetail(Long id);

    void deleteById(Long id);

    EmployeeDTO updateById(Long id, EmployeeDTO employeeDTO);

    Employee getDbEntityById(Long id);
}
