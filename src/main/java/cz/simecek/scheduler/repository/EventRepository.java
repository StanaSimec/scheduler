package cz.simecek.scheduler.repository;

import cz.simecek.scheduler.model.Event;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EventRepository extends JpaRepository<Event, Long> {
}
