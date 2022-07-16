package cn.edu.xaufe.demo.entity;

import javax.persistence.*;

@Entity     //表示该对象成为被JPA管理的实体
@Table(name = "elder", uniqueConstraints = {@UniqueConstraint(columnNames = {"eid", "phonenum"})})      //指定数据表名
public class Elder {
    @Id     //指定主键
    @GeneratedValue(strategy = GenerationType.AUTO) //自动增加?
    /*指定自增方式，有四个选项：
      GenerationType.IDENTITY 以自增主键的形式实现(仅支持有自增主键的数据库，如MySQL)
      GenerationType.SEQUENCE 以序列的形式显示(如支持有序列的数据库，如Oracle)
      GenerationType.TABLE 使用一个特定的数据表存储主键
      GenerationType.Auto 主键由程序控制   √
      */
    private long id;
    @Column(name = "eid", nullable = false, unique = true)     //指定该属性和数据库中字段的对应关系 学号->老人编号 可编辑
    private String eid;
    @Column(name = "nursinghome")      //养老院名称
    private String nursinghome;
    @Column(name = "nursing")       //住院状态
    private Boolean nursing;
    @Column(name = "name")      //姓名
    private String name;
    @Column(name = "sex", nullable = false)     //性别
    private String sex;
    @Column(name = "age")   //年龄
    private int age;
    @Column(name = "phonenum", unique = true)   //手机号
    private long phonenum;
    @Column(name = "roomid")    //房间号
    private String roomid;
    @Column(name = "address")     //家庭住址
    private String address;

    public Elder() {
    }

    public Elder(String eid, String nursinghome, String name, String sex, int age, long phonenum, String roomid, String address, boolean nursing) {
        this.eid = eid;
        this.nursinghome = nursinghome;
        this.name = name;
        this.sex = sex;
        this.age = age;
        this.phonenum = phonenum;
        this.roomid = roomid;
        this.address = address;
        this.nursing = nursing;
    }       //alt+insert

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getEid() {
        return eid;
    }

    public void setEid(String eid) {
        this.eid = eid;
    }

    public String getNursinghome() {
        return nursinghome;
    }

    public void setNursinghome(String nursinghome) {
        this.nursinghome = nursinghome;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public long getPhonenum() {
        return phonenum;
    }

    public void setPhonenum(long phonenum) {
        this.phonenum = phonenum;
    }

    public String getRoomid() {
        return roomid;
    }

    public void setRoomid(String roomid) {
        this.roomid = roomid;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public boolean isNursing() {
        return nursing;
    }

    public void setNursing(boolean nursing) {
        this.nursing = nursing;
    }

    @Override
    public String toString() {
        return "Tutorial{" +
                "id=" + id +
                ", eid='" + eid + '\'' +
                ", nursinghome='" + nursinghome + '\'' +
                ", name='" + name + '\'' +
                ", sex=" + sex +
                ", age=" + age +
                ", phonenum=" + phonenum +
                ", roomid='" + roomid + '\'' +
                ", address='" + address + '\'' +
                ", nursing=" + nursing +
                '}';
    }
}