class Pathfind {
	
    //A* Алгоритм
	
    private Node[][] nodeGrid;
    private ArrayList<Node> openList;
    private ArrayList<Node> closedList;

    private float cost, diagonalCost;

    public Pathfind(Node[][] nodeGrid) {

        this.openList = new ArrayList<>();
        this.closedList = new ArrayList<>();

        this.cost = 1;
        this.diagonalCost = 1.4f;

        this.nodeGrid = nodeGrid;
    }

    // Возвращает наиболее эффективный путь в виде ArrayList.
    public ArrayList<Node> findPath(Node startNode, Node goalNode) {
        this.openList.clear();
        this.closedList.clear();

        // Создание родительского элемента нулевым
        for (int i = 0; i < this.nodeGrid.length; i++) {
            for (int j = 0; j < this.nodeGrid[0].length; j++) {
                this.nodeGrid[i][j].setParent(null);
            }
        }

        this.openList.add(startNode);

        // Продолжение пути, пока openList не станет пустым
        while (!openList.isEmpty()) {

            // Сортировка листа по F
            openList.sort((node1, node2) -> Float.compare(node1.getF(), node2.getF()));
            Node currentNode = openList.get(0);

            openList.remove(currentNode);
            closedList.add(currentNode);

            if (!currentNode.isWalkable()) continue;

            // Если путь найден - то (Условие)
            if (currentNode == goalNode) {
                return constructPath(currentNode);
            }

            ArrayList<Node> children = getAdjacentNodes(currentNode);

            // Перебор дочерних элементов узла
            for (Node child : children) {

                // Если элемент в закрытом списке - пропускаем
                if (closedList.contains(child)) continue;

                float cost = (currentNode.getX() != child.getX() && currentNode.getY() != child.getY()) ? this.diagonalCost : this.cost;
                float tentativeGScore = currentNode.getG() + cost;

                if (!openList.contains(child)) {
                    openList.add(child);
                } else if (tentativeGScore >= child.getG()) {
                    continue;
                }

                child.setParent(currentNode);
                child.setG(tentativeGScore);
                child.setH(Math.abs(child.getX() - goalNode.getX()) + Math.abs(child.getY() - goalNode.getY()));
                child.setF();
            }
        }

        return new ArrayList<>();
    }

    private ArrayList<Node> constructPath(Node constructFrom) {
        ArrayList<Node> path = new ArrayList<>();

        while (true) {
            path.add(constructFrom);

            if (constructFrom.getParent() == null) {
                break;
            }

            constructFrom = constructFrom.getParent();
        }

        Collections.reverse(path);

        return path;
    }

    private ArrayList<Node> getAdjacentNodes(Node node) {

        ArrayList<Node> adjacentNodes = new ArrayList<>();
        // Прохождение через соседние узлы (Точки)
        for (int x = -1; x <= 1; x++) {
            for (int y = -1; y <= 1; y++) {

                // Если X = 0, то пропускаем
                if (x == 0 && y == 0) {
                    continue;
                }

                if (node == null) {
                    System.out.println("getAdjacentNodes() - Nulled"); /* Дебаг информация */
                    return adjacentNodes;
                }

                int newX = node.getX() + x;
                int newY = node.getY() + y;

                if (validateCoordinates(newX, newY)) {
                    adjacentNodes.add(nodeGrid[newX][newY]);
                }
            }
        }
        return adjacentNodes;
    }

    private boolean validateCoordinates(int x, int y) {
        if (x >= 0 && x < nodeGrid.length) { // X Axis
            if (y >= 0 && y < nodeGrid[x].length) { // Y Axis
                return true;
            }
        }
        return false;
    }
}