package com.oksocios.service;

import com.oksocios.model.Entry;
import com.oksocios.repository.EntryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Envy on 14/5/2017.
 */
@Service
public class EntryService {

    @Autowired
    private EntryRepository entryRepository;

    public List<Entry> getAllEntries(){
        List<Entry> entries = new ArrayList<>();
        entryRepository.findAll().forEach(entries :: add);
        return entries;
    }

    public List<Entry> getAllEntriesByEstablishmentId(Long idEstablishment){
        List<Entry> entries = new ArrayList<>();
        entryRepository.findByEstablishment_Id(idEstablishment).forEach(entries :: add);
        return entries;
    }

    public List<Entry> getAllEntriesByUserId(Long idUser){
        List<Entry> entries = new ArrayList<>();
        entryRepository.findByUser_Id(idUser).forEach(entries :: add);
        return entries;
    }

    public void addEntry(Entry entry){
        entryRepository.save(entry);
    }

    public void updateEntry(Entry entry){
        entryRepository.save(entry);
    }

    public void deleteEntry(Long idEntry){
        entryRepository.delete(idEntry);
    }
}
