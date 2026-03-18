// BilRepository.java
package org.example.biluthyrning.repository;

import org.example.biluthyrning.model.Bil;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface BilRepository extends JpaRepository<Bil, Long> {
    List<Bil> findByTillgangligTrue();
    List<Bil> findByMarkeContainingIgnoreCase(String marke);
}