package com.chanzany.JVM;


class Person{
    private int age;
    private String name;
    public Person(String name){
        this.name = name;
    }
    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
public class jvm_02_TestTransferValue {
    private void changeValue1(int age){
        age = 30;
    }

    private void changeValue2(Person person){
        person.setName("xxx");
    }

    private void changeValue3(String str){
        str="xxx";
    }

    /**
     * 基本类型作为参数：传的是复印件，原件不动
     * 引用类型作为参数：传的是引用(地址)
     */
    public static void main(String[] args) {
        jvm_02_TestTransferValue test = new jvm_02_TestTransferValue();
        int age = 20;
        test.changeValue1(age);
        System.out.println(age);

        System.out.println("--------------------------------");
        Person person = new Person("abc");
        test.changeValue2(person);
        System.out.println(person.getName());

        System.out.println("--------------------------------");

        String str = "abc";
        test.changeValue3(str);
        System.out.println(str);

    }

}
