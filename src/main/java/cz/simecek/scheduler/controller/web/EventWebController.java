package cz.simecek.scheduler.controller.web;

import cz.simecek.scheduler.dto.EventDTO;
import cz.simecek.scheduler.service.employee.EmployeeService;
import cz.simecek.scheduler.service.event.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
@RequestMapping("/web/event")
public class EventWebController {

    private final EventService eventService;
    private final EmployeeService employeeService;

    private final String REDIRECT_TO_INDEX = "redirect:/web/event";
    private final String NEW = "/event/new";
    private final String UPDATE = "/event/update";

    private final String eventDTO = "eventDTO";
    private final String employees = "employees";

    @Autowired
    public EventWebController(EventService eventService, EmployeeService employeeService) {
        this.eventService = eventService;
        this.employeeService = employeeService;
    }

    @GetMapping
    public String list(Model model) {
        model.addAttribute("events", eventService.getAll());
        return "/event/list";
    }

    @GetMapping("/detail/{id}")
    public String detail(@PathVariable Long id, Model model) {
        EventDTO event = eventService.getDetail(id);
        model.addAttribute(eventDTO, event);
        if(event.getEmployeeId() != null) {
            model.addAttribute("employee", employeeService.getDetail(event.getEmployeeId()));
        }
        return "/event/detail";
    }

    @GetMapping("/new")
    public String createForm(Model model) {
        model.addAttribute(eventDTO, new EventDTO());
        model.addAttribute(employees, employeeService.getAll());
        return NEW;
    }

    @PostMapping("/new")
    public String create(@Valid EventDTO eventDTO, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute(employees, employeeService.getAll());
            return NEW;
        }
        eventService.save(eventDTO);
        return REDIRECT_TO_INDEX;
    }

    @GetMapping("/update/{id}")
    public String updateForm(@PathVariable Long id, Model model) {
        model.addAttribute(eventDTO, eventService.getDetail(id));
        model.addAttribute(employees, employeeService.getAll());
        return UPDATE;
    }

    @PostMapping("/update/{id}")
    public String update(@PathVariable Long id, @Valid EventDTO eventDTO, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            eventDTO.setId(id);
            model.addAttribute(employees, employeeService.getAll());
            return UPDATE;
        }
        eventService.updateById(id, eventDTO);
        return REDIRECT_TO_INDEX;
    }

    @PostMapping("/delete/{id}")
    public String delete(@PathVariable Long id){
        eventService.deleteById(id);
        return REDIRECT_TO_INDEX;
    }
}
