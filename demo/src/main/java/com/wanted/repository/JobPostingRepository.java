package com.wanted.repository;


import com.wanted.entity.JobPosting;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.persistence.Tuple;
import java.util.List;

@Repository
public interface JobPostingRepository extends JpaRepository<JobPosting, Long> {

    @Query("select j from JobPosting j where j.company.name like %:searchWord% or " +
            "j.position like %:searchWord%")
    List<JobPosting> findByContainingWord(String searchWord);

    @Query("select j.jobPostingId from JobPosting j where j.company.companyId = :companyId" +
            " and j.jobPostingId != :jobPostingId")
    List<Long> findByCompany_CompanyId(Long companyId, Long jobPostingId);

}
