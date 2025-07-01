package com.valeevVA.tierlistBackend.service.tier;

import com.valeevVA.tierlistBackend.model.tier.Tier;
import com.valeevVA.tierlistBackend.repository.tier.TierRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TierServiceImpl implements TierService {
    @Autowired
    private TierRepository tierRepository;

    @Override
    public Tier saveTier(Tier tier) {
        return tierRepository.save(tier);
    }

    @Override
    public List<Tier> getTier() {
        return tierRepository.findAll();
    }

    @Override
    public List<Tier> getTiersByTierlistId(int tierlistId) {
        return tierRepository.findByTierlistId(tierlistId);
    }
}
