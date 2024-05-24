package org.example.labgticsz.repository;

import org.example.labgticsz.entity.Mesa;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MesaRepository extends JpaRepository<Mesa, Integer> {
    List<Character> findByNameIgnoreCaseContaining(String srch);
}
