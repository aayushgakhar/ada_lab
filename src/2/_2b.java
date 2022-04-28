import java.io.*;
import java.util.*;


public class _2b{

    static int n;

    public static int operation(int w){
        int m = w;
        int x = w;
        if(x%2==0){
            m = Math.min(m,w/2+4);
            while(x%2==0){
                x/=2;
            }
        }
        
        for (int i = 3; i < Math.sqrt(w); i+=2) {
            if(x%i==0){
                m = Math.min(m,w/i+2*i);
                while(x%i==0){
                    x/=i;
                }
            }
        }
        if(x>1){
            m = Math.min(m,w/x+2*x);
        }
        return m;
    }

    public static int minV(int[] dist, boolean[] sptSet){
        int m = Integer.MAX_VALUE, mi = -1;
        for (int i = 0; i < n; i++) {
            if(!sptSet[i] && dist[i]<=m){
                m=dist[i];
                mi = i;
            }
        }
        return mi;
    }

    public static int dijkstra(List<List<pair>> adj, int n, int k, int s, int t){
        int[][] dist = new int[k+1][n];
        for (int[] i : dist) {
            Arrays.fill(i,Integer.MAX_VALUE);
        }

        for (int i = 0; i < k+1; i++){
            // boolean[] sptSet = new boolean[n];
            HashSet<Integer> set = new HashSet<>();
            dist[i][s] = 0;
            PriorityQueue<pair> pq = new PriorityQueue<>((p,q)->(p.w-q.w));
            pq.add(new pair(s, 0, 0));
            while(set.size()<n){
                if(pq.isEmpty()){
                    break;
                }
                int u = pq.poll().v;

                if(set.contains(u)){
                    continue;
                }
                set.add(u);
                for(pair p:adj.get(u)){
                    int v = p.v;
                    if(!set.contains(v)){
                        if(dist[i][v]>dist[i][u]+p.w){
                            dist[i][v] = dist[i][u]+p.w;
                        }
                        pq.add(new pair(v, dist[i][v], 0));
                    }
                    if(i<k && dist[i+1][v]>dist[i][u]+p.nw){
                        dist[i+1][v]=dist[i][u]+p.nw;
                    }
                }
            }
        }
        int mn = dist[0][t];
        for (int i = 0; i < k+1; i++) {
            mn = Math.min(mn, dist[i][t]);
        }
        return mn;
    }
    
    public static void main(String[] args) throws IOException {
        Reader.init(System.in);
        n= Reader.nextInt();
        int m = Reader.nextInt(), k = Reader.nextInt();
        List<List<pair>> adj = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            adj.add(new ArrayList<>());
        }
        for (int i = 0; i < m; i++) {
            int u = Reader.nextInt()-1, v = Reader.nextInt()-1, w = Reader.nextInt();
            int x = operation(w);
            adj.get(u).add(new pair(v,w,x));
            adj.get(v).add(new pair(u,w,x));

        }
        int A = Reader.nextInt()-1, B = Reader.nextInt()-1;
        int a= dijkstra(adj, n, k, A, B);
        System.out.println(a);
    }
}

class pair{
    int v,w,nw;
    pair(int a, int b,int c){
        v=a;
        w=b;
        nw=c;
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

/*
6 7 2
1 2 4
2 5 14
1 6 9
6 3 3
3 4 4
4 5 8
1 4 9
5 1



*/