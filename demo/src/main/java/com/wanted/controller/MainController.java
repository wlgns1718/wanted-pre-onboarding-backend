package com.wanted.controller;


import com.wanted.dto.RegistDto;
import com.wanted.service.JobPostingService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

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
            log.info("미션 등록 서비스 호출");
            Long jobPostingId = jobPostingService.registJobPosting(registDto);
            result.put("msg","정보 등록 완료");
            result.put("PostingId",String.valueOf(jobPostingId));
            return result;
        }catch (Exception e){
            e.getMessage();
            result.put("msg","내부 서버 오류 발생");
            result.put("error",e.toString());
            return result;
        }

    }

}
