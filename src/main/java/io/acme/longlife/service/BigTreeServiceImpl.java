package io.acme.longlife.service;

import io.acme.longlife.exception.ResourceNotFoundException;
import io.acme.longlife.model.BigTree;
import io.acme.longlife.repository.BigTreeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class BigTreeServiceImpl implements BigTreeService {

    @Autowired
    private BigTreeRepository bigTreeRepository;


    @Override
    public BigTree createBigTree(BigTree bigTree) {
        return bigTreeRepository.save(bigTree);
    }

    @Override
    public BigTree getBigTreeById(Long bigTreeId) {
        return bigTreeRepository.findById(bigTreeId).
                orElseThrow(() -> new ResourceNotFoundException("BigTree", "Id", bigTreeId));
    }

    @Override
    public Page<BigTree> getAllBigTrees(Pageable pageable) {
        return bigTreeRepository.findAll(pageable);
    }

    @Override
    public BigTree updateBigTree(Long bigTreeId, BigTree bigTreeDetails) {

        BigTree bigTree = getBigTreeById(bigTreeId);
        //throw exception si no encuentra bigTreeId
        //si lo encuentra actualiza:
        bigTree.setUserName(bigTreeDetails.getEmail());
        bigTree.setEmail(bigTreeDetails.getEmail());
        bigTree.setFirstName(bigTreeDetails.getFirstName());
        bigTree.setLastName(bigTreeDetails.getLastName());
        bigTree.setGender(bigTreeDetails.getGender());
        bigTree.setBornAt(bigTreeDetails.getBornAt());
        return bigTreeRepository.save(bigTree);
    }

    @Override
    public ResponseEntity<?> deleteBigTree(Long bigTreeId) {
        BigTree bigTree = getBigTreeById(bigTreeId);
        //throw exception si no encuentra bigTreeId
        //si lo encuentra borra:
        bigTreeRepository.delete(bigTree);
        return ResponseEntity.ok().build();
    }
}
