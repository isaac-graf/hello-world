package com.sbna.vendorportal.dao;

import com.sbna.vendorportal.dto.CronSchedulerRun;
import com.sbna.vendorportal.dto.Status;

public interface CronSchedulerRunDao {

	CronSchedulerRun get(Long id);

	void save(CronSchedulerRun cronScheduler);

    void delete(Long id);

	CronSchedulerRun getMax(Long id,Status status);
}
