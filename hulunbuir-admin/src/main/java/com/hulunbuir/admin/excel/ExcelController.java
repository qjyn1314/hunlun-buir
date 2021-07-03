package com.hulunbuir.admin.excel;

import io.swagger.annotations.ApiOperation;
import lombok.Data;
import lombok.experimental.Accessors;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * explain:
 * 使用easyexcel 进行导入导出
 *
 * 官方文档：
 *  https://www.yuque.com/easyexcel/doc/easyexcel
 *
 *
 *
 * </p>
 *
 * @author wangjunming
 * @since 2021/6/4 11:23
 */
@Slf4j
@RestController
@RequestMapping("/excel")
public class ExcelController {

    /**
     * 使用easyexcel导入数据,导出数据
     *
     * @author wangjunming
     * @since 2021/6/4 12:51
     */
    @ApiOperation("导入excel")
    @PostMapping("/import/excel/001")
    public void importExcel001() {
        String fileName = "F:\\excel_dev\\import_dev_excel.xlsx";
        File file = new File(fileName);

    }

    public void importExcel002(MultipartFile files) {
        String fileName = "F:\\excel_dev\\import_dev_excel.xlsx";
        File file = new File(fileName);

    }

    public static void main(String[] args) {
        ExcelController excelController = new ExcelController();
        excelController.importExcel001();
    }


    public void test01() {
        List<StatisticsCenter> list = new ArrayList<>();
        // 1、收集出 第一集的 数据
        List<StatisticsCenter> collect = list.stream()
                .filter((c) -> c.getParentNum() == 0)
                // 与map功能一致，对list中每一项循环转化，封装childrenList
                .peek((c) -> c.setChildren(getChildrenList(c.getNum(), list)))
                //排序操作
                .sorted(Comparator.comparingInt(StatisticsCenter::getTotal))
                .collect(Collectors.toList());
        collect.forEach(System.out::println);
    }

    //2、获取子节点集合
    public List<StatisticsCenter> getChildrenList(Integer pNum, List<StatisticsCenter> old) {
        return old.stream()
                .filter((c) -> pNum.equals(c.getParentNum()))
                .peek((c) -> c.setChildren(getChildrenList(c.getNum(), old)))
                .sorted(Comparator.comparingInt(StatisticsCenter::getTotal))
                .collect(Collectors.toList());
    }

    @Data
    @Accessors(chain = true)
    static class StatisticsCenter implements Serializable {
        private static final long serialVersionUID = 1L;
        private Integer id;
        private Integer num;
        private String title;
        private Integer parentNum;
        private Integer total;
        private List<StatisticsCenter> children;
    }

}
