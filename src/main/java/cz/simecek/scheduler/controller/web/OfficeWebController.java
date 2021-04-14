package cz.simecek.scheduler.controller.web;

import cz.simecek.scheduler.dto.OfficeDTO;
import cz.simecek.scheduler.service.employee.EmployeeService;
import cz.simecek.scheduler.service.office.OfficeService;
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
@RequestMapping("/web/office")
public class OfficeWebController {

    private final OfficeService officeService;

    private final String NEW = "/office/new";
    private final String UPDATE = "/office/update";
    private final String REDIRECT_TO_INDEX = "redirect:/web/office";

    @Autowired
    public OfficeWebController(OfficeService officeService) {
        this.officeService = officeService;
    }

    @GetMapping
    public String all(Model model){
        model.addAttribute("offices", officeService.getAll());
        return "/office/list";
    }

    @GetMapping("/new")
    public String createForm(Model model){
        model.addAttribute("officeDTO", new OfficeDTO());
        return NEW;
    }

    @PostMapping("/new")
    public String create(@Valid OfficeDTO officeDTO, BindingResult bindingResult, Model model){
        if(bindingResult.hasErrors()){
            return NEW;
        }
        officeService.save(officeDTO);
        return REDIRECT_TO_INDEX;
    }

    @GetMapping("/detail/{id}")
    public String createForm(@PathVariable Long id, Model model){
        model.addAttribute("officeDTO", officeService.getDetail(id));
        return "/office/detail";
    }

    @GetMapping("/update/{id}")
    public String updateForm(@PathVariable Long id, Model model){
        model.addAttribute("officeDTO", officeService.getDetail(id));
        return UPDATE;
    }

    @PostMapping("/update/{id}")
    public String update(@PathVariable Long id, @Valid OfficeDTO officeDTO, BindingResult bindingResult, Model model){
        if(bindingResult.hasErrors()){
            officeDTO.setId(id);
            return UPDATE;
        }
        officeService.updateById(id, officeDTO);
        return REDIRECT_TO_INDEX;
    }

    @PostMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {
        officeService.deleteById(id);
        return REDIRECT_TO_INDEX;
    }
}
