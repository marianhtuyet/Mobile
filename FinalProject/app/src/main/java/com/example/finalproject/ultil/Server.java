package com.example.finalproject.ultil;

public class Server {
    //10.80.254.63
    public static String localhost = "192.168.1.6";
    public static String urlCategory = "http://"+ localhost + ":8080/server/GetCategory.php?_ijt=k8r11l8j0tikof6v308jfcpgrf";
    public static String urlNewProduct = "http://"+ localhost + ":8080/server/getNewProduct.php?_ijt=iuk4hskn5fav17lbl7evvkj5k0";
    public static String urlSoMiNam = "http://"+ localhost + ":8080/server/getProduct.php?page=";
    public static String urlCustomer = "http://"+localhost+":8080/server/getCustomer.php?_ijt=u5486lk8kl4i1rkquc06j26upl";
    public static String urlCustomerAddress = "http://"+localhost+":8080/server/getCustomerAddress.php?_ijt=os3oq3803hq4is5kscvja72pal";
    public static String urlInsertHoaDon ="http://"+localhost+":8080/server/insertHoaDon.php";
    public static String urlInsertCTHD="http://"+localhost+":8080/server/insertCTHD.php";
}
