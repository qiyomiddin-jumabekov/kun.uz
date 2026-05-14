package dasturlash.uz.service;

import dasturlash.uz.dto.region.RequestForRegion;
import dasturlash.uz.dto.region.ResponseDtoForRegionLang;
import dasturlash.uz.entity.Region;
import dasturlash.uz.enums.Visible;
import dasturlash.uz.projections.ProjectionRegionLang;
import dasturlash.uz.repository.RegionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class RegionService {
    @Autowired
    private RegionRepository regionRepository;

    public String createRegion(RequestForRegion request) {
        if (regionRepository.existsByKey(request.key())) {
            return "Region already exists by Region Key";
        }
        Region region = new Region();
        region.setKey(request.key());
        region.setNameUz(request.nameUz());
        region.setNameRu(request.nameRu());
        region.setNameEn(request.nameEn());
        region.setOrderNumber(request.orderNumber());
        regionRepository.save(region);
        return "Region successfully created";
    }

    public String updateById(Integer regionId, RequestForRegion request) {
        Region region = findById(regionId);
        region.setOrderNumber(request.orderNumber());
        region.setKey(request.key());
        region.setNameUz(request.nameUz());
        region.setNameRu(request.nameRu());
        region.setNameEn(request.nameEn());
        regionRepository.save(region);
        return "Region successfully updated";
    }

    public Region findById(Integer id) {
        return regionRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Region not found"));
    }

    public String deleteRegionById(Integer id) {
        Region region = findById(id);
        region.setVisible(Visible.INACTIVE);
        regionRepository.save(region);
        return "Region successfully deleted";
    }

    public Page<Region> getAllRegionsPagination(int page, int size) {
        Pageable pageable = PageRequest.of(page - 1, size);
        return regionRepository.findAll(pageable);
    }

    public Page<ResponseDtoForRegionLang> getRegionsByLang(String lang, int page, int size) {
        Pageable pageable = PageRequest.of(page - 1, size);
        return regionRepository.getRegionsByLang(lang, Visible.ACTIVE, pageable)
                .map(p -> {
                    ResponseDtoForRegionLang dto = new ResponseDtoForRegionLang();
                    dto.setId(p.getRegId());
                    dto.setKey(p.getRegKey());
                    dto.setName(p.getRegName());
                    return dto;
                });
    }
}
