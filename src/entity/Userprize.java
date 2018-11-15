package entity;

/**
 * Created by lidiwen on 2018/11/13.
 */
public class Userprize {
    private int id;
    private String name;//用户名唯一标识
    private String IP;
    private String prize;//获奖情况，一等奖（10%）、二等奖（15%）、三等奖（20%）、没获奖（54%）
    private String date;//创建日期
    private String coment;//点评，一等奖（你运气真是吊炸天），二等奖（距离大奖只差一步了，再接再厉），三等奖（运气不错，三等奖哟，祝贺你亲），无奖（非常抱歉，你的运气稍微差了一点点，不要气垒）
    private String number;//联系方式，送获奖礼物上门，运费收100块
    private int state;//状态

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrize() {
        return prize;
    }

    public void setPrize(String prize) {
        this.prize = prize;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getComent() {
        return coment;
    }

    public void setComent(String coment) {
        this.coment = coment;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getIP() {
        return IP;
    }

    public void setIP(String IP) {
        this.IP = IP;
    }


    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
