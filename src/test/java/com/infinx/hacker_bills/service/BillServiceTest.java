package com.infinx.hacker_bills.service;

import com.infinx.hacker_bills.exception.BillException;
import com.infinx.hacker_bills.pojo.model.Bill;
import com.infinx.hacker_bills.repository.BillRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;


class BillServiceTest {

    @Mock
    private BillRepository billRepository;

    @InjectMocks
    private BillService billService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void saveBill() {

        Bill bill = new Bill(null, 1l, "puneet", LocalDate.of(2021,1,1), LocalDate.of(2021,1,2), 10.00, 20.00, 30.00);

        Mockito.when(billRepository.save(bill)).thenReturn(bill);

        Bill expectedBill = billService.saveBill(bill);

        assertEquals(bill, expectedBill);

    }

    @Test
    void getBill() throws BillException {

        String id = UUID.randomUUID().toString();
        Bill bill = new Bill(id, 1l, "puneet", LocalDate.of(2021,1,1), LocalDate.of(2021,1,2), 10.00, 20.00, 30.00);

        Mockito.when(billRepository.findById(id)).thenReturn(Optional.of(bill));

        Bill expectedBill = billService.findBill(id);

        assertEquals(bill, expectedBill);

    }

    @Test
    void findAllBills() {
        List<Bill> bills = new ArrayList<>();
        bills.add(new Bill());

        Mockito.when(billRepository.findAll()).thenReturn(bills);

        List<Bill> expectedBills = billService.findAllBills();

        assertEquals(bills, expectedBills);

    }

    @Test
    void findDueBills() {
        Bill bill1 = new Bill(null,1l, null, null, LocalDate.of(2021, 1, 1), null, null, null);
        Bill bill2 = new Bill(null,2l, null, null, LocalDate.of(2021, 1, 2), null, null, null);
        Bill bill3 = new Bill(null,3l, null, null, LocalDate.of(2021, 1, 3), null, null, null);
        Bill bill4 = new Bill(null,4l, null, null, LocalDate.of(2021, 1, 3), null, null, null);
        Bill bill5 = new Bill(null,5l, null, null, LocalDate.of(2021, 1, 4), null, null, null);

        List<Bill> dueBills = new ArrayList<>();
        dueBills.add(bill1);
        dueBills.add(bill2);

        LocalDate dueDate = LocalDate.of(2021, 1, 3);

        Mockito.when(billRepository.getDueBillsByDate(dueDate)).thenReturn(dueBills);

        List<Bill> expectedDueBills = billService.findDueBills(dueDate);

        assertEquals(dueBills, expectedDueBills);
    }
}