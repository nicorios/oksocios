package com.oksocios.service;

import com.oksocios.model.Entry;
import com.oksocios.model.User;
import com.oksocios.repository.EntryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Calendar;
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

    public List<Entry> getAllEntriesByEstablishmentIdInLastMonth(Long dni, Long idEstablishment){
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.MONTH, -1);
        return entryRepository.findByUserDniAndEstablishment_IdAndEntryDateGreaterThan(dni, idEstablishment, cal.getTime());
    }

    public void addEntry(Entry entry, Long idEstablishment){
        entry.setUser(userService.getUserByDni(entry.getUser().getDni()));
        entry.setEstablishment(establishmentService.getEstablishmentById(idEstablishment));
        entry.setEntryDate(new Date());
        entryRepository.save(entry);
    }

    public int[] getEntriesFromMonth(int month, Long idEstablishment){
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.MONTH, month);
        cal.set(Calendar.DAY_OF_MONTH, 1);
        Date startDate = cal.getTime();
        cal.set(Calendar.MONTH, month +1);
        Date endDate = cal.getTime();
        List<Entry> entries = entryRepository.findByEstablishmentIdAndEntryDateIsAfterAndEntryDateIsBefore(idEstablishment, startDate, endDate);
        return this.calculateEntriesFromMonth(entries);
    }

    public int[] calculateEntriesFromMonth(List<Entry> entries){
        int[] users = new int[31];
        Calendar cal = Calendar.getInstance();
        for(Entry entry : entries){
            cal.setTime(entry.getEntryDate());
            users[cal.get(Calendar.DAY_OF_MONTH) -1]++;
        }
        return users;
    }

    public int[] getEntriesLastWeek(Long idEstablishment){
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DAY_OF_MONTH, 1);
        Date endDate = cal.getTime();
        cal.add(Calendar.DAY_OF_MONTH, -7);
        Date startDate = cal.getTime();
        List<Entry> entries = entryRepository.findByEstablishmentIdAndEntryDateIsAfterAndEntryDateIsBefore(idEstablishment, startDate, endDate);
        return this.calculateEntriesFromLastWeek(entries);
    }

    public int[] calculateEntriesFromLastWeek(List<Entry> entries){
        int[] users = new int[15];
        User user;
        Calendar cal = Calendar.getInstance();
        int today = cal.get(Calendar.DAY_OF_WEEK);
        for(Entry entry : entries){
            user = entry.getUser();
            if(user.getGender() != null){
                cal.setTime(entry.getEntryDate());
                int entryDay = cal.get(Calendar.DAY_OF_WEEK);
                int diff = Math.abs(6 - today);
                if(entryDay > today && user.getGender()) users[entryDay+diff]++;
                if(entryDay <= today && user.getGender()) users[entryDay-diff]++;
                if(entryDay > today && !user.getGender()) users[entryDay+diff +7]++;
                if(entryDay <= today && !user.getGender()) users[entryDay-diff +7]++;
            }else{
                users[14]++;
            }

        }
        return users;
    }

    public void updateEntry(Entry entry){
        entryRepository.save(entry);
    }

    public void deleteEntry(Long idEntry){
        entryRepository.delete(idEntry);
    }
}
