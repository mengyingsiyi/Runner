package com.runner.planapi.service;

import com.runner.commons.dto.PlanInfoDto;
import com.runner.commons.vo.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @program: runner
 * @description:
 * @author: money
 * @create: 2020-08-22 09:50
 */
@FeignClient("runnerplanservice")
public interface PlanService {

    @PostMapping(value = "api/plan/saveplan.do",consumes = MediaType.APPLICATION_JSON_VALUE)
    R savePlan(@RequestBody PlanInfoDto planInfoDto, @RequestParam String token);

    @GetMapping(value = "api/plan/findplan.do",consumes = MediaType.APPLICATION_JSON_VALUE)
    R showPlan(@RequestParam Integer planInfoId,@RequestParam String token);
}
