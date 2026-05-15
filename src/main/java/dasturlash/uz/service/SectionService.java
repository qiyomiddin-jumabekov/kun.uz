package dasturlash.uz.service;

import dasturlash.uz.dto.section.RequestForSection;
import dasturlash.uz.entity.Section;
import dasturlash.uz.enums.Visible;
import dasturlash.uz.projections.section.ResponseProjectionSession;
import dasturlash.uz.projections.section.SectionProjectionPagination;
import dasturlash.uz.repository.SectionRepository;
import jakarta.validation.constraints.Min;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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

    public String updateSectionById(Integer id, RequestForSection request) {
        Section section = getSectionById(id);
        section.setNameRu(request.nameRu());
        section.setNameEn(request.nameEn());
        section.setNameUz(request.nameUz());
        section.setOrderNumber(request.orderNumber());
        section.setKey(request.key());
        sectionRepository.save(section);
        return "Section succesfully updated";
    }


    public Section getSectionById(Integer id) {
        return sectionRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Section not found"));
    }

    public String deleteSectionById(Integer id) {
        Section section = getSectionById(id);
        section.setVisible(Visible.INACTIVE);
        sectionRepository.save(section);
        return "Section succesfully deleted";
    }

    public Page<SectionProjectionPagination> getSectionList(int page, int size) {
        Pageable pageable = PageRequest.of(page - 1, size);
        return sectionRepository.findAllByVisible(Visible.ACTIVE, pageable);
    }

    public Page<ResponseProjectionSession> getSectionsByLang(int page, int size, String lang) {
        Pageable pageable = PageRequest.of(page - 1, size);
        return sectionRepository.getSectionsByLang(lang, pageable);
    }
}
