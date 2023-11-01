package com.osvaldo.adsdungeons.repositories;
import com.osvaldo.adsdungeons.domain.Minion;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.UUID;

public interface MinionRepository extends JpaRepository<Minion, UUID> { }
