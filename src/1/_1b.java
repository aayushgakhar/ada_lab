import java.util.Scanner;

public class _1b {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int[] x = new int[n];
        int[] y = new int[n];
        for (int i = 0; i < n; i++) {
            x[i] = scanner.nextInt();
            y[i] = scanner.nextInt();
        }
        double[][] dist = new double[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = i+1; j < n; j++) {
                dist[i][j] = Math.pow(Math.pow(x[i]-x[j],2) + Math.pow(y[i]-y[j],2),0.5);
            }
        }
        double minp = Integer.MAX_VALUE;
        for (int i = 0; i < n; i++) {
            for (int j = i+1; j < n; j++) {
                if (dist[i][j]*2 < minp) {
                    for (int k = j + 1; k < n; k++) {
                        minp = Math.min(dist[i][j] + dist[j][k] + dist[i][k], minp);
                    }
                }
            }
        }
        System.out.println(minp);
        scanner.close();

    }
}
