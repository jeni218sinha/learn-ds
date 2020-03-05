import java.util.*;

class Cell {
  int i;
  int j;
  int cost;
  char name;

  Cell(int i, int j,char name) {
    this.i = i;
    this.j = j;
    this.name = name;
    this.cost = Integer.MAX_VALUE;
  }

  Cell(int i, int j,char name, int cost) {
    this.i = i;
    this.j = j;
    this.name = name;
    this.cost = cost;
  }

  @Override
  public String toString() {
    return "["+this.i +" , "+this.j+"]";
  }
}

public class MonkeyBananaProblem {
  int[] dr = {-1,1,0,0};
  int[] dc = {0,0,-1,1};

  public boolean withinRoom(int x, int y, int row, int col) {
    if(x >= 0 && x < row && y >= 0 && y < col) {
      return true;
    }
    return false;
  }

  private ArrayList<Cell> bfs(Cell[][] room, Cell start, char end, Map<Integer, Map<Integer, Integer>> nodeCosts) {
    System.out.println(start.name + " "+ end);
    if(room == null || start == null) {
      return null;
    }
 
    Queue<Integer> xq = new LinkedList();
    Queue<Integer> yq = new LinkedList();
    int row = room.length;
    int col = room[0].length;
    boolean[][] visited = new boolean[row][col];
    ArrayList<Cell> cellsExplored = new ArrayList();
    xq.offer(start.i);
    yq.offer(start.j);
    visited[start.i][start.j] = true;
    start.cost = 0;

    while(!xq.isEmpty()) {
      int x = xq.poll();
      int y = yq.poll();
      cellsExplored.add(room[x][y]);
      if(room[x][y].name == end) {
        break;
      }
      for(int z = 0; z < 4; z++) {
        int rr = x + dr[z];
        int cc = y + dc[z];
        if(withinRoom(rr, cc, row, col) && !visited[rr][cc]) {
          visited[rr][cc] = true;
          xq.offer(rr);
          yq.offer(cc);
          int destCell = rr * col + cc;
          int srcCell = x * col +y;
          int costToDest = nodeCosts.get(srcCell).get(destCell);
          room[rr][cc].cost = room[x][y].cost + costToDest;
        }
      }
    }
    return cellsExplored;
  }

  private ArrayList<Cell> bestFirstSearch(Cell[][] room, Cell start, Cell end, Map<Integer, Map<Integer, Integer>> nodeConnections) {
      System.out.println(start.name + " "+end.name);
    if(room == null || start == null) {
      return null;
    }
    DistanceComparator distanceComparator = new DistanceComparator(end);
    PriorityQueue<Cell> priorityQueue = new PriorityQueue<>(distanceComparator);
    int row = room.length;
    int col = room[0].length;
    boolean[][] visited = new boolean[row][col];
    ArrayList<Cell> cellsExplored = new ArrayList();
    priorityQueue.offer(start);
    visited[start.i][start.j] = true;
    start.cost = 0;
 
    while(!priorityQueue.isEmpty()) {
      Cell temp = priorityQueue.poll();
      int x = temp.i;
      int y = temp.j;
      cellsExplored.add(temp);
      if(temp.name == end.name) {
        break;
      }
      for(int z = 0; z < 4; z++) {
        int rr = x + dr[z];
        int cc = y + dc[z];
        int destCell = rr * col + cc;
        int srcCell = x * col + y;
        if(withinRoom(rr, cc, row, col) && !visited[rr][cc]) {
          visited[rr][cc] = true;
          priorityQueue.offer(room[rr][cc]);
          int srcToDestCost = nodeConnections.get(srcCell).get(destCell);
          room[rr][cc].cost = room[x][y].cost + srcToDestCost;
        }
      }
    }
    return cellsExplored;
  }

