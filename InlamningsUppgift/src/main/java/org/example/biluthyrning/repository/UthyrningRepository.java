// UthyrningRepository.java
package org.example.biluthyrning.repository;

import org.example.biluthyrning.model.Uthyrning;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface UthyrningRepository extends JpaRepository<Uthyrning, Long> {
    List<Uthyrning> findByKundId(Long kundId);
    List<Uthyrning> findByBilId(Long bilId);
    List<Uthyrning> findByAktivTrue();
}