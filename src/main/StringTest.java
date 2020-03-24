import com.alibaba.fastjson.JSONObject;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

import static javafx.scene.input.KeyCode.L;
import static javafx.scene.input.KeyCode.S;

public class StringTest {


    //获取昨天时间
    public static String getYesterDayDate(){
        DateFormat dateFormat=new SimpleDateFormat("yyyy-MM-dd");
        Calendar calendar=Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY,-24);
        String yesterdayDate=dateFormat.format(calendar.getTime());
        return yesterdayDate;
    }


    public  static String DateToStringRiQi(String date){


        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date parse = null;
        try {
            parse = sdf2.parse(date);
            System.out.println(parse);
            return sdf.format(parse);
        } catch (Exception e) {
            return "";
        }
    }

    public  static   Map<String, Object> getAllElements(List<Element> childElements, Map<String,Object> mapEle) {
        for (Element ele : childElements) {
            mapEle.put(ele.getName(), ele.getTextTrim());
            if(ele.elements().size()>0){
                mapEle = getAllElements(ele.elements(), mapEle);
            }
        }
        return mapEle;
    }

    public static void main(String[] args) throws DocumentException {


        String xml = "<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\">\n" +
                "   <soapenv:Header/>\n" +
                "   <soapenv:Body xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\">\n" +
                "      <grequestResponse xmlns=\"http://service.ESB.ep.com\">\n" +
                "         <grequestReturn>\n" +
                "            <esbInfo>\n" +
                "               <attr1/>\n" +
                "               <attr2/>\n" +
                "               <attr3/>\n" +
                "               <instId/>\n" +
                "               <requestTime>2019-11-27 08:50:52.024</requestTime>\n" +
                "               <responseTime>2019-11-27 08:50:52.033</responseTime>\n" +
                "               <returnCode>S001</returnCode>\n" +
                "               <returnMsg>调用成功！</returnMsg>\n" +
                "               <returnStatus>S</returnStatus>\n" +
                "            </esbInfo>\n" +
                "            <resultInfo>\n" +
                "               <result>{\"opflag\":{\"statusMes\":\"查询成功!\",\"status\":\"S\"},\"result\":{\"sywql\":\"0\",\"wcl\":\"0\",\"byxdl\":\"0\",\"byrkl\":\"2822\"}}</result>\n" +
                "            </resultInfo>\n" +
                "         </grequestReturn>\n" +
                "      </grequestResponse>\n" +
                "   </soapenv:Body>\n" +
                "</soapenv:Envelope>";

        Document doc = DocumentHelper.parseText(xml);
        Element rootElt = doc.getRootElement();
        List<Element> childElements = rootElt.elements();
        Map<String, Object> mapEle = new HashMap<String, Object>();
        mapEle = getAllElements(childElements, mapEle);
        String result = mapEle.get("result").toString();
        JSONObject jsonObject = JSONObject.parseObject(result);
        JSONObject resultObj = (JSONObject) jsonObject.get("result");
        String sywql = (String) resultObj.get("sywql");
        String wcl = (String) resultObj.get("wcl");
        String byxdl = (String) resultObj.get("byxdl2");
        String byrkl = (String) resultObj.get("byrkl1");
        if (sywql!=null){

            System.out.println(sywql);
        }
        if (wcl!=null){
            System.out.println(wcl);
        }
        if (byxdl!=null){
            System.out.println(byxdl);
        }
        if (byrkl!=null){
            System.out.println(byrkl);
        }


    }
}
