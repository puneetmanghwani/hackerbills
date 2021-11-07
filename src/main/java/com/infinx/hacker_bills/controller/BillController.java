package com.infinx.hacker_bills.controller;

import com.infinx.hacker_bills.exception.BillException;
import com.infinx.hacker_bills.pojo.dto.JsonResponse;
import com.infinx.hacker_bills.pojo.model.Bill;
import com.infinx.hacker_bills.service.BillService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(value = "/bills")
public class BillController {

    @Autowired
    private BillService billService;

    private static final Logger LOGGER = LoggerFactory.getLogger(BillController.class);

    @PostMapping(value = "")
    public ResponseEntity<JsonResponse> addBill(@RequestBody Bill bill){

        bill = billService.saveBill(bill);
        JsonResponse jsonResponse = new JsonResponse(true, "Bill Saved", bill, HttpStatus.OK, 1);

        return new ResponseEntity<>(jsonResponse, HttpStatus.OK);

    }

    @GetMapping(value = "")
    public ResponseEntity<JsonResponse> getAllBills(){

        List<Bill> bills = billService.findAllBills();
        JsonResponse jsonResponse = new JsonResponse(true, "Bills", bills, HttpStatus.OK, 1);

        return new ResponseEntity<>(jsonResponse, HttpStatus.OK);

    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<JsonResponse> getBill(@PathVariable(name = "id") String id) throws BillException {

        Bill bill = billService.getBill(id);
        JsonResponse jsonResponse = new JsonResponse(true, "Bills", bill, HttpStatus.OK, 1);

        return new ResponseEntity<>(jsonResponse, HttpStatus.OK);
    }

    @GetMapping(value = "/due/{dueDate}")
    public ResponseEntity<JsonResponse> getDueBills(@PathVariable(name = "dueDate") @DateTimeFormat(pattern = "dd-MM-yy") LocalDate dueDate){

        List<Bill> dueBills = billService.findDueBills(dueDate);
        JsonResponse jsonResponse = new JsonResponse(true, "Due Bills", dueBills, HttpStatus.OK, 1);

        return new ResponseEntity<>(jsonResponse, HttpStatus.OK);
    }

}
