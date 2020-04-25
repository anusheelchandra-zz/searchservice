package com.expertsearch.controller;

import com.expertsearch.model.Constants;
import com.expertsearch.model.ExpertDTO;
import com.expertsearch.model.SortMode;
import com.expertsearch.service.ExpertService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import java.util.Collections;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Api(value="Expert Controller", description="Operations related to Expert Search API")
public class ExpertController {

    private final ExpertService expertService;

    @PostMapping(Constants.CREATE_PATH)
    @ResponseStatus(HttpStatus.CREATED)
    @ApiOperation(value = "Expert creation")
    @ApiResponses(value = {@ApiResponse(code = 201, message = "CREATED")})
    public void create(@RequestBody ExpertDTO expertDTO) {
        expertService.create(expertDTO);
    }

    @GetMapping(Constants.FIND_ALL_PATH)
    @ApiOperation(value = "Get All Experts", response = List.class)
    public List<ExpertDTO> findAll() {
        return expertService.findAll();
    }

    @GetMapping(Constants.FIND_BY_NAME_PATH)
    @ApiOperation(value = "Get Expert By Name", response = List.class)
    public List<ExpertDTO> findByName(@RequestParam(required = false) String name) {
        return (name != null && !name.equals(Constants.EMPTY_STRING))
               ? expertService.findByName(name)
               : Collections.emptyList();
    }

    @GetMapping(Constants.FIND_BY_DESC_PATH)
    @ApiOperation(value = "Get Expert By Description", response = List.class)
    public List<ExpertDTO> findByDescription(@RequestParam(required = false) String description) {
        return (description != null && !description.equals(Constants.EMPTY_STRING))
               ? expertService.findByDescriptionContaining(description)
               : Collections.emptyList();
    }

    @GetMapping(Constants.SEARCH_BY_OPTIONS_PATH)
    @ApiOperation(value = "Search Expert By Options", response = List.class)
    public List<ExpertDTO> search(@RequestParam(required = false) String language,
                                  @RequestParam(value = Constants.SORT_BY,
                                                defaultValue = Constants.SORT_BY_DEFAULT,
                                                required = false) SortMode sortMode,
                                  @RequestParam(defaultValue = Constants.DIRECTION_ASC,
                                                required = false) String direction) {
        return expertService.getExpertByLanguage(language, sortMode, direction);
    }

}
