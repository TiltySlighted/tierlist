package com.valeevVA.tierlistBackend.service.average;

import com.valeevVA.tierlistBackend.model.average.Average;

import java.util.List;

public interface AverageService {
    public Average saveAverage(Average average);
    public List<Average> getAverage();
}
