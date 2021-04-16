package cz.simecek.scheduler.service.employee;

import cz.simecek.scheduler.dto.EmployeeDTO;
import cz.simecek.scheduler.exception.EntityNotFoundException;
import cz.simecek.scheduler.model.Employee;
import cz.simecek.scheduler.model.Office;
import cz.simecek.scheduler.repository.EmployeeRepository;
import cz.simecek.scheduler.service.office.OfficeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final OfficeService officeService;

    @Autowired
    public EmployeeServiceImpl(EmployeeRepository employeeRepository, OfficeService officeService) {
        this.employeeRepository = employeeRepository;
        this.officeService = officeService;
    }

    private EmployeeDTO toEmployeeDTO(Employee employee) {
        EmployeeDTO employeeDTO = new EmployeeDTO();
        employeeDTO.setId(employee.getId());
        employeeDTO.setName(employee.getName());
        employeeDTO.setSurname(employee.getSurname());
        employeeDTO.setCode(employee.getCode());
        employeeDTO.setAddress(employee.getAddress());
        employeeDTO.setPositionName(employee.getPositionName());
        employeeDTO.setEvents(employee.getEvents());
        employeeDTO.setOfficeId(employee.getOffice().getId());
        return employeeDTO;
    }

    private List<EmployeeDTO> toEmployeeDTO(List<Employee> employees) {
        return employees.stream().map(this::toEmployeeDTO).collect(Collectors.toList());
    }

    private Employee toEmployee(EmployeeDTO employeeDTO) {
        Employee employee = new Employee();
        return toEmployee(employee, employeeDTO);
    }

    private Employee toEmployee(Employee employee, EmployeeDTO employeeDTO) {
        employee.setId(employeeDTO.getId());
        employee.setName(employeeDTO.getName());
        employee.setSurname(employeeDTO.getSurname());
        employee.setCode(employeeDTO.getCode());
        employee.setAddress(employeeDTO.getAddress());
        employee.setPositionName(employeeDTO.getPositionName());
        return employee;
    }

    @Override
    public Employee getDbEntityById(Long id) {
        return employeeRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Employee", id));
    }

    @Override
    public List<EmployeeDTO> getAll() {
        return this.toEmployeeDTO(employeeRepository.findAll());
    }

    @Override
    public EmployeeDTO save(EmployeeDTO employeeDTO) {
        Employee employee = toEmployee(employeeDTO);
        if (employeeDTO.getOfficeId() != null) {
            Office office = officeService.getDbEntityById(employeeDTO.getOfficeId());
            employee.setOffice(office);
        }
        Employee createdEmployee = employeeRepository.save(employee);
        return toEmployeeDTO(createdEmployee);
    }

    @Override
    public EmployeeDTO getDetail(Long id) {
        Employee employee = getDbEntityById(id);
        return toEmployeeDTO(employee);
    }

    @Override
    public void deleteById(Long id) {
        Employee employee = getDbEntityById(id);
        employeeRepository.delete(employee);
    }

    @Override
    public EmployeeDTO updateById(Long id, EmployeeDTO employeeDTO) {
        Employee employee = getDbEntityById(id);
        Employee toUpdateEmployee = toEmployee(employee, employeeDTO);
        toUpdateEmployee.setId(id);
        if (employeeDTO.getOfficeId() != null && !employeeDTO.getOfficeId().equals(toUpdateEmployee.getOffice().getId())) {
            Office office = officeService.getDbEntityById(employeeDTO.getOfficeId());
            employee.setOffice(office);
        }
        Employee updatedEmployee = employeeRepository.save(toUpdateEmployee);
        return toEmployeeDTO(updatedEmployee);
    }


}
