package main;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;

public class MyJDBC {
    Connection con = null; // 声明一个Connection对象
    PreparedStatement ps = null;
    ResultSet res = null;

    // 实现了数据库连接的功能
    public Connection getConnection() throws ClassNotFoundException, Exception {
        Class.forName("com.mysql.cj.jdbc.Driver"); // 加载数据库驱动类
        con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/test", "root", "zby20200801");
        System.out.println("连接成功！");
        return con; // 返回Connection对象
    }

    // 实现了关闭数据库连接的功能
    public void closeConnection() throws Exception {
        if (!res.isClosed()) {
            res.close();
        }
        if (!ps.isClosed()) {
            ps.close();
        }
        if (!con.isClosed()) {
            con.close();
        }
        if (res.isClosed() && ps.isClosed() && con.isClosed()) {
            System.out.println("数据库连接已关闭！");
        } else {
            System.out.println("未能成功关闭数据库连接！");
        }
    }

    // 实现查询数据表中的数据并遍历查询的结果
    public void show() throws Exception {
        ps = con.prepareStatement("select * from test");
        res = ps.executeQuery();
        while (res.next()) {
            String id = res.getString("编号");
            String name = res.getString("姓名");
            String sex = res.getString("性别");
            String birthday = res.getString("生日");

            System.out.print("编号:" + id);
            System.out.print("    姓名:" + name);
            System.out.print("    性别:" + sex);
            System.out.println("    生日:" + birthday);
        }
    }

    // 实现增加元素的功能
    public void addElement(String number, String name, String sex, String birthday) throws Exception {
        // 这里的?是表示通配符，相当于C语言的%d
        ps = con.prepareStatement("insert into test(编号,姓名,性别,生日) values(?, ?, ?, ?)");
        ps.setString(1, number);
        ps.setString(2, name);
        ps.setString(3, sex);
        ps.setString(4, birthday);
        ps.executeUpdate();
    }

    // 实现删除元素的功能
    public void delelte(String number) throws Exception {
        ps = con.prepareStatement("delete from test where 编号=?");
        ps.setString(1, number);
        ps.executeUpdate();
    }

    // 实现修改一行元素的功能
    public void change(String number, String name, String sex, String birthday) throws Exception {
        ps = con.prepareStatement("update test set 姓名 = ?, 性别 = ?, 生日 = ? where 编号 = ?");
        ps.setString(1, name);
        ps.setString(2, sex);
        ps.setString(3, birthday);
        ps.setString(4, number);
        ps.executeUpdate();
    }


    // 实现查找元素的功能
    public void search(String val) throws Exception {
        ps = con.prepareStatement("select * from test where 编号 = ?");
        ps.setString(1, val);
        res = ps.executeQuery();
        if (!res.next()) {
            System.out.println("查找失败！");
            return;
    }
        do {
            String id = res.getString("编号");
            String name = res.getString("姓名");
            String sex = res.getString("性别");
            String birthday = res.getString("生日");

            System.out.print("编号:" + id);
            System.out.print("    姓名:" + name);
            System.out.print("    性别:" + sex);
            System.out.println("    生日:" + birthday);
        } while (res.next());
    }


    // 主方法
    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);
        System.out.println("测试连接...");
        MyJDBC myJDBC = new MyJDBC(); // 实例化类的对象
        myJDBC.getConnection();
        myJDBC.show();
        String choose;
        do{
            System.out.println("1-显示数据表\n2-增加一行元素\n3-删除一行元素\n4-修改一行元素\n5-查找一行元素\n6-关闭系统");
            choose = sc.nextLine();
            switch(choose){
                case "1":
                    myJDBC.show();
                    break;
                case "2":
                    System.out.println("请输入编号：");
                    String number = sc.nextLine();
                    System.out.println("请输入姓名：");
                    String name = sc.nextLine();
                    System.out.println("请输入性别：");
                    String sex = sc.nextLine();
                    System.out.println("请输入生日：");
                    String birthday = sc.nextLine();
                    myJDBC.addElement(number, name, sex, birthday);
                    System.out.println("添加成功！");
                    myJDBC.show();
                    break;
                case "3":
                    System.out.println("请输入你要删除的行对应的编号：");
                    number = sc.nextLine();
                    myJDBC.delelte(number);
                    System.out.println("删除成功！");
                    myJDBC.show();
                    break;
                case "4":
                    System.out.println("请输入你要修改的编号：");
                    number = sc.nextLine();
                    System.out.println("新的姓名：");
                    name = sc.nextLine();
                    System.out.println("新的性别：");
                    sex = sc.nextLine();
                    System.out.println("新的生日：");
                    birthday = sc.nextLine();
                    myJDBC.change(number, name, sex, birthday);
                    break;
                case "5":
                    System.out.println("请输入你要查找的行对应的编号：");
                    number = sc.nextLine();
                    myJDBC.search(number);
                    break;
                default:
                    if(!choose.equals("6")){
                        System.out.println("请输入正确的选择！");                        
                    }

                    break;
            }
        }while(!choose.equals("6"));
        /*
            * 比较字符串时使用 .equals() 方法： 在 while 循环中，
            * 你使用了 choose != "3" 这种方式来比较字符串。在 Java 中，
            * 字符串的比较应该使用 .equals() 方法，因为 == 比较的是对象的引用，而不是内容。所以应该使用 !choose.equals("3")。
            */
        sc.close();
        myJDBC.closeConnection();
    }
}
