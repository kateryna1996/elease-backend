package nl.klev.eleasebackend.dtos;


public class BillingStatementDto {

    private Long billId;
    private double totalCosts;

    public BillingStatementDto() {
    }

    public Long getBillId() {
        return billId;
    }

    public void setBillId(Long billId) {
        this.billId = billId;
    }

    public double getTotalCosts() {
        return totalCosts;
    }

    public void setTotalCosts(double totalCosts) {
        this.totalCosts = totalCosts;
    }
}
