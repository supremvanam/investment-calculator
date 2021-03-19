package sample;

public class Investment {
    private final double investmentAmount;
    private final int numberOfYears;
    private final double interestRate;

    public Investment(double investmentAmount, int numberOfYears, double interestRate){
        this.investmentAmount = investmentAmount;
        this.numberOfYears = numberOfYears;
        this.interestRate = interestRate;
    }

    public double getFutureValue() {
        // Formula --> A = P(1 + r/n)nt
        double r = interestRate/100;
        double period = 12 * numberOfYears;
        double interestAmountOnInvestment = Math.pow(1+(r/12), period);
        return investmentAmount * interestAmountOnInvestment;
    }

}
