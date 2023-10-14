package com.wanted.service;

import com.wanted.dto.ApplyDto;
import com.wanted.entity.Apply;
import com.wanted.entity.JobPosting;
import com.wanted.entity.User;
import com.wanted.repository.ApplyRepository;
import com.wanted.repository.JobPostingRepository;
import com.wanted.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class ApplyServiceImpl implements ApplyService{

    private final UserRepository userRepository;
    private final JobPostingRepository jobPostingRepository;
    private final ApplyRepository applyRepository;

    @Override
    public void applyJobPosting(ApplyDto applyDto) throws Exception {

        Apply apply = applyRepository.findByUser_UserIdAndJobPosting_JobPostingId
                (applyDto.getUserId(), applyDto.getJobPostingId());
        if(apply != null){
            throw new Exception("이미 지원한 공고입니다.");
        }
        User user = userRepository.findById(applyDto.getUserId())
                .orElseThrow(Exception::new);
        JobPosting jobPosting = jobPostingRepository.findById(applyDto.getJobPostingId())
                .orElseThrow(Exception::new);
        applyRepository.save(Apply.toEntity(user,jobPosting));
    }
}
