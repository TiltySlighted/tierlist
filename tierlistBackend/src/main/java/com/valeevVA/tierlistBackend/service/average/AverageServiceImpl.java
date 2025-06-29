package com.valeevVA.tierlistBackend.service.average;

import com.valeevVA.tierlistBackend.model.average.Average;
import com.valeevVA.tierlistBackend.repository.average.AverageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AverageServiceImpl implements AverageService {
    @Autowired
    private AverageRepository averageRepository;

    @Override
    public Average saveAverage(Average average) {
        return averageRepository.save(average);
    }

    @Override
    public List<Average> getAverage() {
        return averageRepository.findAll();
    }
}
