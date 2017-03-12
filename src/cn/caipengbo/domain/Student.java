package cn.caipengbo.domain;

/**
 * Created by Myth on 3/3/2017.
 */
public class Student {
    private long id;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    private String name;
    private int age;
    public Student(String name,int age) {this.name = name; this.age = age;}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
