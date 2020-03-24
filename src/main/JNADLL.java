import com.sun.jna.Library;
import com.sun.jna.Native;

public class JNADLL {


  /*  public interface Dll extends Library {
        Dll instance = (Dll) Native.loadLibrary("D:\\dll\\ACSSDK.dll", Dll.class);

       boolean ACS_Init();
       void ACS_Release();
    }
*/
    public static void main(String[] args) {
      /* Dll d = Dll.instance;
       boolean preprocess = Dll.instance.ACS_Init();

        System.out.println("值为："+preprocess);
       // System.out.println(Dll.instance.ACS_Release());*/
        boolean b = ACSSDKDll.instance.ACS_Init();
        System.out.println(b);

    }
}
