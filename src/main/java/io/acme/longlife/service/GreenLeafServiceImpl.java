package io.acme.longlife.service;

import io.acme.longlife.exception.ResourceNotFoundException;
import io.acme.longlife.model.GreenLeaf;
import io.acme.longlife.repository.BigTreeRepository;
import io.acme.longlife.repository.GreenLeafRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class GreenLeafServiceImpl implements GreenLeafService {

    @Autowired
    GreenLeafRepository greenLeafRepository;
    @Autowired
    BigTreeRepository bigTreeRepository;

    @Override
    public GreenLeaf createGreenLeaf(Long bigTreeId, GreenLeaf greenLeaf) {
        return bigTreeRepository.findById(bigTreeId).map(bigTree -> {
            greenLeaf.setBigTree(bigTree);
            return greenLeafRepository.save(greenLeaf);
        }).orElseThrow(() -> new ResourceNotFoundException("BigTree", "Id", bigTreeId));
    }

    @Override
    public Page<GreenLeaf> getAllGreenLeafsByBigTreeId(Long bigTreeId, Pageable pageable) {
        return greenLeafRepository.findByBigTreeId(bigTreeId, pageable);
    }

    @Override
    public GreenLeaf getGreenLeafByIdAndBigTreeId(Long bigTreeId, Long greenLeafId) {
        return greenLeafRepository.findByIdAndBigTreeId(greenLeafId, bigTreeId)
                .orElseThrow(() -> new ResourceNotFoundException(
                        String.format("GreenLeaf with Id: %s and comment with Id: %s not found",
                                greenLeafId, bigTreeId)));
    }

    @Override
    public GreenLeaf updateGreenLeaf(Long bigTreeId, Long greenLeafId, GreenLeaf greenLeafDetails) {
        GreenLeaf greenLeaf = getGreenLeafByIdAndBigTreeId(bigTreeId, greenLeafId);
        //throw exception si no encuentra ese greeLeafId en ese bigTreeId
        //si lo encuentra actualiza:
        greenLeaf.setTitle(greenLeafDetails.getTitle());
        greenLeaf.setScenario(greenLeafDetails.getScenario());
        greenLeaf.setTip(greenLeafDetails.getTip());
        return greenLeafRepository.save(greenLeaf);
    }

    @Override
    public ResponseEntity<?> deleteGreenLeaf(Long bigTreeId, Long greenLeafId) {
        GreenLeaf greenLeaf = getGreenLeafByIdAndBigTreeId(bigTreeId, greenLeafId);
        //throw exception si no encuentra ese greeLeafId en ese bigTreeId
        //si lo encuentra borra:
        greenLeafRepository.delete(greenLeaf);
        return ResponseEntity.ok().build();
    }
}
