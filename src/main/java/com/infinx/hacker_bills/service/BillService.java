package com.infinx.hacker_bills.service;

import com.infinx.hacker_bills.pojo.model.Bill;
import com.infinx.hacker_bills.repository.BillRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class BillService {

    @Autowired
    private BillRepository billRepository;

    public Bill saveBill(Bill bill){

        billRepository.save(bill);

        return bill;

    }

    public Bill getBill(String id){

        Optional<Bill> bill = billRepository.findById(id);

        if(bill.isPresent()){
            return bill.get();
        }

        return null;

    }

    public List<Bill> findAllBills(){

        List<Bill> bills = billRepository.findAll();

        return bills;

    }

    public List<Bill> findDueBills(LocalDate dueDate){

        List<Bill> dueBills = billRepository.getDueBillsByDate(dueDate);

        return dueBills;

    }


}
