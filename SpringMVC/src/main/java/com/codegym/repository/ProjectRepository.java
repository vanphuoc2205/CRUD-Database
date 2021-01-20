package com.codegym.repository;

import com.codegym.model.Project;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface ProjectRepository extends PagingAndSortingRepository<Project,Long> {
}
