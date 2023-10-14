package com.wanted.service;


import com.wanted.dto.CompanyDto;
import com.wanted.dto.RegistDto;
import com.wanted.dto.UpdateDto;
import com.wanted.entity.Company;
import com.wanted.entity.JobPosting;
import com.wanted.repository.CompanyRepository;
import com.wanted.repository.JobPostingRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;

@Service
@Transactional
@Slf4j
@RequiredArgsConstructor
public class JobPostingServiceImpl implements JobPostingService {

    private final CompanyRepository companyRepository;
    private final JobPostingRepository jobPostingRepository;


    @Override
    public Long registJobPosting(RegistDto registDto) throws Exception {
        //회사 번호 유효 확인
        log.info("회사 유효 상태 확인");
        System.out.println("회사 유효 상태 확인");
        Company company = companyRepository.findById(registDto.getCompanyId())
                .orElseThrow(Exception::new);

        log.info("공고 정보 생성");
        JobPosting jobPosting = JobPosting.toEntity(company,registDto);

        log.info("공고 정보 등록");
        jobPostingRepository.save(jobPosting);
        log.info("공고 정보 등록 완료");


        return jobPosting.getJobPostingId();
    }

    @Override
    public Long updateJobPosting(UpdateDto updateDto) throws Exception {

        log.info("공고 정보 유효 확인");
        JobPosting jobPosting = jobPostingRepository.findById(updateDto.getJobPostingId())
                .orElseThrow(Exception::new);

        log.info("공고 정보 수정");
        jobPosting.setPosition(updateDto.getPosition());
        jobPosting.setSkill(updateDto.getSkill());
        jobPosting.setContent(updateDto.getContent());
        jobPosting.setCompensation(updateDto.getCompensation());
        log.info("공고 정보 수정 완료");

        return jobPosting.getJobPostingId();

    }
}
