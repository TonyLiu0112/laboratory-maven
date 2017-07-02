//package com.liuboyu.http;
//
//import org.springframework.web.bind.annotation.*;
//
///**
// * https://stackoverflow.com/questions/28039709/what-is-difference-between-requestbody-and-requestparam
// * <p>
// * 测试Spring中@RequestBody和@RequestParam在http各种请求类型中的区别
// * <p>
// * 总结：根据下面方法测试，参阅http协议说明、servlet规范说明，使用建议:
// * <li>对于DELETE和PUT，因存在差异的情况，尽量使用@RequestBody方式直接从http报文体中获取参数</li>
// * <li>对于GET和POST，因为servlet规范有说明绑定方式的约束，可以根据业务来自由定夺使用哪个</li>
// * <p>
// * Created by Tony on 01/06/2017.
// */
//@RestController
//public class HttpController {
//
//    /**
//     * 尝试从servlet的请求parameters中获取参数，符合规范说明则绑定参数
//     * 使用?id=1方式访问，或者在jquery.ajax({data: {id: 1}})
//     *
//     * @param id
//     * @return
//     */
//    @GetMapping("get1")
//    public Object get1(@RequestParam Long id) {
//        return id;
//    }
//
//    /**
//     * 尝试从servlet的请求parameters中获取参数，符合规范说明则绑定参数
//     * 使用?id=1方式访问，或者在jquery.ajax({data: {id: 1}})
//     *
//     * @param demo
//     * @return
//     */
//    @GetMapping("get2")
//    public Object get2(Demo demo) {
//        return demo;
//    }
//
//    /**
//     * get也属于http协议规范的一种，所以直接可以从http请求的报文体中取到
//     * 使用参考post
//     * 对于get，这种方式基本不用
//     *
//     * @param demo
//     * @return
//     */
//    @GetMapping("get3")
//    public Object get3(@RequestBody Demo demo) {
//        return demo;
//    }
//
//    /*
//     * ********************************************************************************************
//     */
//
//    /**
//     * 尝试从servlet的请求parameters中获取参数，符合规范说明则绑定参数
//     * <p>
//     * servlet规范中规定：contentType必须是 application/x-www-form-urlencoded
//     * 否则参数不绑定到方法的参数列表
//     * 又因为@RequestParam注解的约束(默认参数必填，否则报错), 所以当contentType不符合规范，报400错
//     *
//     * @param id1
//     * @param id2
//     * @return
//     */
//    @PostMapping("post0")
//    public Object getTest0(@RequestParam("id1") Long id1, @RequestParam("id2") Long id2) {
//        return new Demo(id1, id2);
//    }
//
//    /**
//     * 尝试从servlet的请求parameters中获取参数，符合规范说明则绑定参数
//     * <p>
//     * servlet规范中规定：contentType必须是 application/x-www-form-urlencoded
//     * 否则参数不绑定到方法的参数列表
//     * 此处并没有@RequestParam注解，所以当contentType不符合规范，请求可以到达，不过参数都为null罢了
//     *
//     * @param demo
//     * @return
//     */
//    @PostMapping("post1")
//    public Object getTest1(Demo demo) {
//        return demo;
//    }
//
//    /**
//     * 直接从http协议的请求报文体中获取参数字符串
//     * 并使用spring的HttpMessageConverters解码器转换参数字符串, 我们默认用的比较多的是json转换
//     *
//     * @param demo
//     * @return
//     */
//    @PostMapping("post2")
//    public Object getTest2(@RequestBody Demo demo) {
//        return demo;
//    }
//
//    /*
//     * ********************************************************************************************
//     */
//
//    /**
//     * servlet规范中并没有给出delete方法的规范说明
//     * 也就是说，除了?id=1的方式外，delete请求并不会将参数绑定到servlet的parameters参数中
//     *
//     * @param id
//     * @return
//     */
//    @DeleteMapping("delete1")
//    public Object delete1(@RequestParam("id") Long id) {
//        return id;
//    }
//
//    /**
//     * servlet规范中并没有给出delete方法的规范说明
//     * 也就是说，除了?id=1的方式外，delete请求并不会将参数绑定到servlet的parameters参数中
//     * 所以，这种方式写法不可用，完全获取不到参数
//     *
//     * @param demo
//     * @return
//     */
//    @DeleteMapping("delete2")
//    public Object delete2(Demo demo) {
//        return demo;
//    }
//
//    /**
//     * delete也属于http协议规范的一种，所以直接可以从http请求的报文体中取到
//     * 使用参考post
//     *
//     * @param demo
//     * @return
//     */
//    @DeleteMapping("delete3")
//    public Object delete3(@RequestBody Demo demo) {
//        return demo;
//    }
//
//
//    /*
//     * ********************************************************************************************
//     */
//
//    /**
//     * servlet规范中并没有给出put方法的规范说明
//     * contentType必须为application/x-www-form-urlencoded
//     * 行为和get一致
//     *
//     * @param id
//     * @return
//     */
//    @PutMapping("put1")
//    public Object put1(@RequestParam("id") Long id) {
//        return id;
//    }
//
//    /**
//     * servlet规范中并没有给出put方法的规范说明
//     * contentType必须为application/x-www-form-urlencoded
//     * 可以通过ajax data属性绑定到数据, 但根据？绑定无效
//     *
//     * @param demo
//     * @return
//     */
//    @PutMapping("put2")
//    public Object put2(Demo demo) {
//        return demo;
//    }
//
//    /**
//     * put也属于http协议规范的一种，所以直接可以从http请求的报文体中取到
//     * 使用参考post
//     *
//     * @param demo
//     * @return
//     */
//    @PutMapping("put3")
//    public Object put3(@RequestBody Demo demo) {
//        return demo;
//    }
//
//    static class Demo {
//        private Long id1;
//        private Long id2;
//
//        public Demo(Long id1, Long id2) {
//            this.id1 = id1;
//            this.id2 = id2;
//        }
//
//        public Demo() {
//        }
//
//        public Long getId1() {
//            return id1;
//        }
//
//        public void setId1(Long id1) {
//            this.id1 = id1;
//        }
//
//        public Long getId2() {
//            return id2;
//        }
//
//        public void setId2(Long id2) {
//            this.id2 = id2;
//        }
//    }
//}
