package com.exodia.logserver.api.service;

import com.exodia.logserver.dto.request.ClearLogRequest;
import com.exodia.logserver.dto.request.CraftLogRequest;
import com.exodia.logserver.dto.request.HuntLogRequest;

public interface LogService {

	public void saveCraftLog(CraftLogRequest request);
	public void saveHuntLog(HuntLogRequest request);
	public void saveClearLog(ClearLogRequest request);
}
