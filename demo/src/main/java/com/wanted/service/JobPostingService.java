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

    void deleteJobPosting(Long jobPostingId);

    List<HashMap<String, Object>> getAllJobPostingList();
}
