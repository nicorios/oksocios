package com.oksocios.repository;

import com.oksocios.model.Entry;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by Envy on 14/5/2017.
 */
public interface EntryRepository extends CrudRepository<Entry, Long> {
    List<Entry> findByEstablishment_Id(Long idEstablishment);
    List<Entry> findByUser_Id(Long idEstablishment);
}
