package com.valeevVA.tierlistBackend.service.tierlist;

import com.valeevVA.tierlistBackend.model.tierlist.Tierlist;
import java.util.List;

public interface TierlistService {
    public Tierlist saveTierlist(Tierlist tierlist);
    public List<Tierlist> getTierlist();
}
