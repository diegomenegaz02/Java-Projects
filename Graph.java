package Project4;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.Scanner;

public class Graph {

        private LinkedList<Integer>[] adjacencyList;  // stores all vertices for getting weight
        private int numEdges;
        private int originalNumVert;  // stores number of original vertices for building final path
        private int middleVert;

        public Graph(int numVertices) {

            adjacencyList = new LinkedList[numVertices];

            for (int i = 0; i < adjacencyList.length; i++) {
                adjacencyList[i] = new LinkedList<>();
            }

        }

        public Graph(String file) {
            loadGraph(file);
        }

        public int getOrgNumVerts() {
            return originalNumVert;
        }

        private void loadGraph(String file) {
            int totalWeight = 0;
            try {
                File myObj = new File(file);
                File getCount = new File(file);

                Scanner myReader = new Scanner(myObj);
                Scanner numReader = new Scanner(getCount);
                numReader.nextInt();
                while (numReader.hasNextLine()) {  // gets a count to know how big to make the adjacencyList
                    String data = numReader.nextLine();
                    String[] values = data.split(",");
                    if (values.length >= 3) {
                        int sumWeight = Integer.parseInt(values[2]);
                        totalWeight += sumWeight;
                    }
                }
                numReader.close();
                int num = myReader.nextInt();
                adjacencyList = new LinkedList[totalWeight];
                originalNumVert = num;
                middleVert = num;

                for (int i = 0; i < adjacencyList.length; i++) {
                    adjacencyList[i] = new LinkedList<>();
                }

                while (myReader.hasNextLine()) {
                    String data = myReader.nextLine();
                    String[] values = data.split(",");
                    int i = 0;
                    if (values.length >= 3) {
                        int one = Integer.parseInt(values[0]);
                        int two = Integer.parseInt(values[1]);
                        int weight = Integer.parseInt(values[2]);
                        if (weight > 1) {
                            addEdge(one, middleVert);
                            i++;
                            while (i < weight - 1) {
                                addEdge(middleVert, middleVert + 1);
                                middleVert++;
                                i++;
                            }
                            addEdge(middleVert, two);
                            middleVert++;
                            i++;

                        } else {
                            addEdge(one, two);
                        }
                    }
                }
                myReader.close();
            } catch (FileNotFoundException e) {
                System.out.println("File not found.");
                e.printStackTrace();
            }
        }

        public int getNumVertices() {
            return adjacencyList.length;
        }

        public int getNumEdges() {
            return numEdges;
        }

        public void addEdge(int vertex1, int vertex2) {
            adjacencyList[vertex1].add(vertex2);
            adjacencyList[vertex2].add(vertex1);

            numEdges++;
        }


        public LinkedList<Integer> getNeighbors(int vertex) {
            return adjacencyList[vertex];
        }
    }

