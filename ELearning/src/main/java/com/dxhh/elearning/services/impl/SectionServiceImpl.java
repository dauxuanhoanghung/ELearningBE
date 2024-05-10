package com.dxhh.elearning.services.impl;

import com.dxhh.elearning.dto.request.NewSectionRequest;
import com.dxhh.elearning.dto.response.SectionResponse;
import com.dxhh.elearning.pojos.Course;
import com.dxhh.elearning.pojos.Section;
import com.dxhh.elearning.repositories.SectionRepository;
import com.dxhh.elearning.services.SectionService;
import com.dxhh.elearning.specifications.GSpecification;
import com.dxhh.elearning.specifications.SearchCriteria;
import com.dxhh.elearning.specifications.SearchOperation;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class SectionServiceImpl implements SectionService {

    private final SectionRepository sectionRepository;

    @Autowired
    public SectionServiceImpl(SectionRepository sectionRepository) {
        this.sectionRepository = sectionRepository;
    }

    @Override
    public Section createSection(NewSectionRequest newSectionRequest) {
        Section section = new Section();
        section.setName(newSectionRequest.getName());
        section.setOrderIndex(sectionRepository.countByCourseId(newSectionRequest.getCourse().getId()) + 1);
        section.setCourse(newSectionRequest.getCourse());
        return sectionRepository.save(section);
    }

    @Override
    public Section updateSection(Integer sectionId, NewSectionRequest updatedSection) {
        Section section = sectionRepository.findById(sectionId)
                .orElseThrow(() -> new EntityNotFoundException("Section not found"));

        section.setName(updatedSection.getName());
        section.setOrderIndex(updatedSection.getOrderIndex());
        section.setCourse(updatedSection.getCourse());
        return sectionRepository.save(section);
    }

    @Override
    public void deleteSection(Integer sectionId) {
        sectionRepository.deleteById(sectionId);
    }

    @Override
    public Section getSectionById(Integer sectionId) {
        return sectionRepository.findById(sectionId)
                .orElseThrow(() -> new EntityNotFoundException("Section not found"));
    }

    @Override
    public List<Section> getAllSections() {
        return sectionRepository.findAll();
    }

    @Override
    public List<Section> getByCourse_Id(Integer courseId) {
        return sectionRepository.findAll(new GSpecification<>(new SearchCriteria("course.id", SearchOperation.EQUAL, courseId)));
    }

    @Override
    public List<SectionResponse> getSectionsAndLecturesByCourseId(Integer courseId) {
        List<Section> sections = sectionRepository.findByCourseId(courseId);
        return sections.stream()
                .map(SectionResponse::new)
                .collect(Collectors.toList());
    }
}
