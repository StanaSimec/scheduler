package cz.simecek.scheduler.repository;

import cz.simecek.scheduler.model.Office;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OfficeRepository extends JpaRepository<Office, Long> {
}
