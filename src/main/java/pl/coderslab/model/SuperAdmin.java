package pl.coderslab.model;

public class SuperAdmin extends Admin {


    public static void setEnable(Admin admin, byte enable) {
        admin.enable = enable;
    }

    public static void setSuperAdmin(Admin admin, byte superAdmin) {
        admin.superadmin = superAdmin;
    }
}