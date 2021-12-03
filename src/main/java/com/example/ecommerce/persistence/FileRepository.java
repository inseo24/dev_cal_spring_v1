package com.example.ecommerce.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.ecommerce.model.FileEntity;

@Repository
public interface FileRepository extends JpaRepository<FileEntity, Long>{

}
