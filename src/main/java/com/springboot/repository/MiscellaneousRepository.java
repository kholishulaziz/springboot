package com.springboot.repository;

import com.springboot.model.Miscellaneous;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MiscellaneousRepository extends JpaRepository<Miscellaneous, Integer> {}
