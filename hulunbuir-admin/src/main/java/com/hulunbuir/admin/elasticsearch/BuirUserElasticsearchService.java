package com.hulunbuir.admin.elasticsearch;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.elasticsearch.core.*;
import org.springframework.data.elasticsearch.core.query.NativeSearchQuery;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static org.elasticsearch.index.query.QueryBuilders.*;
import static org.elasticsearch.index.query.QueryBuilders.wildcardQuery;

/**
 * <p>
 * explain:
 * </p>
 *
 * @author wangjunming
 * @since 2021/6/1 17:39
 */
@Slf4j
@Service
public class BuirUserElasticsearchService {

    @Autowired
    private BuirUserElasticsearchMapper userElasticsearchMapper;
    @Autowired
    private ElasticsearchRestTemplate elasticsearchRestTemplate;

    public void saveUser(BuirUserElasticsearch userElasticsearch) {
        BuirUserElasticsearch buirUserElasticsearch = userElasticsearchMapper.save(userElasticsearch);
        log.info("保存成功的-buirUserElasticsearch-{}", buirUserElasticsearch);
    }

    public void delUser(BuirUserElasticsearch userElasticsearch) {
        userElasticsearchMapper.deleteById(userElasticsearch.getId());
    }

    public BuirUserElasticsearch updateUser(BuirUserElasticsearch userElasticsearch) {
        return userElasticsearchMapper.save(userElasticsearch);
    }
    public Iterable<BuirUserElasticsearch> selectAllList() {
        return userElasticsearchMapper.findAll();
    }
    /**
     * 分页查询参考：
     * https://blog.csdn.net/weixin_45566249/article/details/111297868a
     *
     * @param buirUser 查询传参
     * @param pageSize 当前页
     * @param pageNo   每页显示记录数
     * @author wangjunming
     * @since 2021/6/2 16:17
     */
    public Page<BuirUserElasticsearch> selectPageUser(BuirUserElasticsearch buirUser, Integer pageSize, Integer pageNo) {
        return userElasticsearchMapper.findAll(PageRequest.of(pageSize, pageNo));
    }

    /**
     * 分页查询参考：
     *
     * https://juejin.cn/post/6884851582983208967
     *
     * @param buirUser 查询传参
     * @param pageSize 当前页
     * @param pageNo   每页显示记录数
     * @author wangjunming
     * @since 2021/6/2 16:17
     */
    public Page<BuirUserElasticsearch> selectPageUserBlurry(BuirUserElasticsearch buirUser, Integer pageSize, Integer pageNo) {
        PageRequest pageable = PageRequest.of(pageSize, pageNo);
        BoolQueryBuilder queryBuilder = QueryBuilders.boolQuery();
        String name = buirUser.getName();
        if (StringUtils.isNotBlank(name)) {
            //精确查找
            queryBuilder.filter(termQuery("name", name));
        }
        String searchKey = buirUser.getSearchKey();
        if (StringUtils.isNotBlank(searchKey)) {
            //模糊搜索
            queryBuilder.must(QueryBuilders.matchQuery("name", searchKey));
        }
        //搜索条件
        NativeSearchQuery searchQuery = new NativeSearchQueryBuilder()
                .withQuery(queryBuilder)
                .withPageable(pageable)
                .build();

        SearchHits<BuirUserElasticsearch> searchHits = elasticsearchRestTemplate.search(searchQuery, BuirUserElasticsearch.class);
        List<BuirUserElasticsearch> userList = searchHits.getSearchHits().stream().map(SearchHit::getContent).collect(Collectors.toList());
        long totalHits = searchHits.getTotalHits();
        return new PageImpl<>(userList,pageable,totalHits);
    }

}
