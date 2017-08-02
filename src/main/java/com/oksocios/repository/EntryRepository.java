package com.oksocios.repository;

import com.oksocios.model.Entry;
import org.springframework.data.repository.CrudRepository;

import java.util.Date;
import java.util.List;

public interface EntryRepository extends CrudRepository<Entry, Long> {
    List<Entry> findByEstablishment_Id(Long idEstablishment);
    List<Entry> findByEstablishment_IdAndEntryDateGreaterThan(Long idEstablishment, Date twoHoursAgo);
    List<Entry> findByUser_Id(Long idEstablishment);
}
