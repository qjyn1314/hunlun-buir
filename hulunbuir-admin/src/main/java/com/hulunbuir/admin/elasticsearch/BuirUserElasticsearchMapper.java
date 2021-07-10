package com.hulunbuir.admin.elasticsearch;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.annotations.Query;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

/**
 * <p>
 * explain:
 * </p>
 *
 * @author wangjunming
 * @since 2021/6/1 17:40
 */
@Repository
public interface BuirUserElasticsearchMapper extends ElasticsearchRepository<BuirUserElasticsearch, String> {


}
