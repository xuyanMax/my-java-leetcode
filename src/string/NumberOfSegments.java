package string;

public class NumberOfSegments {

    public static void main(String[] args) {
        System.out.println(solution2("as,     asdmas dmsam dm, sd"));
        System.out.println(solution("as,     asdmas dmsam dm, sd"));

    }

    static int solution(String str) {
        return ("x " + str).split(" +").length - 1;
    }

    static int solution2(String str) {
        //split("\\s+") 按空格,制表符，等进行拆分
        // \s \n \r \f \t...
        if (str.trim().length() == 0) return 0;
        return str.trim().split("\\s+").length;
    }

}
