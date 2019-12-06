import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.*;

public class Test {

    public static String httpGet(String urlStr,Map<String,String> params){
        URL connect;
        StringBuffer data = new StringBuffer();
        try {
            connect = new URL(urlStr);
            HttpURLConnection connection = (HttpURLConnection)connect.openConnection();
            connection.setRequestMethod("GET");
            connection.setDoOutput(true);
            connection.setDoInput(true);
            connection.setUseCaches(true);//post不能使用缓存
            connection.setInstanceFollowRedirects(true);
            connection.setRequestProperty("Authorization", "bearer cn-b3a2e5d1-6a97-490d-848d-4a99a8431415");
            BufferedReader reader = new BufferedReader(new InputStreamReader(
                    connection.getInputStream(), "UTF-8"));
            String line;
            while ((line = reader.readLine()) != null) {
                data.append(line);
            }
            reader.close();
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return data.toString();
    }




                public static String httpPost(String urlStr,Map<String,String> params){
                    URL connect;
                    StringBuffer data = new StringBuffer();
                    try {
                        connect = new URL(urlStr);
                        HttpURLConnection connection = (HttpURLConnection)connect.openConnection();
                        connection.setRequestMethod("POST");
                        connection.setDoOutput(true);
                        connection.setDoInput(true);
                        connection.setUseCaches(false);//post不能使用缓存
                        connection.setInstanceFollowRedirects(true);
                        connection.setRequestProperty("Authorization", "Basic WlJLS2tLZktzejBXOVk3c0wwa3N6dmQ3akhHZUJRZzc6S09xZHlIUEpDTnowR0M5T3JxM0xMdWlES1NnNThjaXM=");
                        BufferedReader reader = new BufferedReader(new InputStreamReader(
                                connection.getInputStream(), "UTF-8"));
                        String line;
                        while ((line = reader.readLine()) != null) {
                            data.append(line);
                        }
                        reader.close();
                    } catch (Exception e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                    return data.toString();
                }
                public static void main(String[] args) {
                  /*  JSONObject json1=new JSONObject();

                    JSONObject json2=new JSONObject();
                    json2.put("fileId", "1750383708014144");
                    map.put("DATA", json2.toJSONString());
*/
                    Map<String, String> map=new HashMap<String, String>();
                    String url="https://api.bimface.com/view/token?fileId=1750383708014144";
                    System.out.println( httpGet(url,map));
            }



            }



