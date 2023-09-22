# Java A* Алгоритм

Использование алгоритма поиска A* на Java

## Пример использования

```java
public static void main(String[] args){
    // Создание системы поиска пути
    int GRID_WIDTH = 15;
    int GRID_HEIGHT = 15;
    Node[][] nodeGrid = new Node[GRID_WIDTH][GRID_HEIGHT];

    // Заполнение сетки поиска пути
    for (int i = 0; i < nodeGrid.length; i++) {
        for (int j = 0; j < nodeGrid[i].length; j++) {
            nodeGrid[i][j] = new Node(i, j, true);
        }
    }

    // Запуск поиска пути 
    Node START_NODE = nodeGrid[1][3];

    // Завершение поиска пути
    Node END_NODE = nodeGrid[7][10];
        
    // Создание обьекта
    Pathfind pathfind = new Pathfind(nodeGrid);
        
    // Поиск пути
    ArrayList<Node> path = pathfind.findPath(START_NODE, END_NODE);
}
```

## Лицензия
[MIT](https://choosealicense.com/licenses/mit/)