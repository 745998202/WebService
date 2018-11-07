package com.example.WebService;

import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.dm.model.v20151123.SingleSendMailRequest;
import com.aliyuncs.dm.model.v20151123.SingleSendMailResponse;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.exceptions.ServerException;
import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.profile.IClientProfile;
import org.springframework.stereotype.Component;

import javax.jws.WebService;

@WebService(serviceName = "EmailService",portName = "EmailServiceImp",targetNamespace = "http://WebService.example.com/",endpointInterface = "com.example.WebService.EmailService")
@Component
public class EmialServiceImp implements  EmailService{
    @Override
    public String sendEmail(String url, String body) {
        if(validEmailAddress(url).equals("N"))
            return "N";
        IClientProfile profile = DefaultProfile.getProfile("cn-hangzhou", "LTAIemSPJRU9fFsU", "QWQBBO5OIPRpAjup46g1ZosPEMvBW1");
        IAcsClient client = new DefaultAcsClient(profile);
        SingleSendMailRequest request = new SingleSendMailRequest();
        try {
            //request.setVersion("2017-06-22");// 如果是除杭州region外的其它region（如新加坡region）,必须指定为2017-06-22
            request.setAccountName("menglong@email.aeljinh.cn");
            request.setFromAlias("姚孟隆");
            request.setAddressType(1);
            request.setTagName("Test");
            request.setReplyToAddress(true);
            request.setToAddress(url);
            //可以给多个收件人发送邮件，收件人之间用逗号分开，批量发信建议使用BatchSendMailRequest方式
            //request.setToAddress("邮箱1,邮箱2");
            request.setSubject("Email");
            request.setHtmlBody(body);
            SingleSendMailResponse httpResponse = client.getAcsResponse(request);
        } catch (ServerException e) {
            e.printStackTrace();
        }
        catch (ClientException e) {
            e.printStackTrace();
        }
        return "Y";
    }

    @Override
    public String sendEmailBatch(String[] urls, String body) {
        IClientProfile profile = DefaultProfile.getProfile("cn-hangzhou", "LTAIemSPJRU9fFsU", "QWQBBO5OIPRpAjup46g1ZosPEMvBW1");
        IAcsClient client = new DefaultAcsClient(profile);
        SingleSendMailRequest request = new SingleSendMailRequest();
        try {
            //request.setVersion("2017-06-22");// 如果是除杭州region外的其它region（如新加坡region）,必须指定为2017-06-22
            request.setAccountName("menglong@email.aeljinh.cn");
            request.setFromAlias("姚孟隆");
            request.setAddressType(1);
            request.setTagName("Test");
            request.setReplyToAddress(true);
            String Address = "";
            for(int i = 0;i<urls.length;i++) {
                if(validEmailAddress(urls[i]).equals("N"))
                    return "N";
                if(i!=0)
                    Address=Address+","+urls[i];
                else
                    Address=urls[0];
            }
            request.setToAddress(Address);
            //可以给多个收件人发送邮件，收件人之间用逗号分开，批量发信建议使用BatchSendMailRequest方式
            //request.setToAddress("邮箱1,邮箱2");
            request.setSubject("Email");
            request.setHtmlBody(body);
            SingleSendMailResponse httpResponse = client.getAcsResponse(request);
        } catch (ServerException e) {
            e.printStackTrace();
        }
        catch (ClientException e) {
            e.printStackTrace();
        }
        return "Y";
    }

    @Override
    public String validEmailAddress(String url) {
        String regex="\\w{0,}\\@\\w{0,}\\.{1}\\w{0,}";
        if(url.matches(regex))
            return "Y";
        else
            return "N";
    }
}
