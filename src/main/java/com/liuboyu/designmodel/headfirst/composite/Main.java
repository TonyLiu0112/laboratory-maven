package com.liuboyu.designmodel.headfirst.composite;

/**
 * Created by Tony on 16/10/2016.
 */
public class Main {
    public static void main(String[] args) {
        // 构建菜单1和它的菜品
//        MenuComponent menu1 = new Menu("湖南菜单", "我是第一个菜单哦");
//        menu1.add(new MenuItem("辣椒炒肉", "又香又辣", false, 12.08));
//        menu1.add(new MenuItem("酸辣土豆丝", "鲜香可口", true, 4.99));
//
//        MenuComponent menu2 = new Menu("东北菜单", "我是第二个菜单哦");
//        menu2.add(new MenuItem("锅包肉", "外焦里内", false, 33.87));
//        menu2.add(new MenuItem("韭菜鸡蛋", "壮阳补肾", true, 7.99));

        MenuComponent menu3 = new Menu("上海菜单", "我是第三个菜单哦");
//        menu3.add(new MenuItem("鱼香肉丝", "甜得一逼", true, 10.9));
//        menu3.add(new MenuItem("糖醋排骨", "好吃", false, 37.99));

        MenuComponent menu4 = new Menu("新疆菜单", "我是第四个菜单哦");
        menu4.add(new MenuItem("烤全羊", "外焦里内", false, 198.87));
        menu4.add(new MenuItem("大盘鸡", "好吃！", true, 92.99));

        MenuComponent allMenu = new Menu("主菜单", "我是一个主菜单，包括所有菜单哦");
//        allMenu.add(menu1);
//        allMenu.add(menu2);
        menu3.add(menu4);
        allMenu.add(menu3);

        Waitress waitress = new Waitress(allMenu);
        waitress.printVmenu();

    }
}
