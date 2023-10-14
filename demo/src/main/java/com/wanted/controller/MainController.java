package com.wanted.controller;


import com.wanted.dto.RegistDto;
import com.wanted.dto.UpdateDto;
import com.wanted.service.JobPostingService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/wanted")
@CrossOrigin("*")
@RequiredArgsConstructor
@Slf4j
public class MainController {

    private final JobPostingService jobPostingService;

    @PostMapping(value = "")
    public HashMap<String, String> regist(@RequestBody RegistDto registDto){
        HashMap<String,String> result = new HashMap<>();

        try{
            log.info(registDto.toString());
            log.info("공고 등록 서비스 호출");
            Long jobPostingId = jobPostingService.registJobPosting(registDto);
            result.put("msg","공고 등록 완료");
            result.put("JobPostingId",String.valueOf(jobPostingId));
            return result;
        }catch (Exception e){
            e.getMessage();
            result.put("msg","공고 등록 실패");
            result.put("error",e.toString());
            return result;
        }

    }

    @PutMapping(value = "")
    public HashMap<String,String> update(@RequestBody UpdateDto updateDto){
        HashMap<String, String> result = new HashMap<>();

        log.info(updateDto.toString());
        log.info("공고 수정 서비스 호출");
        try{
            Long jobPostingId = jobPostingService.updateJobPosting(updateDto);
            result.put("msg","공고 정보 수정 완료");
            result.put("JobPostingId",String.valueOf(jobPostingId));
            return result;
        }catch (Exception e){
            e.getMessage();
            result.put("msg","공고 정보 수정 실패");
            result.put("error",e.toString());
            return result;
        }

    }

    @DeleteMapping(value = "/{jobpostingid}")
    public HashMap<String,String> delete(@PathVariable("jobpostingid") Long jobPostingId){
        HashMap<String,String> result = new HashMap<>();

        log.info(String.valueOf(jobPostingId));
        log.info("공고 정보 삭제 서비스 호출");
        try{
            jobPostingService.deleteJobPosting(jobPostingId);
            result.put("msg","공고 정보 삭제 완료");
            return result;
        }catch (Exception e){
            e.getMessage();
            result.put("msg","공고 정보 삭제 실패");
            result.put("error",e.toString());
            return result;
        }
    }

    @GetMapping(value = "")
    public HashMap<String,Object> getJobPosting(){
        HashMap<String,Object> result = new HashMap<>();

        log.info("공고 정보 불러오기");

        try {
            List<HashMap<String, Object>> jobPostingList = jobPostingService.getAllJobPostingList();
            result.put("msg","공고 불러오기 성공");
            result.put("data",jobPostingList);
            return result;
        }catch (Exception e){
            e.getMessage();
            result.put("msg","공고 불러오기 실패");
            result.put("error",e.toString());
            return result;
        }

    }

}
