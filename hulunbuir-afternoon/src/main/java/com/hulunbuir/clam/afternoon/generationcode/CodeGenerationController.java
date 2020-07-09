package com.hulunbuir.clam.afternoon.generationcode;

import com.hulunbuir.clam.afternoon.generationcode.entity.CodeGeneration;
import com.hulunbuir.clam.afternoon.generationcode.service.CodeGenerationService;
import com.hulunbuir.clam.common.base.BaseController;
import com.hulunbuir.clam.common.base.QueryRequest;
import com.hulunbuir.clam.common.config.ApplicationContextUtils;
import com.hulunbuir.clam.parent.result.JsonResult;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * explain: 用于生成代码的控制层
 * 思路，将生成代码的配置存放到redis中，key的生成根据不同的模板来进行生成
 * </p>
 *
 * @author wangjunming
 * @since 2020/7/1 14:17
 */
@Slf4j
@RestController
@RequestMapping("/generation")
public class CodeGenerationController extends BaseController {

    @Autowired
    private CodeGenerationService codeGenerationService;

    @ApiOperation("保存配置")
    @PostMapping("/saveGeneration")
    public JsonResult saveGeneration(CodeGenerationConfig generationConfig){
        generationConfig.setSessionId(getSessionId());
        log.info("生成代码配置是：{}",generationConfig);
        return JsonResult.success(codeGenerationService.saveGeneration(generationConfig));
    }

    @ApiOperation("获取配置")
    @GetMapping("/getGeneration")
    public JsonResult getGeneration(){
        CodeGenerationConfig codeGenerationConfig = new CodeGenerationConfig(getSessionId());
        return JsonResult.success(codeGenerationService.getGeneration(codeGenerationConfig));
    }

    @ApiOperation("数据库列表")
    @GetMapping("/databases")
    public JsonResult databases(){
        return JsonResult.success(codeGenerationService.databases());
    }

    @ApiOperation("数据库中的表")
    @GetMapping("/tables")
    public JsonResult tables(QueryRequest queryRequest, CodeGeneration generation){
        return JsonResult.success(getDataTable(codeGenerationService.tables(queryRequest,generation)));
    }
    

    /**
     * 获取sessionId，即关闭当前浏览器会有新的sessionId
     *
     * @author wangjunming
     * @since 2020/7/8 13:12
     */
    private String getSessionId() {
        return ApplicationContextUtils.getRequestSessionId();
    }


//    public static void main(String[] args) {
//        String datetime1="20200702100000";
//        String datetime2="20200628093000";
//
//        final Date date1 = new Date(Long.parseLong(datetime1));
//        final Date date2 = new Date(Long.parseLong(datetime2));
//        final long newDateTime = date1.getTime() - date2.getTime();
//        final int num = 60 * 60 * 24 * 1000;
//        //相差的天数
//        final long day = newDateTime / num;
//        final Date date = new Date(day);
//        final String yyyyMMddkkmmss = new SimpleDateFormat("yyyyMMddkkmmss").format(date);
//        System.out.println(yyyyMMddkkmmss);
//
//        String workday="20200702100000";
//        final Date workdaydate = new Date(Long.parseLong(workday));
//        final Calendar instance = Calendar.getInstance();
//        instance.setTime(workdaydate);
//        final int week = instance.get(Calendar.DAY_OF_WEEK);
//        Integer[] weeks = {2,3,4,5,6};
//        final boolean contains = Arrays.asList(weeks).contains(week);
//        System.out.println("时间："+workdaydate+"是否是工作日："+(contains ? "是": "否"));
//
////        284. 44. 和15.4
//        BigDecimal max = new BigDecimal("284.44");
//         BigDecimal min = new BigDecimal("15.4");
//
//
//        //相乘
//         BigDecimal multiply = max.multiply(min);
//        //相除
////        final BigDecimal divide = max.divide(min).setScale(10, RoundingMode.UP);
////        //相减
////         BigDecimal subtract = multiply.subtract(divide);
////        System.out.println(subtract);
//
//        //
//        BigDecimal money =new BigDecimal(132900947);
//        final DecimalFormat decimalFormat = new DecimalFormat("##,##0.00");
//        final String format = decimalFormat.format(money);
//        System.out.println(format);
//
////        GetNum nums = new GetNum();
////        Thread t1 = new Thread( nums, "A操作员");
////        Thread t2 = new Thread(  nums, "B操作员");
////        Thread t3 = new Thread( nums, "C操作员");
////        t1.start();
////        t2.start();
////        t3.start();
//
//    }
}

//class GetNum implements Runnable{
//    Integer num = 1;
//    @Override
//    public void run() {
//        for (int i = 0; i < num; i++) {
//            getNums();
//        }
//    }
//    synchronized void getNums(){
//        if(num == 1000){
//            return;
//        }
//        System.out.println("操作员："+Thread.currentThread().getName()+"所得的序列号是："+num++);
//    }
//}


