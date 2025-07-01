package com.valeevVA.tierlistBackend.service.tier;

import com.valeevVA.tierlistBackend.model.tier.Tier;

import java.util.List;

public interface TierService {
    public Tier saveTier(Tier tierlist);
    public List<Tier> getTier();
    List<Tier> getTiersByTierlistId(int tierlistId);
}
