package com.cleitonspringboot.taskmanager.repository;



import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.cleitonspringboot.taskmanager.entity.LogEntry;


public interface LogEntryRepository extends MongoRepository<LogEntry, String> {

    List<LogEntry> findByUserEmail(String userEmail);

    List<LogEntry> findByAction(String action);
}