  private ArrayList<Cell> ucs(Cell[][] room, Cell start, Cell end, Map<Integer, Map<Integer, Integer>> nodeConnections) {
    if(room == null || start == null) {
      return null;
    }
    int row = room.length;
    int col = room[0].length;
    int cells = row *col;
    int[] dist = new int[cells];
    Arrays.fill(dist, Integer.MAX_VALUE);
    int[] parent = new int[cells];
    boolean[][] visited = new boolean[row][col];

    CostComparator costComparator = new CostComparator(col);
    PriorityQueue<Cell> priorityQueue = new PriorityQueue<>(costComparator);
    start.cost = 0;
    dist[start.i * col + start.j] = 0;
    priorityQueue.offer(start);
    ArrayList<Cell> cellsExplored = new ArrayList<>();

    while(!priorityQueue.isEmpty()) {
        Cell temp = priorityQueue.poll();
        cellsExplored.add(temp);
        if(temp.name == end.name) {
            System.out.println(" UCS cost = "+ dist[temp.i * col + temp.j]);
            temp.cost = dist[temp.i * col + temp.j];
            break;
        }
        visited[temp.i][temp.j] = true;
        for(int z = 0; z < 4; z++) {
            int rr = temp.i + dr[z];
            int cc = temp.j + dc[z];
            int destCellno = rr * col + cc;
            int srcCellno = temp.i * col + temp.j;
            if(withinRoom(rr, cc, row, col) && !visited[rr][cc]) {
                int costToDest = nodeConnections.get(srcCellno).get(destCellno);
                if(dist[destCellno] > dist[srcCellno] + costToDest) {
                    dist[destCellno] = dist[srcCellno] + costToDest;
                    parent[destCellno] = srcCellno;
                }
                room[rr][cc].cost = dist[destCellno];
                if(priorityQueue.contains(room[rr][cc])){
                    priorityQueue.remove(room[rr][cc]);
                }
                priorityQueue.offer(room[rr][cc]);
            }
        }
    }
    return cellsExplored;
  }

  private ArrayList<Cell> astar(Cell[][] room, Cell start, Cell end, Map<Integer, Map<Integer, Integer>> nodeConnections) {
    if(room == null || start == null) {
      return null;
    }
    int row = room.length;
    int col = room[0].length;
    int cells = row *col;
    int[] dist = new int[cells];
    Arrays.fill(dist, Integer.MAX_VALUE);
    int[] parent = new int[cells];
    boolean[][] visited = new boolean[row][col];

    AStarHeuristicComparator aStarHeuristicComparator = new AStarHeuristicComparator(end);
    PriorityQueue<Cell> priorityQueue = new PriorityQueue<>(aStarHeuristicComparator);
    start.cost = 0;
    dist[start.i * col + start.j] = 0;
    priorityQueue.offer(start);
    ArrayList<Cell> cellsExplored = new ArrayList<>();

    while(!priorityQueue.isEmpty()) {
        Cell temp = priorityQueue.poll();
        cellsExplored.add(temp);
        if(temp.name == end.name) {
            System.out.println(" Astar cost = "+ dist[temp.i * col + temp.j]);
            temp.cost = dist[temp.i * col + temp.j];
            break;
        }
        visited[temp.i][temp.j] = true;
        for(int z = 0; z < 4; z++) {
            int rr = temp.i + dr[z];
            int cc = temp.j + dc[z];
            int destCellno = rr * col + cc;
            int srcCellno = temp.i * col + temp.j;
            if(withinRoom(rr, cc, row, col) && !visited[rr][cc]) {
                int costToDest = nodeConnections.get(srcCellno).get(destCellno);
                if(dist[destCellno] > dist[srcCellno] + costToDest) {
                    dist[destCellno] = dist[srcCellno] + costToDest;
                    parent[destCellno] = srcCellno;
                }
                room[rr][cc].cost = dist[destCellno];
                if(priorityQueue.contains(room[rr][cc])){
                    priorityQueue.remove(room[rr][cc]);
                }
                priorityQueue.offer(room[rr][cc]);
            }
        }
    }
    return cellsExplored;
  }

