package com.example.WebService.test;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.dm.model.v20151123.SingleSendMailRequest;
import com.aliyuncs.dm.model.v20151123.SingleSendMailResponse;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.exceptions.ServerException;
import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.profile.IClientProfile;
import org.apache.cxf.endpoint.Client;
import org.apache.cxf.jaxws.endpoint.dynamic.JaxWsDynamicClientFactory;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class WebServiceTest {
    public static void sendEmailBatch(ArrayList<String> urls,String body){
        JaxWsDynamicClientFactory dcf = JaxWsDynamicClientFactory.newInstance();
        Client client = dcf.createClient("http://localhost:8090/webservice/EmailService?wsdl");
        Object[] objects = new Object[0];
        try {

            // invoke("方法名",参数1,参数2,参数3....);
            objects = client.invoke("sendEmailBatch", urls,body);
            System.out.println("返回信息:" + objects[0]);
        } catch (java.lang.Exception e) {
            e.printStackTrace();
        }
    }
    public static void sendEmail(String url,String body){
        JaxWsDynamicClientFactory dcf = JaxWsDynamicClientFactory.newInstance();
        Client client = dcf.createClient("http://localhost:8090/webservice/EmailService?wsdl");
        Object[] objects = new Object[0];
        try {

            // invoke("方法名",参数1,参数2,参数3....);
            objects = client.invoke("sendEmail", url,body);
            System.out.println("返回信息:" + objects[0]);
        } catch (java.lang.Exception e) {
            e.printStackTrace();
        }
    }
    public static void main(String []args){
        Scanner scanner = new Scanner(System.in);
        System.out.println("欢迎调用WebService服务，请选择你要发送邮件的方式：");
        System.out.println("1. 单个邮件");
        System.out.println("2. 批量发送");
        int type = scanner.nextInt();
        if(type == 1){
            System.out.println("请输入你要发送的邮件地址");
            String url = scanner.next();
            System.out.println("请输入邮件内容");
            String body = scanner.next();
            WebServiceTest.sendEmail(url,body);
        }else{
            System.out.println("请输入你要发送的邮件地址,地址中间用‘，’隔开");
            String helpString = scanner.next();
            ArrayList<String> urls = new ArrayList<String>(Arrays.asList(helpString.split(",")));
            System.out.println("请输入邮件内容");
            String body = scanner.next();
            WebServiceTest.sendEmailBatch(urls, body);
        }
    }
}
