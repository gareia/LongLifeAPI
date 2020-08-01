package io.acme.longlife.service;

import io.acme.longlife.model.GreenLeaf;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

public interface GreenLeafService {

    //CRUD
    GreenLeaf createGreenLeaf(Long bigTreeId, GreenLeaf greenLeaf);

    Page<GreenLeaf> getAllGreenLeafsByBigTreeId(Long bigTreeId, Pageable pageable);

    GreenLeaf getGreenLeafByIdAndBigTreeId(Long bigTreeId, Long greenLeafId);

    GreenLeaf updateGreenLeaf(Long bigTreeId, Long greenLeafId, GreenLeaf greenLeafDetails);

    ResponseEntity<?> deleteGreenLeaf(Long bigTreeId, Long greenLeafId);
}
