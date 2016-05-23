package com.sbna.vendorportal.dao;

import com.sbna.vendorportal.dto.DeliveryPerformance;

public interface DeliveryPerformanceDao {

	
	DeliveryPerformance get(Long id);
	DeliveryPerformance get(String poNo);

	void save(DeliveryPerformance deliveryPerformance);

}
