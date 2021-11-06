package com.infinx.hacker_bills.controller;

import com.infinx.hacker_bills.pojo.dto.JsonResponse;
import com.infinx.hacker_bills.pojo.model.Bill;
import com.infinx.hacker_bills.service.BillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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

    @PostMapping(value = "/")
    public JsonResponse addBill(@RequestBody Bill bill){

        bill = billService.saveBill(bill);

        JsonResponse jsonResponse = new JsonResponse(true, "Bill Saved", bill, HttpStatus.OK, 1);

        return jsonResponse;

    }

    @GetMapping(value = "/")
    public JsonResponse getAllBills(){

        List<Bill> bills = billService.findAllBills();

        JsonResponse jsonResponse = new JsonResponse(true, "Bills", bills, HttpStatus.OK, 1);

        return jsonResponse;

    }

    @GetMapping(value = "/{id}")
    public JsonResponse getBill(@PathVariable(name = "id") String id){

        Bill bill = billService.getBill(id);

        JsonResponse jsonResponse = new JsonResponse(true, "Bills", bill, HttpStatus.OK, 1);
        return jsonResponse;
    }

    @GetMapping(value = "/due/{dueDate}")
    public JsonResponse getDueBills(@PathVariable(name = "dueDate") LocalDate dueDate){

        List<Bill> dueBills = billService.findDueBills(dueDate);
        JsonResponse jsonResponse = new JsonResponse(true, "Due Bills", dueBills, HttpStatus.OK, 1);
        return jsonResponse;
    }

}
