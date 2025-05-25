import org.junit.jupiter.api.Test;
import reference.Game;
import reference.Sales;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SalesTest {

    @Test
    public void profit() {
        Game game = new Game("Object", 10000, 0.1);

        Sales sales = new Sales(game);
        sales.addSale(3);// 27000
        sales.addSale(2);// 18000

        assertEquals( 45000, sales.totalAmount()); // 45000
        assertEquals(9000, sales.profit()); // 9000

    }

    @Test
    public void invalid_profit() {
        Game game = new Game("Object", 10000, 0.1);

        Sales sales = new Sales(game);
        sales.addSale(3); // 27000

        Sales anotherSales = new Sales(game);
        anotherSales.addSale(2); // 18000

        assertEquals(27000, sales.totalAmount());
        assertEquals(5400, sales.profit());

        assertEquals(18000, anotherSales.totalAmount());
        assertEquals(3600, anotherSales.profit());

    }
}
