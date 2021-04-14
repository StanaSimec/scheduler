package cz.simecek.scheduler.controller.rest;

import cz.simecek.scheduler.dto.EventDTO;
import cz.simecek.scheduler.service.event.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/event")
public class EventRestController {

    private final EventService eventService;

    @Autowired
    public EventRestController(EventService eventService) {
        this.eventService = eventService;
    }

    @GetMapping
    public List<EventDTO> all(){
        return eventService.getAll();
    }

    @PostMapping
    public EventDTO create(@Valid @RequestBody EventDTO eventDTO){
        return eventService.save(eventDTO);
    }

    @GetMapping("/{id}")
    public EventDTO detail(@PathVariable Long id){
        return eventService.getDetail(id);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id){
        eventService.deleteById(id);
    }

    @PutMapping("/{id}")
    public EventDTO update(@Valid @RequestBody EventDTO eventDTO, @PathVariable Long id){
        return eventService.updateById(id, eventDTO);
    }
}
