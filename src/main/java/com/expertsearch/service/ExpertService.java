package com.expertsearch.service;

import com.expertsearch.entity.Expert;
import com.expertsearch.mapper.ExpertMapper;
import com.expertsearch.model.Constants;
import com.expertsearch.model.ExpertDTO;
import com.expertsearch.model.SortMode;
import com.expertsearch.repository.ExpertRepository;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ExpertService {

    private final ExpertRepository repository;

    public void create(ExpertDTO expertDTO) {
        Expert expert = ExpertMapper.expertDTOToExpert(expertDTO);
        repository.save(expert);
    }

    public List<ExpertDTO> findAll() {
        List<Expert> expertList = repository.findAll();
        return expertList != null
            ? mapToExpertDTOList(expertList)
            : Collections.emptyList();
    }

    public List<ExpertDTO> findByName(String name) {
        List<Expert> expertsByName = repository.findByName(name.toLowerCase());
        return mapToExpertDTOList(expertsByName);
    }

    public List<ExpertDTO> findByDescriptionContaining(String desciption) {
        List<Expert> expertsByName = repository.findByDescriptionContaining(desciption.toLowerCase());
        return mapToExpertDTOList(expertsByName);
    }

    public List<ExpertDTO> getExpertByLanguage(String language, SortMode sortMode, String direction) {
        return language != null ? findByLanguage(language.toLowerCase(), sortMode, direction) : Collections.emptyList();
    }

    private List<ExpertDTO> findByLanguage(String language, SortMode sortMode, String direction) {
        Sort sortBy = getSortingOrder(sortMode, direction);
        List<Expert> expertsByName = repository.findByLanguage(language.toLowerCase(), sortBy);
        return mapToExpertDTOList(expertsByName);
    }

    private Sort getSortingOrder(SortMode sortMode, String direction) {
        Sort sortBy = sortMode.getSortBy();
        if (direction.equalsIgnoreCase(Constants.DIRECTION_ASC)) {
            sortBy.ascending();
            return sortBy;
        }
        sortBy.descending();
        return sortBy;
    }

    private List<ExpertDTO> mapToExpertDTOList(List<Expert> expertsByName) {
        return expertsByName.stream()
            .map(ExpertMapper::expertToExpertDTO)
            .collect(Collectors.toList());
    }
}
