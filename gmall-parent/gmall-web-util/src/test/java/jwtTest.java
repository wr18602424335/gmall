import com.aryun.gmall.util.JwtUtil;

import java.util.HashMap;
import java.util.Map;

public class jwtTest {
    public static void main(String[] args) {
        Map map=new HashMap();
        map.put("wr",1);
        String s=JwtUtil.encode("gmall",map,"");
        System.out.println(s);

       Map map1= JwtUtil.decode("eyJhbGciOiJIUzI1NiJ9.eyJ3ciI6MX0.hz_bIZlzxrMeFADPNAYu3C0g4OrTi4YOaxywH6J_tK4","gmall","");
        System.out.println(map1);
    }

}
