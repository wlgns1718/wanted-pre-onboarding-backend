package com.wanted.repository;


import com.wanted.entity.Apply;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ApplyRepository extends JpaRepository<Apply,Long> {


    Apply findByUser_UserIdAndJobPosting_JobPostingId(Long userid, Long jobPostingId);

}
