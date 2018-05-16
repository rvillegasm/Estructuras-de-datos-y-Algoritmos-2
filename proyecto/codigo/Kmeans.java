import java.util.Arrays;

/**
 * Algoritmo Kmeans - Se basa en dividir un conjunto de datos en k-numeros de subconjuntos llamados clusters
 * 
 * @author Felipe Cortes J & Rafael Villegas Michel & David Pierre (Reference Author)
 * @reference Se baso del algoritmo creado por David Pierre, el cual se puede encontrar en https://github.com/pierredavidbelanger/ekmeans 
 * @version 1
*/

public class Kmeans{

    protected double[][] centroids;
    protected double[][] points;
    protected boolean equal = true;  //Calcula los clusters con cardinalidad semejante en todos ellos

    protected int idealCount;
    protected double[][] distances;
    protected int[] assignments;
    protected boolean[] changed;
    protected int[] counts;
    protected boolean[] done;

    public Kmeans(double[][] centroids, double[][] points) {
        this.centroids = centroids;
        this.points = points;

        if (centroids.length > 0) {
            idealCount = points.length / centroids.length;
        } else {
            idealCount = 0;
        }
        distances = new double[centroids.length][points.length];
        assignments = new int[points.length];
        Arrays.fill(assignments, -1);
        changed = new boolean[centroids.length];
        Arrays.fill(changed, true);
        counts = new int[centroids.length];
        done = new boolean[centroids.length];
        
    }

    public int[] run() {
        return run(128);  //Numero de iteraciones para manejar los clusters
    }

    public int[] run(int iteration) {
        calculateDistances();
        int move = makeAssignments();
        int i = 0;
        while (move > 0 && i++ < iteration) {
            if (points.length >= centroids.length) {
                move = fillEmptyCentroids();
            }
            moveCentroids();
            calculateDistances();
            move += makeAssignments();
        }
        return assignments;
    }

    protected void calculateDistances() {
        distance(changed, distances, centroids, points);
        Arrays.fill(changed, false);
    }

    protected int makeAssignments() {
        int move = 0;
        Arrays.fill(counts, 0);
        for (int p = 0; p < points.length; p++) {
            int nc = nearestCentroid(p);
            if (nc == -1) {
                continue;
            }
            if (assignments[p] != nc) {
                if (assignments[p] != -1) {
                    changed[assignments[p]] = true;
                }
                changed[nc] = true;
                assignments[p] = nc;
                move++;
            }
            counts[nc]++;
            if (equal && counts[nc] > idealCount) {
                move += remakeAssignments(nc);
            }
        }
        return move;
    }

    protected int remakeAssignments(int cc) {
        int move = 0;
        double md = Double.POSITIVE_INFINITY;
        int nc = -1;
        int np = -1;
        for (int p = 0; p < points.length; p++) {
            if (assignments[p] != cc) {
                continue;
            }
            for (int c = 0; c < centroids.length; c++) {
                if (c == cc || done[c]) {
                    continue;
                }
                double d = distances[c][p];
                if (d < md) {
                    md = d;
                    nc = c;
                    np = p;
                }
            }
        }
        if (nc != -1 && np != -1) {
            if (assignments[np] != nc) {
                if (assignments[np] != -1) {
                    changed[assignments[np]] = true;
                }
                changed[nc] = true;
                assignments[np] = nc;
                move++;
            }
            counts[cc]--;
            counts[nc]++;
            if (counts[nc] > idealCount) {
                done[cc] = true;
                move += remakeAssignments(nc);
                done[cc] = false;
            }
        }
        return move;
    }

    protected int nearestCentroid(int p) {
        double md = Double.POSITIVE_INFINITY;
        int nc = -1;
        for (int c = 0; c < centroids.length; c++) {
            double d = distances[c][p];
            if (d < md) {
                md = d;
                nc = c;
            }
        }
        return nc;
    }

    protected int nearestPoint(int inc, int fromc) {
        double md = Double.POSITIVE_INFINITY;
        int np = -1;
        for (int p = 0; p < points.length; p++) {
            if (assignments[p] != inc) {
                continue;
            }
            double d = distances[fromc][p];
            if (d < md) {
                md = d;
                np = p;
            }
        }
        return np;
    }

    protected int largestCentroid(int except) {
        int lc = -1;
        int mc = 0;
        for (int c = 0; c < centroids.length; c++) {
            if (c == except) {
                continue;
            }
            if (counts[c] > mc) {
                lc = c;
            }
        }
        return lc;
    }

    protected int fillEmptyCentroids() {
        int move = 0;
        for (int c = 0; c < centroids.length; c++) {
            if (counts[c] == 0) {
                int lc = largestCentroid(c);
                int np = nearestPoint(lc, c);
                assignments[np] = c;
                counts[c]++;
                counts[lc]--;
                changed[c] = true;
                changed[lc] = true;
                move++;
            }
        }
        return move;
    }

    protected void moveCentroids() {
        center(changed, assignments, centroids, points);
    }

    //Metodos para hallar la distancia EUCLIDIANA

    public void distance(boolean[] changed, double[][] distances, double[][] centroids, double[][] points) {
            for (int c = 0; c < centroids.length; c++) {
                if (!changed[c]) continue;
                double[] centroid = centroids[c];
                for (int p = 0; p < points.length; p++) {
                    double[] point = points[p];
                    distances[c][p] = distanceM(centroid, point);
                }
            }
    }

    public double distanceM(double[] p1, double[] p2) {
        double s = 0;
        for (int d = 0; d < p1.length && d < p2.length; d++) {
            s += Math.pow(Math.abs(p1[d] - p2[d]), 2);
        }
        return Math.sqrt(s);
    }

    //Metodo para centrar los centroides

    public void center(boolean[] changed, int[] assignments, double[][] centroids, double[][] points) {
        for (int c = 0; c < centroids.length; c++) {
            if (!changed[c]) continue;
            double[] centroid = centroids[c];
            int n = 0;
            for (int p = 0; p < points.length; p++) {
                if (assignments[p] != c) continue;
                double[] point = points[p];
                if (n++ == 0) Arrays.fill(centroid, 0d);
                for (int d = 0; d < centroid.length && d < point.length; d++) {
                    centroid[d] += point[d];
                }
            }
            if (n > 0) {
                for (int d = 0; d < centroid.length; d++) {
                    centroid[d] /= n;
                }
            }
        }
    }
    
}