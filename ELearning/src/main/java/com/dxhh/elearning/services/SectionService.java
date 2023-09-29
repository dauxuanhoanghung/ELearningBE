package com.dxhh.elearning.services;

import com.dxhh.elearning.dto.request.NewSectionRequest;
import com.dxhh.elearning.pojos.Section;

import java.util.List;

public interface SectionService {
    Section createSection(NewSectionRequest newSectionRequest);
    Section updateSection(Integer sectionId, NewSectionRequest updatedSection);
    void deleteSection(Integer sectionId);
    Section getSectionById(Integer sectionId);
    List<Section> getAllSections();
    List<Section> getByCourse_Id(Integer courseId);
}
