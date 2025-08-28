package fr.dragonsslayer.board;

public abstract class Cell {
    private Object content;

    public Cell(Object content){
        this.content = content;
    }

    public Object getContent() {
        return content;
    }

    public abstract void interact();
}
