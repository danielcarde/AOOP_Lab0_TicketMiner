public class Sport{

    private int id;
    private String name;
    private String date;
    private String time;
    private double VIPPrice;
    private double goldPrice;
    private double silverPrice;
    private double bronzePrice;
    private double generalAdPrice;

    public Sport(){

    }

    public Sport(int idIN,String nameIn,String dateIn, String timeIn, double VIPPriceIn, double goldPriceIn, double silverPriceIn, double bronzePriceIn, double generalAdPriceIn){
        this.id=idIN;
        this.name=nameIn;
        this.date=dateIn;
        this.time=timeIn;
        this.VIPPrice=VIPPriceIn;
        this.goldPrice=goldPriceIn;
        this.silverPrice=silverPriceIn;
        this.bronzePrice=bronzePriceIn;
        this.generalAdPrice=generalAdPriceIn;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public void setVIPPrice(double vIPPrice) {
        VIPPrice = vIPPrice;
    }

    public void setGoldPrice(double goldPrice) {
        this.goldPrice = goldPrice;
    }

    public void setSilverPrice(double silverPrice) {
        this.silverPrice = silverPrice;
    }

    public void setBronzePrice(double bronzePrice) {
        this.bronzePrice = bronzePrice;
    }

    public void setGeneralAdPrice(double generalAdPrice) {
        this.generalAdPrice = generalAdPrice;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDate() {
        return date;
    }

    public String getTime() {
        return time;
    }

    public double getVIPPrice() {
        return VIPPrice;
    }

    public double getGoldPrice() {
        return goldPrice;
    }

    public double getSilverPrice() {
        return silverPrice;
    }

    public double getBronzePrice() {
        return bronzePrice;
    }

    public double getGeneralAdPrice() {
        return generalAdPrice;
    }

    public void printInfo(){
        System.out.println("Event Id: "+getId()
                            +"\nName: "+getName()
                            +"\nDate: "+getDate()
                            +"\nTime: "+ getTime()
                            +"\nVIP Price: $"+getVIPPrice()
                            +"\nGold Price: $"+getGoldPrice()
                            +"\nBronze Price : $"+getBronzePrice()
                            +"\nGeneral Admission Price: $"+ getGeneralAdPrice()
                            );
    }
}