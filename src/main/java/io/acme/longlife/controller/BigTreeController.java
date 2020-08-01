package io.acme.longlife.controller;

import io.acme.longlife.model.BigTree;
import io.acme.longlife.resource.BigTreeResource;
import io.acme.longlife.resource.SaveBigTreeResource;
import io.acme.longlife.service.BigTreeService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@Tag(name = "trees", description = "Trees API")
@RestController
@RequestMapping("/api")
public class BigTreeController {

    @Autowired
    private ModelMapper mapper;
    @Autowired
    private BigTreeService bigTreeService;

    @PostMapping("/trees")
    public BigTreeResource createBigTree(@Valid @RequestBody SaveBigTreeResource resource) {
        BigTree bigTree = convertToEntity(resource);
        return convertToResource(bigTreeService.createBigTree(bigTree));

    }

    @GetMapping("/trees/{treeId}")
    public BigTreeResource getBigTreeById(@PathVariable(name = "treeId") Long bigTreeId) {
        return convertToResource(bigTreeService.getBigTreeById(bigTreeId));
    }

    @GetMapping("/trees")
    public Page<BigTreeResource> getAllBigTrees(Pageable pageable) {
        Page<BigTree> bigTrees = bigTreeService.getAllBigTrees(pageable);
        List<BigTreeResource> resources = bigTrees.getContent().stream().map(this::convertToResource).collect(Collectors.toList());

        return new PageImpl<>(resources, pageable, resources.size());
    }

    @PutMapping("/trees/{treeId}")
    public BigTreeResource updateBigTree(@PathVariable(name = "treeId") Long bigTreeId, @Valid @RequestBody SaveBigTreeResource resource) {
        BigTree bigTree = convertToEntity(resource);
        return convertToResource(bigTreeService.updateBigTree(bigTreeId, bigTree));
    }

    @DeleteMapping("/trees/{treeId}")
    public ResponseEntity<?> deleteBigTree(@PathVariable(name = "treeId") Long bigTreeId) {
        return bigTreeService.deleteBigTree(bigTreeId);
    }

    private BigTree convertToEntity(SaveBigTreeResource resource) {
        return mapper.map(resource, BigTree.class);
    }

    private BigTreeResource convertToResource(BigTree entity) {
        return mapper.map(entity, BigTreeResource.class);
    }

}



