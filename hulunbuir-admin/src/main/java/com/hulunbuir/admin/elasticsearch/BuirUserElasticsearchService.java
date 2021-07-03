package com.hulunbuir.admin.elasticsearch;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.index.query.QueryBuilders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
import org.springframework.data.elasticsearch.core.SearchHit;
import org.springframework.data.elasticsearch.core.SearchHits;
import org.springframework.data.elasticsearch.core.query.MoreLikeThisQuery;
import org.springframework.data.elasticsearch.core.query.NativeSearchQuery;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.stereotype.Service;

import java.util.Iterator;
import java.util.List;
import java.util.stream.Stream;

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

    public void saveUser(BuirUserElasticsearch userElasticsearch) {
        BuirUserElasticsearch buirUserElasticsearch = userElasticsearchMapper.save(userElasticsearch);
        log.info("保存成功的-buirUserElasticsearch-{}",buirUserElasticsearch);
    }

    public void delUser(BuirUserElasticsearch userElasticsearch) {
        userElasticsearchMapper.deleteById(userElasticsearch.getId());
    }

    public BuirUserElasticsearch updateUser(BuirUserElasticsearch userElasticsearch) {
        return userElasticsearchMapper.save(userElasticsearch);
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
//        String name = buirUser.getName();
        //构建分页
        Pageable pageable= PageRequest.of(0,10);
//        String[] fileds = {""};
//        Page<BuirUserElasticsearch> searchSimilar = userElasticsearchMapper.findByName(buirUser, pageable);
        Page<BuirUserElasticsearch> all = userElasticsearchMapper.findAll(pageable);
//        Page<BuirUserElasticsearch> elasticsearchPage = userElasticsearchMapper.searchSimilar(buirUser, fileds, pageable);
        log.info("查询出来的es数据是_{}", JSON.toJSONString(all));
//        SearchHits<BuirUserElasticsearch> search = elasticsearchRestTemplate.search(query, BuirUserElasticsearch.class);
//        Stream<SearchHit<BuirUserElasticsearch>> searchHitStream = search.get();
//        Iterator<SearchHit<BuirUserElasticsearch>> iterator = search.iterator();
//        List<SearchHit<BuirUserElasticsearch>> searchHits = search.getSearchHits();
//        for (SearchHit<BuirUserElasticsearch> searchHit : searchHits) {
//            BuirUserElasticsearch content = searchHit.getContent();
//            log.info("查询出来的es数据是：{}", JSON.toJSONString(content));
//        }
        return all;
    }

}
