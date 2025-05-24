package reference;

public class Game {
    private String title;
    private long price;
    private double discountRate;

    public Game(String title, long price, double discountRate) {
        this.title = title;
        this.price = price;
        this.discountRate = discountRate;
    }

    /**
     * 게임 하나당 가격 계산
     * @return
     */
    public long calculateSalePrice() {
        return price - (long)Math.ceil(price* discountRate);
    }
}
