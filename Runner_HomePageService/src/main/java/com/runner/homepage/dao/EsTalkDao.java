package com.runner.homepage.dao;

import com.runner.homepage.dto.EsTalkDto;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * @Description:
 * @author: 王永
 * @date: 2020/8/15  16:54
 */
public interface EsTalkDao extends ElasticsearchRepository<EsTalkDto,Integer> {
}
