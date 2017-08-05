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
    private final EstablishmentService establishmentService;
    private final UserService userService;

    @Autowired
    public EntryService(EntryRepository entryRepository, EstablishmentService establishmentService, UserService userService){
        this.entryRepository = entryRepository;
        this.establishmentService = establishmentService;
        this.userService = userService;
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

    public void addEntry(Entry entry, Long idEstablishment){
        entry.setUser(userService.getUserByDni(entry.getUser().getDni()));
        entry.setEstablishment(establishmentService.getEstablishmentById(idEstablishment));
        entry.setEntryDate(new Date());
        entryRepository.save(entry);
    }

    public void updateEntry(Entry entry){
        entryRepository.save(entry);
    }

    public void deleteEntry(Long idEntry){
        entryRepository.delete(idEntry);
    }
}
