package cz.simecek.scheduler.controller.rest;

import cz.simecek.scheduler.dto.EmployeeDTO;
import cz.simecek.scheduler.service.employee.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/employee")
public class EmployeeRestController {

    private final EmployeeService employeeService;

    @Autowired
    public EmployeeRestController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping
    public List<EmployeeDTO> all() {
        return employeeService.getAll();
    }

    @PostMapping
    public EmployeeDTO create(@Valid @RequestBody EmployeeDTO employeeDTO) {
        return employeeService.save(employeeDTO);
    }

    @GetMapping("/{id}")
    public EmployeeDTO detail(@PathVariable Long id){
        return employeeService.getDetail(id);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id){
        employeeService.deleteById(id);
    }

    @PutMapping("/{id}")
    public EmployeeDTO update(@Valid @RequestBody EmployeeDTO employeeDTO, @PathVariable Long id){
        return employeeService.updateById(id, employeeDTO);
    }
}
