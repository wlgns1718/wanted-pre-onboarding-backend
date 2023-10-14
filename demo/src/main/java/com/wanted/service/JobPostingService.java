package com.wanted.service;


import com.wanted.dto.RegistDto;
import com.wanted.dto.UpdateDto;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

@Service
public interface JobPostingService {

    Long registJobPosting(RegistDto registDto) throws Exception;

    Long updateJobPosting(UpdateDto updateDto) throws Exception;

    void deleteJobPosting(Long jobPostingId) throws Exception;

    List<HashMap<String, Object>> getAllJobPostingList()throws Exception;

    List<HashMap<String, Object>> getSearchJobPostings(String searchWord)throws Exception;

    HashMap<String, Object> getDetail(Long jobPostingId) throws Exception;
}
