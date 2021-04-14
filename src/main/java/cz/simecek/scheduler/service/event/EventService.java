package cz.simecek.scheduler.service.event;

import cz.simecek.scheduler.dto.EventDTO;

import java.util.List;

public interface EventService {
    List<EventDTO> getAll();

    EventDTO save(EventDTO eventDTO);

    EventDTO getDetail(Long id);

    void deleteById(Long id);

    EventDTO updateById(Long id, EventDTO eventDTO);
}
