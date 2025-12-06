package com.gerenciamento.domain.repository;

import com.gerenciamento.domain.entity.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProjectRepository extends JpaRepository<Project, String> {

    Optional<Project> findByName(String name);
}
