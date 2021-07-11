package com.hulunbuir.admin;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.hulunbuir.admin.design.factory_strategy.message.Message;
import com.hulunbuir.admin.design.factory_strategy.message.MessageFactory;
import com.hulunbuir.admin.design.proxy.FoodService;
import com.hulunbuir.admin.elasticsearch.BuirUserElasticsearch;
import com.hulunbuir.admin.elasticsearch.BuirUserElasticsearchService;
import com.hulunbuir.admin.mongodb.BuirUser;
import com.hulunbuir.admin.mongodb.BuirUserService;
import com.hulunbuir.admin.persistence.entity.EcsGoods;
import com.hulunbuir.admin.persistence.service.EcsGoodsService;
import com.hulunbuir.admin.worktest.SupplierPo;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.redis.core.ListOperations;

import java.util.ArrayList;
import java.util.List;
@Slf4j
@SpringBootTest(classes = AdminApplication.class)
class HulunbuirAdminApplicationTests {

    @Autowired
    private Message message;

    @Autowired
    private MessageFactory messageFactoryService;
    @Autowired
    private MessageFactory factoryService;
    @Autowired
    private EcsGoodsService ecsGoodsService;

    @Autowired
    @Qualifier("foodServiceProxy")
    private FoodService foodService;

    //测试工厂模式实践示例
    @Test
    void contextLoadss() {
        log.info("contextLoadss");
    }

    //测试代理模式实践示例
    @Test
    void contextLoadsProxy() {
        foodService.scrambledEggWithTomato();
    }
    @Autowired
    private ListOperations<String, Object> listOperations;
    @Test
    public void getAppKey(){
         List<SupplierPo> supplierPos = new ArrayList<>();
        SupplierPo supplierPo = new SupplierPo("15321355715","A0001","username001");
        supplierPos.add(supplierPo);
        SupplierPo supplierPo1 = new SupplierPo("15321355715","A0001","username001");
        supplierPos.add(supplierPo1);
        supplierPos.forEach( supplier ->{
            listOperations.rightPush(supplier.getPhone(),supplier);
            System.out.println(supplier.getPhone());
        });
        supplierPos.forEach( supplier ->{
            final Object leftPop = listOperations.rightPop(supplier.getPhone());
            System.out.println(leftPop);
        });
    }

    //---------------测试 mongodb 的分页查询以及保存
    @Autowired
    private BuirUserService buirUserService;

    @Test
    public void saveUserByMongodb(){
        for (int i = 0; i < 100; i++) {
            BuirUser buirUser = new BuirUser();
            buirUser.setName("mongodb_name"+i);
            buirUser.setPassword("mongodb_password"+i);
            buirUser.setStatus(i);
            buirUserService.saveUser(buirUser);
        }
    }

    @Test
    public void pageUserByMongodb(){
        BuirUser buirUser = new BuirUser();
        buirUser.setName("mongodb_name1");
        buirUser.setStatus(10);
        Integer pageSize = 0;
        Integer pageNo = 10;
        Page<BuirUser> buirUserPage = buirUserService.selectPageUser(buirUser, pageSize, pageNo);
        log.info("buirUserPage:{}", JSONObject.toJSONString(buirUserPage));
    }

    //---------------测试 mongodb 的分页查询以及保存

    //---------------测试 Elasticsearch  的分页查询以及保存
    @Autowired
    private BuirUserElasticsearchService userElasticsearchService;
    /**
     * 保存到elas数据库
     *
     * @author wangjunming
     * @since 2021/7/4 10:36
     */
    @Test
    public void saveUserByElasticsearch(){
        for (int i = 0; i < 100; i++) {
            BuirUserElasticsearch buirUser = new BuirUserElasticsearch();
            buirUser.setId(String.valueOf(i));
            buirUser.setName("Elasticsearch_name"+i);
            buirUser.setPassword("Elasticsearch_password"+i);
            buirUser.setStatus(i);
            userElasticsearchService.saveUser(buirUser);
        }
    }
    /**
     * 删除到elas数据库
     *
     * @author wangjunming
     * @since 2021/7/4 10:36
     */
    @Test
    public void delUserByElasticsearch(){
        Iterable<BuirUserElasticsearch> list = userElasticsearchService.selectAllList();
        for (BuirUserElasticsearch userElasticsearch : list) {
            log.info("删除的数据是{}",userElasticsearch);
            userElasticsearchService.delUser(userElasticsearch);
        }
    }

    /***
     * 只有分页
     *
     * @author wangjunming
     * @since 2021/7/4 10:35
     */
    @Test
    public void pageTestUserByElasticsearch() {
        BuirUserElasticsearch buirUser = new BuirUserElasticsearch();
        Integer pageSize = 0;
        Integer pageNo = 10;
        Page<BuirUserElasticsearch> buirUserPage = userElasticsearchService.selectPageUser(buirUser, pageSize, pageNo);
        log.info("buirUserPage:{}", JSONObject.toJSONString(buirUserPage));
    }

    /**
     * 分页加上模糊搜索
     *
     * @author wangjunming
     * @since 2021/7/4 10:35
     */
    @Test
    public void pageAndBlurryTestUserByElasticsearch() {
        BuirUserElasticsearch buirUser = new BuirUserElasticsearch();
//        buirUser.setName("Elasticsearch_name1");
        buirUser.setSearchKey("Elasticsearch_name*");
        Integer pageSize = 0;
        Integer pageNo = 10;
        Page<BuirUserElasticsearch> buirUserPage = userElasticsearchService.selectPageUserBlurry(buirUser, pageSize, pageNo);
        log.info("buirUserPage:{}", JSONObject.toJSONString(buirUserPage));
    }
    //---------------测试 Elasticsearch  的分页查询以及保存



    /**测试MySQL的悲观锁*/
    @Test
    public void pessimisticLock(){
        Long goodId = 60L;
        String goodsSn = "ECS000060";
        String goodsName = "指环式防滑手机支架001";
        goodId = ecsGoodsService.selectIdByForUpdate(goodId);
        log.info("商品ID是_{}",goodId);
        EcsGoods ecsGoods = new EcsGoods();
        ecsGoods.setGoodsId(goodId.intValue());
        ecsGoods.setGoodsName(goodsName);
        boolean flag = ecsGoodsService.updateGoodsById(ecsGoods);
        ecsGoods = ecsGoodsService.selectByIdAndCode(goodId, goodsSn);
        log.info("商品信息是_{}", JSON.toJSONString(ecsGoods));
    }






}
