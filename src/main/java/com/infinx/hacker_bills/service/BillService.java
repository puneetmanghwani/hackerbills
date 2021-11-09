package com.infinx.hacker_bills.service;

import com.infinx.hacker_bills.HackerBillsApplication;
import com.infinx.hacker_bills.exception.BillException;
import com.infinx.hacker_bills.pojo.model.Bill;
import com.infinx.hacker_bills.repository.BillRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class BillService {

    @Autowired
    private BillRepository billRepository;

    private static final Logger LOGGER = LoggerFactory.getLogger(BillService.class);


    /*
    save the bill in database provided by user
     */
    public Bill saveBill(Bill bill){

        /*
        setting the total amount of bill so that if it is not included or entered wrong
        then correct total amount will be set.
         */
        LOGGER.info("Saving "+ bill.toString());

        try {
            Double totalAmount = bill.getAmount() + bill.getTax();
            bill.setTotalAmount(totalAmount);

            billRepository.save(bill);

            LOGGER.info("Bill saved");
        } catch (Exception e){
            LOGGER.error(e.getMessage());
            throw e;
        }

        return bill;

    }

    /*
    get the bills which have id provided by client
     */
    public Bill findBill(String id) throws BillException {

        LOGGER.info("Finding bill with id " + id);

        try {
            Optional<Bill> bill = billRepository.findById(id);

            if(bill.isPresent()){
                LOGGER.info("Bill found " + bill.get().toString());
                return bill.get();
            } else {
                LOGGER.error("Bill not found");
                throw new BillException("Id not found", -1);
            }
        } catch (Exception e){
            System.out.println(e.getMessage());
            throw e;
        }

    }

    /*
    get all the bills that exists
     */
    public List<Bill> findAllBills(){

        LOGGER.info("Finding all bills");

        try {
            List<Bill> bills = billRepository.findAll();
            return bills;
        } catch (Exception e){
            LOGGER.error(e.getMessage());
            throw  e;
        }

    }

    /*
    get the bills which have due date before the given date
     */
    public List<Bill> findDueBills(LocalDate dueDate){

        LOGGER.info("Finding bills having due date before " + dueDate);

        try {
            List<Bill> dueBills = billRepository.getDueBillsByDate(dueDate);
            return dueBills;
        } catch (Exception e){
            LOGGER.error(e.getMessage());
            throw e;
        }

    }


}
