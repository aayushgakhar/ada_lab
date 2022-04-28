import java.io.*;
import java.util.*;

public class _1c {
    static int bft = 40;
    static int ss = 4;
    static double bf(point p[], int l, int r, double res) {
        if (l >= r-2) {
            if (l == r-2) {
                return p[l].dist(p[l+1])+p[l+2].dist(p[l+1])+p[l].dist(p[l+2]);
            }
            return res;
        }
        for (int i = l; i <= r; i++) {
            for (int j = i + 1; j <= r; j++) {
                if (p[i].dist(p[j]) < res/2) {
                    for (int k = j + 1; k <= r; k++) {
                        res = Math.min(res, p[i].dist(p[j]) + p[j].dist(p[k]) + p[i].dist(p[k]));
                    }
                }
            }
        }
        return res;
    }
    static double fstrip(List<point> p, double res) {
        p.sort((a, b) -> a.y == b.y ? a.x - b.x : a.y - b.y);
        int r = p.size()-1;
        for (int i = 0; i <= r; i++) {
            int en = i + ss;
            for (int j = i + 1; j<en && j <= r; j++) {
                if (p.get(i).dist(p.get(j)) < res/2) {
                    for (int k = j + 1; k<en && k <= r; k++) {
                        res = Math.min(res, p.get(i).dist(p.get(j)) + p.get(j).dist(p.get(k)) + p.get(i).dist(p.get(k)));
                    }
                }
            }
        }
        return res;
    }
    static double f(point p[], int l, int r, double res) {

        if (l>=r){
                return Integer.MAX_VALUE;
        }
        if(r-l < bft) {
            return bf(p, l, r, res);
        }
        int mid = (l + r) / 2;
        res = Math.min(f(p, l, mid, res), f(p, mid+1 , r, res));
        double sd = res/2;
        List<point> m = new ArrayList<>();
        double mid_x = p[mid].x;
        for (int i = l; i <= r; i++) {
            if (Math.abs(p[i].x - mid_x) < sd) {
                m.add(p[i]);
            }
        }
        int len = m.size();
        if (len <3){
            return res;
        }
        return fstrip(m, res);
    }
//5 0 0 0 3 3 0 1 1 2 3
    public static void main(String[] args) throws IOException {
        Reader.init(System.in);
        BufferedWriter output = new BufferedWriter(new OutputStreamWriter(System.out));
        int n = Reader.nextInt();
        point[] p = new point[n];
        for (int i = 0; i < n; i++) {
            p[i] = new point(Reader.nextInt(), Reader.nextInt());
        }
        Arrays.sort(p, (a,b)-> a.x == b.x ? a.y - b.y : a.x - b.x);
        output.write(f(p, 0, n - 1, Integer.MAX_VALUE)+"\n");
        output.close();
    }
}

class point {
    int x, y,n;
    public HashMap<point, Double> dists = new HashMap<>();
    point(int x, int y) {
        this.x = x;
        this.y = y;
        this.n = 0;
    }
    double dist(point p) {
//        if(dists.containsKey(p)) return dists.get(p);
        double d = Math.sqrt(Math.pow(x - p.x,2) + Math.pow(y - p.y,2));
//        if(n<10000){
//            dists.put(p, d);
//            p.dists.put(this, d);
//            n++;
//        }

        return d;
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