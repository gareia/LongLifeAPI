package io.acme.longlife.service;

import io.acme.longlife.model.BigTree;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

public interface BigTreeService {

    //CRUD
    BigTree createBigTree(BigTree bigTree);

    BigTree getBigTreeById(Long bigTreeId);

    Page<BigTree> getAllBigTrees(Pageable pageable);

    BigTree updateBigTree(Long bigTreeId, BigTree bigTreeDetails);

    ResponseEntity<?> deleteBigTree(Long bigTreeId);
}
