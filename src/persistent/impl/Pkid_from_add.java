package persistent.impl;

import java.sql.ResultSet;

/**
 * Created by yzh on 2015/12/23.
 */
public class Pkid_from_add {
    static ConnectionSQL con = new ConnectionSQL();
    Long get_pkid(String sql,int n){
        Long b = null;
        if (n>=0){
            ResultSet rs = con.querySQL(sql);
            try{rs.next();
                b = rs.getLong(1);
                System.out.println("pkid_from_add类 get_pkid方法 完成sql操作。");
            }catch (Exception e){
                e.printStackTrace();
                System.out.println("pkid_from_add类 get_pkid方法 失败 。");
            }
            return b;
        }else{
            return null;
        }
    }
}
