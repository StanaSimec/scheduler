package cz.simecek.scheduler.service.office;

import cz.simecek.scheduler.dto.OfficeDTO;
import cz.simecek.scheduler.model.Office;

import java.util.List;

public interface OfficeService{
    List<OfficeDTO> getAll();

    OfficeDTO save(OfficeDTO officeDTO);

    OfficeDTO getDetail(Long id);

    void deleteById(Long id);

    OfficeDTO updateById(Long id, OfficeDTO officeDTO);

    Office getDbEntityById(Long id);
}
