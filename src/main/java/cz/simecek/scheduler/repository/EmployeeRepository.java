package cz.simecek.scheduler.repository;

import cz.simecek.scheduler.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
}
