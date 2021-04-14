package cz.simecek.scheduler.service.office;

import cz.simecek.scheduler.dto.OfficeDTO;
import cz.simecek.scheduler.exception.EntityNotFoundException;
import cz.simecek.scheduler.model.Office;
import cz.simecek.scheduler.repository.OfficeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class OfficeServiceImpl implements OfficeService {

    private final OfficeRepository officeRepository;

    @Autowired
    public OfficeServiceImpl(OfficeRepository officeRepository) {
        this.officeRepository = officeRepository;
    }

    private OfficeDTO toOfficeDTO(Office office) {
        OfficeDTO officeDTO = new OfficeDTO();
        officeDTO.setId(office.getId());
        officeDTO.setCity(office.getCity());
        officeDTO.setAddress(office.getAddress());
        officeDTO.setCapacity(office.getCapacity());
        return officeDTO;
    }

    private List<OfficeDTO> toOfficeDTO(List<Office> offices) {
        return offices.stream().map(this::toOfficeDTO).collect(Collectors.toList());
    }

    private Office toOffice(OfficeDTO officeDTO) {
        Office office = new Office();
        return toOffice(office, officeDTO);
    }

    private Office toOffice(Office office, OfficeDTO officeDTO) {
        office.setId(officeDTO.getId());
        office.setCity(officeDTO.getCity());
        office.setAddress(officeDTO.getAddress());
        office.setCapacity(officeDTO.getCapacity());
        return office;
    }

    @Override
    public Office getDbEntityById(Long id) {
        return officeRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Office", id));
    }

    @Override
    public List<OfficeDTO> getAll() {
        return toOfficeDTO(officeRepository.findAll());
    }

    @Override
    public OfficeDTO save(OfficeDTO officeDTO) {
        Office office = toOffice(officeDTO);
        Office createdOffice = officeRepository.save(office);
        return toOfficeDTO(createdOffice);
    }

    @Override
    public OfficeDTO getDetail(Long id) {
        return toOfficeDTO(getDbEntityById(id));
    }

    @Override
    public void deleteById(Long id) {
        Office office = getDbEntityById(id);
        officeRepository.delete(office);
    }

    @Override
    public OfficeDTO updateById(Long id, OfficeDTO officeDTO) {
        Office office = getDbEntityById(id);
        Office toUpdateOffice = toOffice(office, officeDTO);
        toUpdateOffice.setId(id);
        Office updatedOffice = officeRepository.save(toUpdateOffice);
        return toOfficeDTO(updatedOffice);
    }
}
