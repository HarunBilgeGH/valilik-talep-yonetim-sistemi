package com.staj.talepyonetimi.repository;

import java.util.List;
import java.util.Optional;

import com.staj.talepyonetimi.model.Department;

public interface DepartmentRepository {
    Department save(Department department);

    Optional<Department> findById(Long id);

    List<Department> findAll();

    boolean existsByName(String name);
}
