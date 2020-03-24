import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class sdafsd {



    public static void main(String[] args) {

String result = "{\"emsg\":\"信息传递\",\"yxList\":[],\"ycList\":[{\"MKName\":\"铜三420-G7正向有功\",\"MKValue\":\"8155670.5\",\"MKId\":\"1048\"},{\"MKName\":\"铜三420-G8正向有功\",\"MKValue\":\"7694135.0\",\"MKId\":\"1065\"},{\"MKName\":\"铜三420-G9正向有功\",\"MKValue\":\"5015325.5\",\"MKId\":\"1082\"},{\"MKName\":\"铜三420-G10正向有功\",\"MKValue\":\"5307555.0\",\"MKId\":\"1099\"},{\"MKName\":\"铜管212-G4正向有功电能\",\"MKValue\":\"5952128.5\",\"MKId\":\"1219\"},{\"MKName\":\"铜管212-G5正向有功电能\",\"MKValue\":\"0.0\",\"MKId\":\"1242\"},{\"MKName\":\"铜管212-G8正向有功电能\",\"MKValue\":\"7483259.0\",\"MKId\":\"1311\"},{\"MKName\":\"铜管212-G9正向有功电能\",\"MKValue\":\"9034489.0\",\"MKId\":\"1334\"},{\"MKName\":\"盘管105-G5正向有功\",\"MKValue\":\"9974.9\",\"MKId\":\"1428\"},{\"MKName\":\"盘管105-G6正向有功\",\"MKValue\":\"10012.2\",\"MKId\":\"1440\"}],\"ymList\":[],\"ecode\":\"1\"}";
        JSONObject jsonObject = JSONObject.parseObject(result);

        JSONArray resultObj = (JSONArray) jsonObject.get("ycList");
        List<Electric> sycls   =JSONObject.parseArray(resultObj.toJSONString(), Electric.class);
        /*String sywql = (String) resultObj.get("sywql");
        String wcl = (String) resultObj.get("wcl");
        String byxdl = (String) resultObj.get("byxdl2");
        String byrkl = (String) resultObj.get("byrkl1");*/

    }
}
