//package com.liuboyu;
//
//import org.springframework.core.ParameterizedTypeReference;
//import org.springframework.http.HttpEntity;
//import org.springframework.http.HttpMethod;
//import org.springframework.web.client.RestTemplate;
//
//import java.util.List;
//import java.util.stream.Collectors;
//import java.util.stream.Stream;
//
//public class QueryTest {
//
//    public static void main(String[] args) {
//
//        List<String> collect = Stream.of("1", "2", "3").collect(Collectors.toList());
//
//        RestTemplate restTemplate = new RestTemplate();
//
//        HttpEntity<Object> reqEntity = new HttpEntity<>(collect);
//
//        restTemplate.exchange("", HttpMethod.GET, reqEntity, new ParameterizedTypeReference<RestResponse<List<Res>>>() {
//        });
//    }
//
//    public static class Res {
//        private String uid;
//
//        private String subAccount;
//
//        private String subAccountType;
//
//        private String subAccountName;
//
//        private String subAccountPasswd;
//
//        private String subAccountStatus;
//
//        private String mobileNo;
//
//        private String subAccountExt;
//
//        public String getUid() {
//            return uid;
//        }
//
//        public void setUid(String uid) {
//            this.uid = uid;
//        }
//
//        public String getSubAccount() {
//            return subAccount;
//        }
//
//        public void setSubAccount(String subAccount) {
//            this.subAccount = subAccount;
//        }
//
//        public String getSubAccountType() {
//            return subAccountType;
//        }
//
//        public void setSubAccountType(String subAccountType) {
//            this.subAccountType = subAccountType;
//        }
//
//        public String getSubAccountName() {
//            return subAccountName;
//        }
//
//        public void setSubAccountName(String subAccountName) {
//            this.subAccountName = subAccountName;
//        }
//
//        public String getSubAccountPasswd() {
//            return subAccountPasswd;
//        }
//
//        public void setSubAccountPasswd(String subAccountPasswd) {
//            this.subAccountPasswd = subAccountPasswd;
//        }
//
//        public String getSubAccountStatus() {
//            return subAccountStatus;
//        }
//
//        public void setSubAccountStatus(String subAccountStatus) {
//            this.subAccountStatus = subAccountStatus;
//        }
//
//        public String getMobileNo() {
//            return mobileNo;
//        }
//
//        public void setMobileNo(String mobileNo) {
//            this.mobileNo = mobileNo;
//        }
//
//        public String getSubAccountExt() {
//            return subAccountExt;
//        }
//
//        public void setSubAccountExt(String subAccountExt) {
//            this.subAccountExt = subAccountExt;
//        }
//    }
//
//}
