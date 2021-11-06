package com.infinx.hacker_bills.repository;

import com.infinx.hacker_bills.pojo.model.Bill;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface BillRepository extends JpaRepository<Bill, String> {

    @Query(value = "select * from bills where due_date<?1", nativeQuery = true)
    public List<Bill> getDueBillsByDate(LocalDate dueDate);

}
