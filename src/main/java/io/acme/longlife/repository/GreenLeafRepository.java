package io.acme.longlife.repository;

import io.acme.longlife.model.BigTree;
import io.acme.longlife.model.GreenLeaf;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface GreenLeafRepository extends JpaRepository<GreenLeaf, Long> {
    Page<GreenLeaf> findByBigTreeId(Long bigTreeId, Pageable pageable);

    Optional<GreenLeaf> findByIdAndBigTreeId(Long id, Long bigTreeId);
}