  private boolean dfs(Cell[][] room, Cell start, char end, ArrayList<Cell> cellsExplored, boolean[][] visited, Map<Integer, Map<Integer, Integer>> nodeConnections) {
    if(room == null || start == null) {
      return false;
    }

    visited[start.i][start.j] = true;
    cellsExplored.add(start);
    if(start.name == end) {
      return true;
    }
    int row = room.length;
    int col = room[0].length;
    for(int z = 0; z < 4; z++) {
      int rr = start.i + dr[z];
      int cc = start.j + dc[z];
      if(withinRoom(rr, cc, row, col) && !visited[rr][cc]) {
          int srcCell = start.i * col + start.j;
          int destCell = rr * col + cc;
          int srcToDestCost = nodeConnections.get(srcCell).get(destCell);
          room[rr][cc].cost = start.cost +  srcToDestCost;       
        if(dfs(room, room[rr][cc], end, cellsExplored, visited, nodeConnections))  {
          return true;
        }
      }
    }
    return false;
  }

  public void getBanana(Cell[][] room, Cell monkey, Cell chair, Cell banana, Map<Integer, Map<Integer, Integer>> nodeCosts) {
    System.out.println("=========== Path via BFS =============");
    SearchResult searchBFS = getBananaBfs(room, monkey, chair, banana, nodeCosts);
    List<Cell> pathBfs = searchBFS.path;
    for(Cell cell: pathBfs) {
      System.out.println(cell);
    }
    System.out.println("=========== Path via DFS =============");
    SearchResult searchDFS = getBananaDfs(room, monkey, chair, banana, nodeCosts);
    List<Cell> pathDfs = searchDFS.path;
    for(Cell cell: pathDfs) {
      System.out.println(cell);
    }
    System.out.println("=========== Path via Best FS =============");
    SearchResult searchBestFS = getBananaBestFirstSearch(room, monkey, chair, banana, nodeCosts);
    List<Cell> pathBestFS = searchBestFS.path;
    for(Cell cell: pathBestFS) {
      System.out.println(cell);
    }

    System.out.println("=========== Path via UCS =============");
    SearchResult searchUCS = getBananaUcs(room, monkey, chair, banana, nodeCosts);
    List<Cell> ucsPath = searchUCS.path;
    for(Cell cell: ucsPath) {
      System.out.println(cell);
    }


    SearchResult searchAstar = getBananaAstar(room, monkey, chair, banana, nodeCosts);
    List<Cell> astarPath = searchUCS.path;
    for(Cell cell: astarPath) {
      System.out.println(cell);
    }
    System.out.println("Algorithm\tMoves\tCost");
    System.out.println("BFS\t\t"+pathBfs.size()+"\t"+searchBFS.pathCost);
    System.out.println("DFS\t\t"+pathDfs.size()+"\t"+searchDFS.pathCost);
    System.out.println("Best FS\t\t"+pathBestFS.size()+"\t"+searchBestFS.pathCost);
    System.out.println("UCS\t\t"+ucsPath.size()+"\t"+searchUCS.pathCost);
    System.out.println("Astar\t\t"+astarPath.size()+"\t"+searchAstar.pathCost);
  }

  private SearchResult getBananaBfs(Cell[][] room, Cell monkey, Cell chair, Cell banana, Map<Integer, Map<Integer, Integer>> nodeCosts) {
    initCosts(room);
    ArrayList<Cell> path = new ArrayList();
    path.addAll(bfs(room, monkey, 'c', nodeCosts));
    Cell chairFound = path.get(path.size() - 1);
    System.out.println("Chair found "+ chairFound);
    int pathCostChair = chairFound.cost;


    initCosts(room);
    path.addAll(bfs(room, chairFound, 'b', nodeCosts));
    Cell bananaFound = path.get(path.size() - 1);
    System.out.println("Banana found "+ bananaFound);
    int pathCostBanana = bananaFound.cost;

    System.out.println("Total BFS cost = "+pathCostBanana+pathCostChair);
    SearchResult searchResult = new SearchResult(path, pathCostBanana + pathCostChair);
    return searchResult;
  }

