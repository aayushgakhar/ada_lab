import java.io.*;
import java.util.*;

public class _2a{
    static int n;

    public static int get(int[][] a, int i, int j){
        if(i<0 || j<0 || i>=n || j>=n){
            return 0;
        }
        return a[i][j];
    }
    public static int get2(int[][] a, int i, int j){
        if(i<0 || j<0 || i>=n || j>=n){
            return 0;
        }
        a[i][j]-=1;
        return a[i][j];
    }

    public static int dfs(int[][] a, int[][] c, boolean[][] v, int n,int i, int j,PriorityQueue<point> pq){
        if(i<0 || j<0 || i>=n || j>=n || a[i][j]==0){
            return 0;
        }
        if(v[i][j])return 0;
        v[i][j] = true;
        c[i][j] = get(a,i+1,j)+get(a,i-1,j)+get(a,i,j+1)+get(a,i,j-1);
        pq.add(new point(i, j,c[i][j]));

        return 1+dfs(a, c, v, n, i+1, j, pq)+dfs(a, c, v, n, i-1, j,pq)+dfs(a, c, v, n, i, j+1,pq)+dfs(a, c, v, n, i, j-1,pq);
    }

    public static void printarr(int[][] a){
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                System.out.print(a[i][j]+" ");
            }
            System.out.println();
        }
    }

    public static void add(PriorityQueue<point> pq,int[][] c, boolean[][] v,int i, int j) {
        if(i<0 || j<0 || i>=n || j>=n || c[i][j]==0){
            return;
        }
        pq.add(new point(i, j, get2(c,i,j)));
    }

    public static void addall(PriorityQueue<point> pq,int[][] c, boolean[][] v, int i, int j) {
        add(pq,c,v,i+1,j);
        add(pq,c,v,i-1,j);
        add(pq,c,v,i,j+1);
        add(pq,c,v,i,j-1);
        
    }
    
    public static void main(String[] args) throws IOException {
        Reader.init(System.in);
        n = Reader.nextInt();
        int[][] a = new int[n][n], c = new int[n][n];
        boolean[][] v = new boolean[n][n];
        PriorityQueue<point> pq = new PriorityQueue<>((p,q)->p.c-q.c);
        for (int i = 0; i < n; i++) {
            String s = Reader.next();
            for(int j = 0; j<n; j++){
                a[i][j] = s.charAt(j) - '0';
            }
        }
        int s = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if(!v[i][j] && a[i][j]==1){
                    int x = dfs(a, c, v, n, i, j,pq);
                    s+=x;
                    if(x%2==1){
                        System.out.println(0);
                        return;
                    }
                }
            }
            
        }
        // v = new boolean[n][n];
        ArrayList<pair> ans = new ArrayList<>();
        while(!pq.isEmpty() && ans.size()<=s/2){
            point p = pq.poll();
            if(p.i<0 || p.j<0 || p.i>=n || p.j>=n || !v[p.i][p.j] || p.c<0){
                continue;
            }
            // System.out.println("reached");
            v[p.i][p.j] = false;
            if(p.i<n-1 && v[p.i+1][p.j]){
                ans.add(new pair(p.i,p.j,p.i+1,p.j));
                a[p.i+1][p.j] = 0;
                v[p.i+1][p.j] = false;
                addall(pq, c,v, p.i, p.j);
                addall(pq, c,v, p.i+1, p.j);
                continue;
            }
            if(p.i>0 && v[p.i-1][p.j]){
                ans.add(new pair(p.i,p.j,p.i-1,p.j));
                a[p.i-1][p.j] = 0;
                v[p.i-1][p.j] = false;
                addall(pq, c,v, p.i, p.j);
                addall(pq, c,v, p.i-1, p.j);
                continue;
            }
            if(p.j<n-1 && v[p.i][p.j+1]){
                ans.add(new pair(p.i,p.j,p.i,p.j+1));
                a[p.i][p.j+1] = 0;
                v[p.i][p.j+1] = false;
                addall(pq, c,v, p.i, p.j);
                addall(pq, c,v, p.i, p.j+1);
                continue;
            }
            if(p.j>0 && v[p.i][p.j-1]){
                ans.add(new pair(p.i,p.j,p.i,p.j-1));
                a[p.i][p.j-1] = 0;
                v[p.i][p.j-1] = false;
                addall(pq, c,v, p.i, p.j);
                addall(pq, c,v, p.i, p.j-1);
                continue;
            }
            // printarr(a);
            
        }
        if(ans.size()!=s/2){
            System.out.println(0);
            return;
        }
        System.out.println(1);
        System.out.println(s/2);

        for (int i = 0; i < ans.size(); i++) {
            System.out.println(ans.get(i));
        }System.out.println();
        
    }
}
class pair{
    int a,b,c,d;
    pair(int p, int q, int r, int s){
        a=p;
        b=q;
        c=r;
        d=s;
    }
    public String toString(){
        return (a+1)+" "+(b+1)+" "+(c+1)+" "+(d+1);
    }
}

class point{
    int i,j,c;
    point(int a,int b,int c){
        i=a;
        j=b;
        this.c = c;
    }
}


class Reader {
    static BufferedReader reader;
    static StringTokenizer tokenizer;

    /** call this method to initialize reader for InputStream */
    static void init(InputStream input) {
        reader = new BufferedReader(
                new InputStreamReader(input) );
        tokenizer = new StringTokenizer("");
    }

    /** get next word */
    static String next() throws IOException {
        while ( ! tokenizer.hasMoreTokens() ) {
            //TODO add check for eof if necessary
            tokenizer = new StringTokenizer(
                    reader.readLine() );
        }
        return tokenizer.nextToken();
    }
    static String nextLine() throws IOException {
        return reader.readLine();
    }

    static int nextInt() throws IOException {
        return Integer.parseInt( next() );
    }
    static long nextLong() throws IOException{
        return Long.parseLong(next() );
    }

    static double nextDouble() throws IOException {
        return Double.parseDouble( next() );
    }
}