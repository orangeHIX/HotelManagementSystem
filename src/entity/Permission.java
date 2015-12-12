package entity;

/**
 * Created by hyx on 2015/12/10.
 */
public class Permission {
    long ID;
    String permissionName;

    public long getID() {
        return ID;
    }

    public void setID(long ID) {
        this.ID = ID;
    }

    public String getPermissionName() {
        return permissionName;
    }

    public void setPermissionName(String permissionName) {
        this.permissionName = permissionName;
    }
}