  private SearchResult getBananaDfs(Cell[][] room, Cell monkey, Cell chair, Cell banana, Map<Integer, Map<Integer, Integer>> nodeConnections) {
    initCosts(room);
    ArrayList<Cell> cellsExplored = new ArrayList();
    int r = room.length, c = room[0].length;
    boolean[][] visited = new boolean[r][c];
    monkey.cost = 0;

    dfs(room, monkey, 'c', cellsExplored, visited, nodeConnections);
    Cell chairFound = cellsExplored.get(cellsExplored.size() - 1);
    int chairCost = chairFound.cost;
    System.out.println("Chair found "+ chairFound);

    for(boolean[] v: visited) {
      Arrays.fill(v, false);
    }
    initCosts(room);
    chairFound.cost = 0;
    dfs(room, chairFound, 'b', cellsExplored, visited, nodeConnections);
    Cell bananaFound = cellsExplored.get(cellsExplored.size() - 1);
    System.out.println(" Banana found "+ bananaFound);

    SearchResult searchResult = new SearchResult(cellsExplored, chairCost + bananaFound.cost);
    return searchResult;
  }


  private SearchResult getBananaBestFirstSearch(Cell[][] room, Cell  monkey, Cell chair, Cell banana, Map<Integer, Map<Integer, Integer>> nodeConnections) {
    initCosts(room);
    ArrayList<Cell> path = new ArrayList();
    path.addAll(bestFirstSearch(room, monkey, chair, nodeConnections));
    Cell chairFound = path.get(path.size() - 1);
    int chairCost = chairFound.cost;
    System.out.println("Chair found "+ chairFound);

    initCosts(room);
    path.addAll(bestFirstSearch(room, chairFound, banana, nodeConnections));
    Cell bananaFound = path.get(path.size() - 1);
    System.out.println("Banana found "+ bananaFound);

    SearchResult searchResult = new SearchResult(path, chairCost + bananaFound.cost);
    return searchResult;
  }

  private SearchResult getBananaUcs(Cell[][] room, Cell  monkey, Cell chair, Cell banana, Map<Integer, Map<Integer, Integer>> nodeConnections) {
    initCosts(room);
    ArrayList<Cell> path = new ArrayList();
    path.addAll(ucs(room, monkey, chair, nodeConnections));
    Cell chairFound = path.remove(path.size() - 1);
    int chairCost = chairFound.cost;
    System.out.println("Chair found "+ chairFound);

    initCosts(room);
    path.addAll(ucs(room, chairFound, banana, nodeConnections));
    Cell bananaFound = path.get(path.size() - 1);
    System.out.println("Banana found "+ bananaFound);

    SearchResult searchResult = new SearchResult(path, chairCost + bananaFound.cost);
    return searchResult;
  }

  private SearchResult getBananaAstar(Cell[][] room, Cell  monkey, Cell chair, Cell banana, Map<Integer, Map<Integer, Integer>> nodeConnections) {
     initCosts(room);
    ArrayList<Cell> path = new ArrayList();
    path.addAll(astar(room, monkey, chair, nodeConnections));
    Cell chairFound = path.remove(path.size() - 1);
    int chairCost = chairFound.cost;
    System.out.println("Chair found "+ chairFound);

    initCosts(room);
    path.addAll(astar(room, chairFound, banana, nodeConnections));
    Cell bananaFound = path.get(path.size() - 1);
    System.out.println("Banana found "+ bananaFound);

    SearchResult searchResult = new SearchResult(path, chairCost + bananaFound.cost);
    return searchResult;     
  }

  private void initCosts(Cell[][] room) {
     int row = room.length;
     int col = room[0].length;
     for(int i = 0; i < row; i++) {
        for(int j = 0; j < col; j++) {
            room[i][j].cost = Integer.MAX_VALUE;
        }
    }     
  }

  private void displayAdjacency(Cell[][] room, Map<Integer, Map<Integer, Integer>> nodesWithCosts) {
    int row = room.length;
    int col = room[0].length;
    int cells = row * col;
    for(int i = 0; i < cells; i++) {
        for(int j = 0; j < cells; j++) {
            Integer edgeCost = nodesWithCosts.get(i).get(j);
            edgeCost = edgeCost == null ? 9 : edgeCost;
            System.out.print(edgeCost+ " ");
        }
        System.out.println();
    }    
  }

