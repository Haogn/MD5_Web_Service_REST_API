package com.ra.repository;

import com.ra.entity.ImageData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface StorageRepository extends JpaRepository<ImageData, Long> {


}
