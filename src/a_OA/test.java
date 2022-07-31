package a_OA;

public class test {
    public static void main(String[] args) {
        String str = "<?xml version f14057=\"MSET_HK_PROD_SINGLE\"><f1231231=\"9890192\"";
        System.out.println(
                str.substring(str.indexOf("f14057=\"")+"f14057=\"".length(),
                        str.indexOf("\"", str.indexOf("f14057=\"") + "f14057=\"".length())));

        System.out.println(str.indexOf("f14057=\""));
        System.out.println(str.indexOf("\"", str.indexOf("f14057=\"")+1));
    }
}