  public static void main(String args[]) {
    int row = 6, col = 7;
    Random random = new Random();
    Cell[][] room  = new Cell[row][col];
    for(int i = 0; i < row; i++) {
      for(int j = 0; j < col; j++) {
        room[i][j] = new Cell(i, j, '.');
      }
    }
    room[1][1].name = 'm';
    room[2][3].name = 'b';
    room[5][4].name = 'c';
    room[3][3].name = 's';
    
    int[] dr = {-1,1,0,0};
    int[] dc = {0,0,-1,1};
    MonkeyBananaProblem  monkeyBanana = new MonkeyBananaProblem ();
    Map<Integer, Map<Integer, Integer>> nodesWithCosts = new HashMap();
    
    for(int i = 0; i < row; i++) {
        for(int j = 0; j < col; j++) {
            int cellno = i * col + j;
            if(!nodesWithCosts.containsKey(cellno)) {
                nodesWithCosts.put(cellno, new HashMap<Integer, Integer>());
            }
            Map<Integer, Integer> connectedNodes = nodesWithCosts.get(cellno);
            for(int z = 0; z < 4; z++) {
                int rr = i + dr[z];
                int cc = j + dc[z];
                int destCell = rr * col + cc;
                if(!connectedNodes.containsKey(destCell)) {
                    if(monkeyBanana.withinRoom(rr, cc, row, col)) {
                        int edgeCost = random.nextInt(5);
                        connectedNodes.put(destCell, edgeCost);
                        if(!nodesWithCosts.containsKey(destCell)) {
                            nodesWithCosts.put(destCell, new HashMap<Integer, Integer>());
                        }
                        Map<Integer, Integer> connectedNodesToDest = nodesWithCosts.get(destCell);
                        connectedNodesToDest.put(cellno, edgeCost);
                        nodesWithCosts.put(destCell, connectedNodesToDest);
                    }
                }
            }
            nodesWithCosts.put(cellno, connectedNodes);
        }
    }
    monkeyBanana.displayAdjacency(room, nodesWithCosts);
    Cell monkey = room[1][1], banana = room[2][3], chair = room[5][4];
    monkeyBanana.getBanana(room, monkey, chair, banana, nodesWithCosts);
  }
}


class DistanceComparator implements Comparator<Cell> {
  private Cell destination;

  DistanceComparator(Cell destination) {
    this.destination = destination;
  }

  @Override
  public int compare(Cell one, Cell two) {
    Double dist1 = Math.sqrt(Math.pow(destination.i - one.i, 2) + Math.pow(destination.j - one.j, 2));
    Double dist2 = Math.sqrt(Math.pow(destination.i - two.i, 2) + Math.pow(destination.j - two.j, 2));
    return dist1.compareTo(dist2);
  }
}


class AStarHeuristicComparator implements Comparator<Cell>{
    private Cell destination;
  AStarHeuristicComparator(Cell destination) {
    this.destination = destination;
  }

  @Override
  public int compare(Cell one, Cell two) {
    Double dist1 = Math.sqrt(Math.pow(destination.i - one.i, 2) + Math.pow(destination.j - one.j, 2)) + one.cost;
    Double dist2 = Math.sqrt(Math.pow(destination.i - two.i, 2) + Math.pow(destination.j - two.j, 2)) + two.cost;
    return dist1.compareTo(dist2);
  }    

}

class CostComparator implements Comparator<Cell> {
    int col;

    CostComparator(int col) {
        this.col = col;
    }
    @Override
    public int compare(Cell one , Cell two) {
        Integer cost1 = one.cost;
        Integer cost2 = two.cost;
        if(cost1 < cost2) {
            return -1;
        }
        if(cost1 > cost2) {
            return 1;
        }
        int cell1 = one.i * col + one.j;
        int cell2 = two.i * col + two.j;
        if(cell1 < cell2) {
            return -1;
        }
        return 0;
    }
}

class SearchResult {
    List<Cell> path;
    int pathCost;

    SearchResult(List<Cell> path, int pathCost) {
        this.path = path;
        this.pathCost = pathCost;
    }
}