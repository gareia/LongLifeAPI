package io.acme.longlife.repository;

import io.acme.longlife.model.BigTree;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BigTreeRepository extends JpaRepository<BigTree, Long> {
}
