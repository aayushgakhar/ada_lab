import java.util.Scanner;

public class _1a {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = scanner.nextInt();
        }
        int m=Integer.MAX_VALUE;
        for (int i = 2; i < 200; i++) {
            boolean[] set = new boolean[101];
            int c=0;
            for (int j = 0; j < n; j++) {
                if(i-arr[j]>0 && i-arr[j]<101 && set[i-arr[j]]){
                    c++;
                    set = new boolean[101];
                }else {
                    set[arr[j]]=true;
                }
            }
            m = Math.min(m,n-c*2);
        }
        System.out.println(m);
        scanner.close();
    }

}
