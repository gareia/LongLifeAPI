package io.acme.longlife.controller;

import io.acme.longlife.model.BigTree;
import io.acme.longlife.model.GreenLeaf;
import io.acme.longlife.resource.BigTreeResource;
import io.acme.longlife.resource.GreenLeafResource;
import io.acme.longlife.resource.SaveBigTreeResource;
import io.acme.longlife.resource.SaveGreenLeafResource;
import io.acme.longlife.service.BigTreeService;
import io.acme.longlife.service.GreenLeafService;
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

@Tag(name = "leafs", description = "Leafs API")
@RestController
@RequestMapping("/api")
public class GreenLeafController {

    @Autowired
    private ModelMapper mapper;
    @Autowired
    private GreenLeafService greenLeafService;

    @PostMapping("/trees/{treeId}/leafs")
    public GreenLeafResource createGreenLeaf(@PathVariable(name = "treeId") Long bigTreeId,
                                             @Valid @RequestBody SaveGreenLeafResource resource) {
        return convertToResource(greenLeafService.createGreenLeaf(bigTreeId,
                convertToEntity(resource)));
    }

    @GetMapping("/trees/{treeId}/leafs")
    public Page<GreenLeafResource> getAllGreenLeafsByBigTreeId(@PathVariable(name = "treeId") Long bigTreeId, Pageable pageable) {
        Page<GreenLeaf> greenLeafs = greenLeafService.getAllGreenLeafsByBigTreeId(bigTreeId, pageable);
        List<GreenLeafResource> resources = greenLeafs.getContent().stream().map(this::convertToResource).collect(Collectors.toList());
        return new PageImpl<>(resources, pageable, resources.size());
    }

    @GetMapping("/trees/{treeId}/leafs/{leafId}")
    public GreenLeafResource getGreenLeafByIdAndBigTreeId(@PathVariable(name = "treeId") Long bigTreeId,
                                                          @PathVariable(name = "leafId") Long greenLeafId) {
        return convertToResource(greenLeafService.getGreenLeafByIdAndBigTreeId(bigTreeId, greenLeafId));
    }

    @PutMapping("/trees/{treeId}/leafs/{leafId}")
    public GreenLeafResource updateGreenLeaf(@PathVariable(name = "treeId") Long bigTreeId, @PathVariable(name = "leafId") Long greenLeafId, @Valid @RequestBody SaveGreenLeafResource resource) {
        return convertToResource(greenLeafService.updateGreenLeaf(bigTreeId, greenLeafId, convertToEntity(resource)));
    }

    @DeleteMapping("/trees/{treeId}/leafs/{leafId}")
    public ResponseEntity<?> deleteGreenLeaf(@PathVariable(name = "treeId") Long bigTreeId, @PathVariable(name = "leafId") Long greenLeafId) {
        return greenLeafService.deleteGreenLeaf(bigTreeId, greenLeafId);
    }

    private GreenLeaf convertToEntity(SaveGreenLeafResource resource) {
        return mapper.map(resource, GreenLeaf.class);
    }

    private GreenLeafResource convertToResource(GreenLeaf entity) {
        return mapper.map(entity, GreenLeafResource.class);
    }
}
