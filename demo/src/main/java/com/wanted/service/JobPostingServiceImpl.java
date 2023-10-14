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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

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

    @Override
    public void deleteJobPosting(Long jobPostingId) {

        log.info("공고 정보 삭제");
        jobPostingRepository.deleteById(jobPostingId);
        log.info("공고 정보 삭제 완료");
    }

    @Override
    public List<HashMap<String, Object>> getAllJobPostingList() {

        List<JobPosting> jobPostings = jobPostingRepository.findAll();
        List<HashMap<String, Object>> list = new ArrayList<>();
        log.info("전체 공고 불러오기...");
        for(JobPosting jobPosting : jobPostings){
            Company company = jobPosting.getCompany();
            HashMap<String,Object> map = new HashMap<>();
            map.put("채용공고_id", jobPosting.getJobPostingId());
            map.put("회사명", company.getName());
            map.put("국가",company.getNation());
            map.put("지역",company.getRegion());
            map.put("채용포지션", jobPosting.getPosition());
            map.put("채용보상금", jobPosting.getCompensation());
            map.put("사용기술", jobPosting.getSkill());
            list.add(map);
        }
        log.info("공고 불러오기 성공");
        return list;
    }
}
