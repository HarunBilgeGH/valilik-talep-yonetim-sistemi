package com.staj.talepyonetimi.repository.memory;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.staj.talepyonetimi.model.Department;
import com.staj.talepyonetimi.repository.DepartmentRepository;
import com.staj.talepyonetimi.util.IdGenerator;

public class InMemoryDepartmentRepository implements DepartmentRepository {
    private final List<Department> departments = new ArrayList<>();
    private final IdGenerator idGenerator = new IdGenerator("D");

    @Override
    public Department save(Department department) {
        if (department == null) {
            return null;
        }

        if (department.getId() == null) {
            department.setId(idGenerator.nextId());
            this.departments.add(department);
            return department;
        }

        for (int i = 0; i < departments.size(); i++) {
            Department existingDepartment = this.departments.get(i);

            if (existingDepartment.getId() != null && existingDepartment.getId().equals(department.getId())) {
                this.departments.set(i, department);
                return department;
            }
        }

        this.departments.add(department);
        return department;
    }
    
    @Override
    public Optional<Department> findById(String id) {
        if (id == null) {
            return Optional.empty();
        }
        for (Department existingDepartment : departments) {
            if (existingDepartment.getId() != null && existingDepartment.getId().equals(id)) {
                return Optional.of(existingDepartment);}
        }
        return Optional.empty();
    }
        
    
    @Override
    public List<Department> findAll() {
        return List.copyOf(departments);
    }
    
    @Override
    public boolean existsByName(String name) {
        if (name == null) {
            return false;
        }
        for (Department department : departments) {
            if (department.getName() != null && department.getName().equalsIgnoreCase(name)) {
                return true;
            }
        }
        return false;
    }
}
