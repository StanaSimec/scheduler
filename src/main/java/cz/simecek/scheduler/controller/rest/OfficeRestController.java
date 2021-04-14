package cz.simecek.scheduler.controller.rest;

import cz.simecek.scheduler.dto.OfficeDTO;
import cz.simecek.scheduler.service.office.OfficeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/office")
public class OfficeRestController {

    private final OfficeService officeService;

    @Autowired
    public OfficeRestController(OfficeService officeService) {
        this.officeService = officeService;
    }

    @GetMapping
    public List<OfficeDTO> all() {
        return officeService.getAll();
    }

    @PostMapping
    public OfficeDTO create(@Valid @RequestBody OfficeDTO officeDTO) {
        return officeService.save(officeDTO);
    }

    @GetMapping("/{id}")
    public OfficeDTO detail(@PathVariable Long id) {
        return officeService.getDetail(id);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        officeService.deleteById(id);
    }

    @PutMapping("/{id}")
    public OfficeDTO update(@Valid @RequestBody OfficeDTO officeDTO, @PathVariable Long id) {
        return officeService.updateById(id, officeDTO);
    }
}
