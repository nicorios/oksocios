package com.oksocios.service;

import com.oksocios.model.Entry;
import com.oksocios.repository.EntryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class EntryService {

    private final EntryRepository entryRepository;

    @Autowired
    public EntryService(EntryRepository entryRepository){
        this.entryRepository = entryRepository;
    }

    public List<Entry> getAllEntries(){
        List<Entry> entries = new ArrayList<>();
        entryRepository.findAll().forEach(entries :: add);
        return entries;
    }

    public List<Entry> getAllEntriesByEstablishmentId(Long idEstablishment){
        return entryRepository.findByEstablishment_Id(idEstablishment);
    }

    public List<Entry> getAllEntriesByUserId(Long idUser){
        return entryRepository.findByUser_Id(idUser);
    }

    public List<Entry> getAllEntriesByEstablishmentIdInLastTwoHours(Long idEstablishment){
        return entryRepository.findByEstablishment_IdAndEntryDateGreaterThan(idEstablishment, new Date(System.currentTimeMillis() - (2 * 60 * 60 * 1000)));
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
