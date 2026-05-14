package dasturlash.uz.service;

import dasturlash.uz.dto.section.RequestForSection;
import dasturlash.uz.entity.Section;
import dasturlash.uz.repository.SectionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SectionService {
    @Autowired
    private SectionRepository sectionRepository;

    public String addSection(RequestForSection request) {
        if (sectionRepository.existsByKey(request.key())) {
            return "Section already exists by Section Key";
        }
        Section section = new Section();
        section.setKey(request.key());
        section.setNameRu(request.nameRu());
        section.setNameEn(request.nameEn());
        section.setNameUz(request.nameUz());
        section.setOrderNumber(request.orderNumber());
        sectionRepository.save(section);
        return "Section succesfully added";
    }
}
