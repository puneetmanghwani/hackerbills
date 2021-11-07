package com.infinx.hacker_bills.service;

import com.infinx.hacker_bills.exception.BillException;
import com.infinx.hacker_bills.pojo.model.Bill;
import com.infinx.hacker_bills.repository.BillRepository;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class BillService {

    @Autowired
    private BillRepository billRepository;

    /*
    save the bill in database provided by user
     */
    public Bill saveBill(Bill bill){

        billRepository.save(bill);
        return bill;

    }

    /*
    get the bills which have id provided by client
     */
    public Bill findBill(String id) throws BillException {

        Optional<Bill> bill = billRepository.findById(id);

        if(bill.isPresent()){
            return bill.get();
        } else {
            throw new BillException("Id not found", -1);
        }

    }

    /*
    get all the bills that exists
     */
    public List<Bill> findAllBills(){

        List<Bill> bills = billRepository.findAll();
        return bills;

    }

    /*
    get the bills which have due date before the given date
     */
    public List<Bill> findDueBills(LocalDate dueDate){

        List<Bill> dueBills = billRepository.getDueBillsByDate(dueDate);
        return dueBills;

    }


}
