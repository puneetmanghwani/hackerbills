package com.infinx.hacker_bills.pojo.dto;

import com.infinx.hacker_bills.pojo.model.Bill;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Column;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Data
@ToString
public class BillDTO {

    @NotNull(message = "Bill number may not be empty")
    private Long billNo;

    @NotEmpty(message = "Billed to may not be empty")
    private String billedTo;

    @NotNull(message = "Billed date may not be empty")
    private LocalDate billDate;

    @NotNull(message = "Bill due date may not be empty")
    private LocalDate dueDate;

    @NotNull(message = "Amount may not be empty")
    private Double amount;

    @NotNull(message = "Tax may not be empty")
    private Double tax;

    private Double totalAmount;

    public Bill getBill(Bill bill){
        if(bill == null){
            bill = new Bill();
        }

        bill.setBillNo(this.billNo);
        bill.setBilledTo(this.billedTo);
        bill.setBillDate(this.billDate);
        bill.setDueDate(this.dueDate);
        bill.setAmount(this.amount);
        bill.setTax(this.tax);
        bill.setTotalAmount(this.totalAmount);

        return bill;
    }


}
