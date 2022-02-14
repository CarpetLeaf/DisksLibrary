package sample;

public class Place {
    String type;
    int myId, num, cnt;

    public Place(int myId, String type, int num, int cnt) {
        this.type = type;
        this.myId = myId;
        this.num = num;
        this.cnt = cnt;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getMyId() {
        return myId;
    }

    public void setMyId(int myId) {
        this.myId = myId;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public int getCnt() {
        return cnt;
    }

    public void setCnt(int cnt) {
        this.cnt = cnt;
    }
}
