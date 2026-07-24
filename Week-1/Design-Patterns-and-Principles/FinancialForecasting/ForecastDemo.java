public class ForecastDemo {

    public static void main(String[] args) {

        double presentValue = 10000;
        double growthRate = 0.08; // 8%
        int years = 5;

        double futureValue = FinancialForecast.predictFutureValue(
                presentValue,
                growthRate,
                years
        );

        System.out.println("Present Value : ₹" + presentValue);
        System.out.println("Growth Rate   : " + (growthRate * 100) + "%");
        System.out.println("Years         : " + years);
        System.out.printf("Future Value  : ₹%.2f%n", futureValue);
    }
}