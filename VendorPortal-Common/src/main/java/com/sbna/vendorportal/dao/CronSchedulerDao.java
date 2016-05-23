package com.sbna.vendorportal.dao;

import com.sbna.vendorportal.dto.CronScheduler;

public interface CronSchedulerDao {
	CronScheduler get(Long id);

	CronScheduler get(String cronName);

    void save(CronScheduler cronScheduler);

    void delete(Long id);


}
