package cz.simecek.scheduler.service.event;

import cz.simecek.scheduler.dto.EventDTO;
import cz.simecek.scheduler.exception.EntityNotFoundException;
import cz.simecek.scheduler.model.Employee;
import cz.simecek.scheduler.model.Event;
import cz.simecek.scheduler.repository.EventRepository;
import cz.simecek.scheduler.service.employee.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class EventServiceImpl implements EventService{

    private final EventRepository eventRepository;
    private final EmployeeService employeeService;

    @Autowired
    public EventServiceImpl(EventRepository eventRepository, EmployeeService employeeService) {
        this.eventRepository = eventRepository;
        this.employeeService = employeeService;
    }

    private EventDTO toEventDTO(Event event){
        EventDTO eventDTO = new EventDTO();
        eventDTO.setId(event.getId());
        eventDTO.setType(event.getType());
        eventDTO.setCreatedAt(event.getCreatedAt());
        eventDTO.setEmployeeId(event.getEmployee().getId());
        return eventDTO;
    }

    private List<EventDTO> toEventDTO(List<Event> events){
        return events.stream().map(this::toEventDTO).collect(Collectors.toList());
    }

    private Event toEvent(EventDTO eventDTO){
        Event event = new Event();
        return toEvent(event, eventDTO);
    }

    private Event toEvent(Event event, EventDTO eventDTO){
        event.setId(eventDTO.getId());
        event.setType(eventDTO.getType());
        return event;
    }

    private Event getById(Long id){
        return eventRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Event", id));
    }

    @Override
    public List<EventDTO> getAll(){
        return toEventDTO(eventRepository.findAll());
    }

    @Override
    public EventDTO save(EventDTO eventDTO) {
        Event event = toEvent(eventDTO);
        if(eventDTO.getEmployeeId() != null){
            Employee employee = employeeService.getDbEntityById(eventDTO.getEmployeeId());
            event.setEmployee(employee);
        }
        event.setCreatedAt(new Timestamp(System.currentTimeMillis()));
        return toEventDTO(eventRepository.save(event));
    }

    @Override
    public EventDTO getDetail(Long id) {
        return toEventDTO(getById(id));
    }

    @Override
    public void deleteById(Long id) {
        eventRepository.delete(getById(id));
    }

    @Override
    public EventDTO updateById(Long id, EventDTO eventDTO) {
        Event event = getById(id);
        Event toUpdateEvent = toEvent(event, eventDTO);
        toUpdateEvent.setId(id);
        if(eventDTO.getEmployeeId() != null && !eventDTO.getEmployeeId().equals(toUpdateEvent.getEmployee().getId())){
            Employee employee = employeeService.getDbEntityById(eventDTO.getEmployeeId());
            toUpdateEvent.setEmployee(employee);
        }
        Event updatedEvent = eventRepository.save(toUpdateEvent);
        return toEventDTO(updatedEvent);
    }
}
