package com.valeevVA.tierlistBackend.service.tierlist;

import com.valeevVA.tierlistBackend.model.tierlist.Tierlist;
import com.valeevVA.tierlistBackend.repository.tierlist.TierlistRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TierlistServiceImpl implements TierlistService {
    @Autowired
    private TierlistRepository tierlistRepository;

    @Override
    public Tierlist saveTierlist(Tierlist tierlist) {
        return tierlistRepository.save(tierlist);
    }

    @Override
    public List<Tierlist> getTierlist() {
        return tierlistRepository.findAll();
    }
}
