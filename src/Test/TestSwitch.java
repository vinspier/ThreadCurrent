package Test;

public class TestSwitch {
    public static void main(String[] args) {
        switchTest(null);
    }

    public static void switchTest(String v){
        switch (v){
            case "1":
                System.out.println("1");
                break;
            case "2":
                System.out.println("1");
                break;
            default:
                System.out.println("default");
        }
    }
}
