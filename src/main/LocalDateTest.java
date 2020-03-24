import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class LocalDateTest {

    public static void main(String[] args) throws IOException {
//1.打开一个指向指定文件的输入流对象
        Reader reader = new FileReader("C:/Niagara/Niagara-4.6.96.28/security/licenses/vykon.license");

        //2.进行读取操作,字符流以字符为单位
        String str = "";
        //一次读取单个字符
        while(reader.ready()){
            str+=(char)reader.read();
        }

        System.out.println(str);

        //3.关闭资源
        reader.close();


		/*String  str = "是地方撒地方撒地方";
		System.out.println(Arrays.toString(str.toCharArray()));
		System.out.println(Arrays.toString(str.getBytes()));*/




    }
}
