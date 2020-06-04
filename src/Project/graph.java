package Project;
import java.io.*;
import java.util.*;

public class graph {
    public boolean valid;
    ArrayList<Vertex> adjList = new ArrayList<Vertex>();
    class Vertex {
        String name;
        Double latitude;
        Double longitude;
        LinkedList<Vertex> edges;
        boolean visit;

        Vertex(String label, Double lat, Double lon) {
            name = label;
            latitude = lat;
            longitude = lon;
            edges = new LinkedList<Vertex>();
        }

        @Override
        public String toString() {
            return name;
        }
    }

    public int getLocation(String label) {
        for (int i = 0; i < adjList.size(); i++)
            if(adjList.get(i).name.compareTo(label) == 0)
                return i;
        return -1;
    }

    public int getEdge(String insert, String label) {
        int L = getLocation(insert);
        for(int i = 0; i < adjList.get(L).edges.size(); i++)
            if(adjList.get(L).name.equals(label))
                return i;
        return -1;
    }

    public void addVertex(String label, Double lat, Double lon) {
        if(getLocation(label) == -1)
            adjList.add(new Vertex(label, lat, lon));
    }

    public void addEdge(String insert, String label) {
        if (getEdge(insert, label) == -1 && getEdge(label, insert) == -1) {
            adjList.get(getLocation(insert)).edges.add(adjList.get(getLocation(label)));
            adjList.get(getLocation(label)).edges.add(adjList.get(getLocation(insert)));
        }
    }

    public void fileReading() {
        try {
            FileInputStream fStream = new FileInputStream("Location.txt");

            DataInputStream dStream = new DataInputStream(fStream);

            BufferedReader bReader = new BufferedReader(new InputStreamReader(dStream));

            String strLine;

            while((strLine = bReader.readLine()) != null) {
                String[] location = strLine.split(",", 3);
                addVertex(location[0], Double.parseDouble(location[1]), Double.parseDouble(location[2]));
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void addingEdges() {
        try {
            FileInputStream fStream = new FileInputStream("Edges.txt");

            DataInputStream dStream = new DataInputStream(fStream);

            BufferedReader bReader = new BufferedReader(new InputStreamReader(dStream));

            String strLine;

            while((strLine = bReader.readLine()) != null) {
                String[] edLoc = strLine.split(",");
                for(int i = 1; i < edLoc.length; i++)
                    addEdge(edLoc[0], edLoc[i]);
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
//    public boolean validPath(String L1,String L2){
//         int d = 0;
//         Stack<String> stk = new Stack<>();
//         LinkedList<Vertex> Q = new LinkedList<>();
//        String[] pred = new String[adjList.size()];
//        for (int i = 0; i < pred.length; i++)
//            pred[i] = null;
//        String src = L1;
//        String dest = L2;
//         Vertex v = adjList.get(getLocation(src));
//        Q.add(v);
//        v.visit = true;
//        //System.out.printf("Following is the shortest BFS traversal from %s to %s: ", src, dest);
//        while(!Q.isEmpty()) {
//            v = Q.poll();
//            for (int i = 0; i < v.edges.size(); i++) {
//                if (!v.edges.get(i).visit) {
//                    Q.add(v.edges.get(i));
//                    pred[getLocation(v.edges.get(i).name)] = adjList.get(getLocation(v.name)).name;
//                    v.edges.get(i).visit = true;
//                }
//            }
//        }
//        stk.push(dest);
//        while(pred[getLocation(dest)] != null) {
//            d += distances(stk.push(pred[getLocation(dest)]), dest);
//            dest = pred[getLocation(dest)];
//        }
//        return stk.size() > 1; //System.out.println("A path doesn't exist!");
//    }
    public String[] shortestPathBFS(String L1, String L2) {
       int d = 0;
        Stack<String> stk = new Stack<>();
        LinkedList<Vertex> Q = new LinkedList<>();
        String[] pred = new String[adjList.size()];
        for (int i = 0; i < pred.length; i++)
            pred[i] = null;
        String src = L1;
        String dest = L2;
        Vertex v = adjList.get(getLocation(src));
        Q.add(v);
        v.visit = true;
        System.out.printf("Following is the shortest BFS traversal from %s to %s: ", src, dest);
        while(!Q.isEmpty()) {
            v = Q.poll();
            for (int i = 0; i < v.edges.size(); i++) {
                if (!v.edges.get(i).visit) {
                    Q.add(v.edges.get(i));
                    pred[getLocation(v.edges.get(i).name)] = adjList.get(getLocation(v.name)).name;
                    v.edges.get(i).visit = true;
                }
            }
        }
        stk.push(dest);
        while(pred[getLocation(dest)] != null) {
            d += distances(stk.push(pred[getLocation(dest)]), dest);
            dest = pred[getLocation(dest)];
        }
        if(stk.size() == 1) {
            System.out.println("A path doesn't exist!");
            this.valid = false;
            return null;
        }
        else {
            this.valid = true;
            while (!stk.empty()) {
                System.out.print(stk.pop() + " -> ");
            }
            System.out.printf("Shortest Distance: %dm.", d);
        }
          return pred;
    }
    public int distances(String v1, String v2) {
        double theCos = Math.sin(adjList.get(getLocation(v1)).latitude) * Math.sin(adjList.get(getLocation(v2)).latitude) + Math.cos(adjList.get(getLocation(v1)).latitude) * Math.cos(adjList.get(getLocation(v2)).latitude) * Math.cos(adjList.get(getLocation(v1)).longitude - adjList.get(getLocation(v2)).longitude);
        double arcLength = Math.acos(theCos) * 1609.34;
        return (int) arcLength;
    }

    public void display() {
        for(Vertex v: adjList)
            System.out.println(v.toString() + " --> " + v.edges.toString());
    }

    public static void main(String[] args) {
        graph g = new graph();
        g.fileReading();
        g.addingEdges();
       // g.display();
      g.shortestPathBFS("Discovery Park", "Woodland Park Zoo");
    }
}
