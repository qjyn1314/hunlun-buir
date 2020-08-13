package com.hulunbuir.clam.admin.socket;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
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
        for (int i = 10; i <18; i++) {
            students.add(new Student("153213557"+i,"116.372822,39.89"+i));
        }
        final String conTelRegx = "([1-9]\\d*\\d*)|(0\\d*[1-9])";
        final String jdWdRegx = "^\\d+(\\.\\d+)?,\\d+(\\.\\d+)?";
        final String aceCodeRegx = "^[0-9]|[A-Za-z] {18,18}$";
        String aceCode = "123456789987654321";
        final boolean codeFlag = !Pattern.compile(aceCodeRegx).matcher(aceCode).matches();
        System.out.println(codeFlag);
        final boolean anyMatch = students.stream().anyMatch(stu ->
                !Pattern.compile(conTelRegx).matcher(stu.getPhone()).matches() ||
                !Pattern.compile(jdWdRegx).matcher(stu.getJdWd()).matches()
        );
        System.out.println(anyMatch ? "验证不通过" : "验证通过");
        List<Student> studentsList = new ArrayList<>();
        studentsList.add(new Student("15321355715","116.372822,39.8915"));
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







    }
}
class Student{
    private String phone;
    private String jdWd;

    public Student(String phone, String jdWd) {
        this.phone = phone;
        this.jdWd = jdWd;
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
                '}';
    }
}