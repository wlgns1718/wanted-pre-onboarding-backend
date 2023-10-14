package com.wanted.service;


import com.wanted.dto.RegistDto;
import org.springframework.stereotype.Service;

@Service
public interface JobPostingService {

    Long registJobPosting(RegistDto registDto) throws Exception;

}
