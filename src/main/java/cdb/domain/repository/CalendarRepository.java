package cdb.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import cdb.domain.entity.CalendarEntity;

public interface CalendarRepository extends JpaRepository<CalendarEntity, Long> {

}
