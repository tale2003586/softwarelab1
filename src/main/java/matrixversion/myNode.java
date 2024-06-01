package matrixversion;

public class myNode {
    private String content;
    private int outDegree;
    private int inDegree;

    public myNode() {
    }

    public myNode(String content) {
        this.outDegree = 0;
        this.content = content;
        this.inDegree = 0;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getOutDegree() {
        return outDegree;
    }

    public void setOutDegree(int outDegree) {
        this.outDegree = outDegree;
    }

    public int getInDegree() {
        return inDegree;
    }

    public void setInDegree(int inDegree) {
        this.inDegree = inDegree;
    }

    //入度加一
    public void addInDegree() {
        this.inDegree++;
    }

    //出度加一
    public void addOutDegree() {
        this.outDegree++;
    }
}
