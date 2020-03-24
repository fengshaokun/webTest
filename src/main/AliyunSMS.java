import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.dysmsapi.model.v20170525.QuerySendDetailsRequest;
import com.aliyuncs.dysmsapi.model.v20170525.QuerySendDetailsResponse;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsRequest;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsResponse;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.profile.IClientProfile;

public class AliyunSMS {
	 //产品名称:云通信短信API产品,开发者无需替换
    static final String product = "Dysmsapi";
    //产品域名,开发者无需替换
    static final String domain = "dysmsapi.aliyuncs.com";
    // TODO 此处需要替换成开发者自己的AK(在阿里云访问控制台寻找)
    static final String accessKeyId = "LTAIWTGB1NhzLJOI";
    static final String accessKeySecret = "tHHZqoC3Fq02yB1whv0DyRHAMCWrYo";
    public static SendSmsResponse sendSms(String mobile,String signName,String templateCode,String templateParam) throws ClientException {
        //可自助调整超时时间
        System.setProperty("sun.net.client.defaultConnectTimeout", "10000");
        System.setProperty("sun.net.client.defaultReadTimeout", "10000");
        //初始化acsClient,暂不支持region化
        IClientProfile profile = DefaultProfile.getProfile("cn-hangzhou", accessKeyId, accessKeySecret);
        DefaultProfile.addEndpoint("cn-hangzhou", "cn-hangzhou", product, domain);
        IAcsClient acsClient = new DefaultAcsClient(profile);
        //组装请求对象-具体描述见控制台-文档部分内容
        SendSmsRequest request = new SendSmsRequest();
        //必填:待发送手机号
        request.setPhoneNumbers(mobile);
        //必填:短信签名-可在短信控制台中找到
        request.setSignName(signName);
        //必填:短信模板-可在短信控制台中找到
        request.setTemplateCode(templateCode);
        //可选:模板中的变量替换JSON串,如模板内容为"亲爱的${name},您的验证码为${code}"时,此处的值为
        request.setTemplateParam(templateParam);
        //选填-上行短信扩展码(无特殊需求用户请忽略此字段)
        //request.setSmsUpExtendCode("90997");
        //可选:outId为提供给业务方扩展字段,最终在短信回执消息中将此值带回给调用者
        request.setOutId("yourOutId");
        //hint 此处可能会抛出异常，注意catch
        SendSmsResponse sendSmsResponse = acsClient.getAcsResponse(request);
        return sendSmsResponse;
    }
    public static SendSmsResponse sendmobile(String mobile,String signName,String templateCode) throws ClientException {
    	 IClientProfile profile = DefaultProfile.getProfile("cn-hangzhou", accessKeyId, accessKeySecret);
         DefaultProfile.addEndpoint("cn-hangzhou", "cn-hangzhou", product, domain);
         IAcsClient acsClient = new DefaultAcsClient(profile);
         //组装请求对象-具体描述见控制台-文档部分内容
         SendSmsRequest request = new SendSmsRequest();
         //必填:待发送手机号
         request.setPhoneNumbers(mobile);
         //必填:短信签名-可在短信控制台中找到
         request.setSignName(signName);
         //必填:短信模板-可在短信控制台中找到
         request.setTemplateCode(templateCode);
         //可选:模板中的变量替换JSON串,如模板内容为"亲爱的${name},您的验证码为${code}"时,此处的值为
         request.setOutId("yourOutId");
         //hint 此处可能会抛出异常，注意catch
         SendSmsResponse sendSmsResponse = acsClient.getAcsResponse(request);
    	return sendSmsResponse;
    }
    public static QuerySendDetailsResponse sendstatus(String mobile,String bizid,String date) throws ClientException{
    	System.setProperty("sun.net.client.defaultConnectTimeout", "10000");
        System.setProperty("sun.net.client.defaultReadTimeout", "10000");

        //初始化ascClient
        IClientProfile profile = DefaultProfile.getProfile("cn-hangzhou", accessKeyId, accessKeySecret);
        DefaultProfile.addEndpoint("cn-hangzhou", "cn-hangzhou", product, domain);
        IAcsClient acsClient = new DefaultAcsClient(profile);
        //组装请求对象
        QuerySendDetailsRequest request = new QuerySendDetailsRequest();
        //必填-号码
        request.setPhoneNumber(mobile);
        //可选-调用发送短信接口时返回的BizId
        request.setBizId(bizid);
        //必填-短信发送的日期 支持30天内记录查询（可查其中一天的发送数据），格式yyyyMMdd
        request.setSendDate(date);
        //必填-页大小
        request.setPageSize(10L);
        //必填-当前页码从1开始计数
        request.setCurrentPage(1L);
        //hint 此处可能会抛出异常，注意catch
        QuerySendDetailsResponse querySendDetailsResponse = acsClient.getAcsResponse(request);
        //获取返回结果
       if(querySendDetailsResponse.getCode() != null && querySendDetailsResponse.getCode().equals("OK")){
        //代表请求成功
    	   return querySendDetailsResponse;
       }
       return querySendDetailsResponse;
    	
    	
    }


    public static void main(String[] args) throws ClientException, InterruptedException {
    	String model="";
		String model1="吉美思大厦";
		String model2="20";
/*    	SendSmsResponse response = AliyunSMS.sendSms("13022593931","如燕智慧养老","SMS_139225575","{\"place\":\"南京市雨花台郁金香路\"}");
*/    	SendSmsResponse response = AliyunSMS.sendSms("18770251321","如燕智慧养老","SMS_152542246",/*"{\"organization\":\""
		+ model
		+ "\",\"place\":\""
		+ model1
		+ "\",\"type\":\""
		+ "水位预警高度"
		+ "\",\"value\":\""
		+ model2
		+ "cm\"}");*/
		"{\"date\":\""
				+ "2018-12-18 19:38:00.0"
				+ "\",\"person\":\""
				+ "晓飞"
				+ "\"}");
       //SendSmsResponse response = sendSms("13022593931","如燕智慧养老","SMS_94240008","{\"hisname\":\"Tom\", \"position\":\"123\", \"wendu\":\"456\", \"shidu\":\"75\"}");
      //  SendSmsResponse response = sendSms("18205147114","异常预警","SMS_94240008","{\"hisname\":\"Tom\", \"position\":\"123\", \"wendu\":\"456\", \"shidu\":\"75\"}");
        System.out.println("短信接口返回的数据----------------");
        System.out.println("Code=" + response.getCode());
        System.out.println("Message=" + response.getMessage());
        System.out.println("RequestId=" + response.getRequestId());
        System.out.println("BizId=" + response.getBizId());
        
      /*  QuerySendDetailsResponse res=sendstatus("13022593931","657520416688156022^0","20180123");
        System.out.println("短信接口返回的数据----------------");
        System.out.println("Code=" + res.getCode());
        System.out.println("Message=" + res.getMessage());
        System.out.println("RequestId=" + res.getRequestId());
        System.out.println();
        List<SmsSendDetailDTO> list=res.getSmsSendDetailDTOs();
        for(int i=0;i<list.size();i++){
        	System.out.println("BizId=" + list.get(i).getTemplateCode());
        	System.out.println("BizId=" + list.get(i).getSendStatus());
        	System.out.println("BizId=" + list.get(i).getSendDate());
        	System.out.println("BizId=" + list.get(i).getReceiveDate());
        }*/
        
    }

}
