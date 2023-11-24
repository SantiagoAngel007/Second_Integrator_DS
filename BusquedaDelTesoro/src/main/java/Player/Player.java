package Player;

public class Player {

    static private Player player;
    private String username;
    private int coinAmount;
    private int lifeStatus;
    private String word;

    private Player(){
    }

    static public Player getInstance(){
        if(player ==null) player = new Player();
        return player;
    }

    public int getCoinAmount() {
        return coinAmount;
    }

    public void setCoinAmount(int coinAmount) {
        this.coinAmount = coinAmount;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getLifeStatus() {
        return lifeStatus;
    }
    public void setLifeStatus(int lifeStatus) {
        this.lifeStatus = lifeStatus;
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }
}
