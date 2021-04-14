package cz.simecek.scheduler.controller.web;

import cz.simecek.scheduler.dto.EmployeeDTO;
import cz.simecek.scheduler.service.employee.EmployeeService;
import cz.simecek.scheduler.service.office.OfficeService;
import org.dom4j.rule.Mode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("/web/employee")
public class EmployeeWebController {

    private final EmployeeService employeeService;
    private final OfficeService officeService;

    @Autowired
    public EmployeeWebController(EmployeeService employeeService, OfficeService officeService) {
        this.employeeService = employeeService;
        this.officeService = officeService;
    }

    @GetMapping
    public String all(Model model) {
        model.addAttribute("employees", employeeService.getAll());
        return "/employee/list";
    }

    @GetMapping("/new")
    public String createForm(Model model) {
        model.addAttribute("employeeDTO", new EmployeeDTO());
        model.addAttribute("offices", officeService.getAll());
        return "/employee/new";
    }

    @PostMapping("/new")
    public String create(@Valid EmployeeDTO employeeDTO, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("offices", officeService.getAll());
            return "/employee/new";
        }
        employeeService.save(employeeDTO);
        return "redirect:/web/employee";
    }

    @GetMapping("/detail/{id}")
    public String detail(@PathVariable Long id, Model model) {
        EmployeeDTO employeeDTO = employeeService.getDetail(id);
        model.addAttribute("employeeDTO", employeeService.getDetail(id));
        if (employeeDTO.getOfficeId() != null) {
            model.addAttribute("office", officeService.getDetail(employeeDTO.getOfficeId()));
        }
        return "/employee/detail";
    }

    @GetMapping("/update/{id}")
    public String updateForm(@PathVariable Long id, Model model){
        model.addAttribute("employeeDTO", employeeService.getDetail(id));
        model.addAttribute("offices", officeService.getAll());
        return "/employee/update";
    }

    @PostMapping("/update/{id}")
    public String update(@PathVariable Long id, @Valid EmployeeDTO employeeDTO, BindingResult bindingResult, Model model){
        if(bindingResult.hasErrors()){
            employeeDTO.setId(id);
            model.addAttribute("offices", officeService.getAll());
            return "/employee/update";
        }
        employeeService.updateById(id, employeeDTO);
        return "redirect:/web/employee";
    }

    @PostMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {
        employeeService.deleteById(id);
        return "redirect:/web/employee";
    }
}
