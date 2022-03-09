package test;

public class test1 {

    public static void main(String[] args) {
        for(int i = 0; i < 10; i++) {
            System.out.println("hello world：" + i);
            try {
                Thread.sleep(1000*3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("end!");
    }
}
