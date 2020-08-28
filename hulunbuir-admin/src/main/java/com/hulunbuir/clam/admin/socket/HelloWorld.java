package com.hulunbuir.clam.admin.socket;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

/**
 * <p>
 * Explain:
 * </p >
 *
 * @author wangjunming
 * @since 2019-07-19 22:25
 */
public class HelloWorld {
    public static void main(String[] args) {

        List<Student> students = new ArrayList<>();
        final String conTelRegx = "([1-9]\\d*\\d*)|(0\\d*[1-9]) {11}";
        final String jdWdRegx = "^\\d+(\\.\\d+)?,\\d+(\\.\\d+)?";
        final String aceCodeRegx = "^[0-9]|[A-Za-z] {18,18}$";
        String aceCode = "123456789987654321";
        final boolean codeFlag = !Pattern.compile(aceCodeRegx).matcher(aceCode).matches();
        System.out.println(codeFlag);
        students.stream().anyMatch(stu ->
                !Pattern.compile(conTelRegx).matcher(stu.getPhone()).matches() ||
                        !Pattern.compile(jdWdRegx).matcher(stu.getJdWd()).matches()
        );
        final boolean anyMatch = false;
        System.out.println(anyMatch ? "验证不通过" : "验证通过");
        List<Student> studentsList = new ArrayList<>();
        studentsList.add(new Student("15321355715","116.372822,39.8915",OffsetDateTime.now()));
        List<Student> handleStudentsList = new ArrayList<>();
        students.parallelStream().forEach(student -> {
            final boolean contains = studentsList.contains(student);
            if(!contains){
                handleStudentsList.add(student);
            }
        });
        System.out.println(students);
        System.out.println("-----------------------------");
        System.out.println(studentsList);
        System.out.println("-----------------------------");
        System.out.println(handleStudentsList);
        System.out.println("-----------------------------");
        final List<Student> collect = students.parallelStream().filter(student -> !studentsList.contains(student)).collect(Collectors.toList());
        System.out.println(collect);
        String regx = "^[a-zA-Z0-9]+";
        String code = "qazwsx741RFV789poi";
        System.out.println(!Pattern.compile(regx).matcher(code).matches());
        String codeRegx = "^([1-9]\\d*\\.?\\d*)|(0\\.\\d*[1-9])|([0])|([0]\\.\\d*)$";
        String codes = "12345678945698798798";
        System.out.println(!Pattern.compile(codeRegx).matcher(codes).matches());
        String ctsccRegx = "^[0-9A-Za-z]{2,18}$";
        String codess = "91110108MA004BMB97";
        System.out.println(codess.length());
        System.out.println(!Pattern.compile(ctsccRegx).matcher(codess).matches());
        String ctsccRegxss = "^[\\u4e00-\\u9fa5_a-zA-Z0-9]{2,100}$";
        String codessss = "机构名称";
        System.out.println(codessss.length());
        System.out.println(!Pattern.compile(ctsccRegxss).matcher(codessss).matches());
        String ctsccRegxs = "^[0-9]{11}$";
        String codesss = "";
        System.out.println(codesss.length());
        System.out.println(!Pattern.compile(ctsccRegxs).matcher(codesss).matches());
        for (int i = 10; i <18; i++) {
            students.add(new Student("1212153213557"+i,"116.372822,39.89"+i,null));
        }
        final List<Student> collect1 = studentsList.parallelStream().peek(student -> {
            if(null != student.getDataes()){
                student.setDataesStr(student.getDataes().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
            }
        }).collect(Collectors.toList());
        System.out.println(collect1);

        // 按指定模式在字符串查找  ------------------------------------
        String line = "This order was placed for QT3000! OK? <img src='www.hulunubir.vip' /> oauid9asdygu  <img src=asdasd /> iusdf9a7dsfyu  <img src=6265 />oiu97adsgfoasdf  <img src=asdfasdf />";
        String pattern = "(\\D*)(\\d+)(.*)";
        String imgPattern = "^*(<img src=)|(/>)*$";

        // 创建 Pattern 对象
        Pattern r = Pattern.compile(imgPattern);
        Matcher m = r.matcher(line);
        int num = 0;
        while (m.find()){
            if(num == m.groupCount()){
                break;
            }
            String group = m.group(num);
            num ++;
            System.out.println(group);
        };

        String coordPointExpres = "[{x:116.387112, y:39.920977},{x:116.385243, y:39.913063},{x:116.394226, y:39.917988},{x:116.401772, y:39.921364},{x:116.41248, y:39.927893}]";
        final Object toJSON = JSONObject.toJSON(coordPointExpres);
        System.out.println(toJSON);
        final String toString = JSON.toJSON(toJSON).toString();
        final String toJSONString = JSON.toJSONString(coordPointExpres);

//        现在创建 matcher 对象
//        Matcher m = r.matcher(line);
//        if (m.find()) {
//            System.out.println("Found value: " + m.group(0) );
//        } else {
//            System.out.println("NO MATCH");
//        }

    }
}
class Student{
    private String phone;
    private String jdWd;
    private OffsetDateTime dataes;
    private String dataesStr;

    public Student(String phone, String jdWd, OffsetDateTime dataes) {
        this.phone = phone;
        this.jdWd = jdWd;
        this.dataes = dataes;
    }

    public String getDataesStr() {
        return dataesStr;
    }

    public void setDataesStr(String dataesStr) {
        this.dataesStr = dataesStr;
    }

    public OffsetDateTime getDataes() {
        return dataes;
    }

    public void setDataes(OffsetDateTime dataes) {
        this.dataes = dataes;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getJdWd() {
        return jdWd;
    }

    public void setJdWd(String jdWd) {
        this.jdWd = jdWd;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Student student = (Student) o;
        return Objects.equals(phone, student.phone) &&
                Objects.equals(jdWd, student.jdWd);
    }

    @Override
    public int hashCode() {
        return Objects.hash(phone, jdWd);
    }

    @Override
    public String toString() {
        return "Student{" +
                "phone='" + phone + '\'' +
                ", jdWd='" + jdWd + '\'' +
                ", dataes=" + dataes +
                ", dataesStr='" + dataesStr + '\'' +
                '}';
    }
}