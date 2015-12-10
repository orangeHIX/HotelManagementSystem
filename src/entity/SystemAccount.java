package entity;

import java.util.List;

/**
 * Created by hyx on 2015/12/10.
 */
public class SystemAccount {
    long ID;
    String username;
    String password;
    List<Permission> permissions;
}
