package reference;

/**
 * 게임별로 매출을 관리할 Sales 클래스
 */
public class Sales {
    private Game game;
    private int totalQuantity; // 게임의 누적 수량
    private long totalPrice; // 게임의 누적 금액

    /**
     * 판매 금액, 판매 수량 업데이트
     * @param qunatity
     */
    public void addSale(int qunatity) {
        totalQuantity+= qunatity;
        totalPrice += game.calculateSalePrice()* qunatity;
    }

    /**
     * 게임의 영업 이익 계산
     * @return
     */
    public long profit() {
        return (long) Math.ceil(totalPrice* 0.2);
    }
}
