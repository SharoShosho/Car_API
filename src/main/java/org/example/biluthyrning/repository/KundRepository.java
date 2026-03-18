// KundRepository.java
package org.example.biluthyrning.repository;

import org.example.biluthyrning.model.Kund;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface KundRepository extends JpaRepository<Kund, Long> {
    Optional<Kund> findByEpost(String epost);
    boolean existsByEpost(String epost);
}