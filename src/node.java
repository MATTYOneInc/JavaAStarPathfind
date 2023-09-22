class Node {

    private float f, g, h;
    private Node parent;
    private int x, y;
    private boolean walkable;

    public Node(int x, int y, boolean walkable) {
        this.x = x;
        this.y = y;
        this.walkable = walkable;
    }

    public float getF() {
        return this.f;
    }

    public int getX() {
        return this.x;
    }

    public int getY() {
        return this.y;
    }

    public float getG() {
        return this.g;
    }

    public Node getParent() {
        return this.parent;
    }

    public boolean isWalkable() {
        return this.walkable;
    }

    public void setG(float g) {
        this.g = g;
    }

    public void setH(float h) {
        this.h = h;
    }

    public void setF() {
        this.f = this.g + this.h;
    }

    public void setParent(Node parent) {
        this.parent = parent;
    }

    public void setWalkable(boolean walkable) {
        this.walkable = walkable;
    }

}