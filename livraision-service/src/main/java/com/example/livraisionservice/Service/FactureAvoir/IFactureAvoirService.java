package com.example.livraisionservice.Service.FactureAvoir;

import com.example.livraisionservice.Entities.FactureAvoir;

import java.util.List;

public interface IFactureAvoirService {
    public FactureAvoir addFactureAvoir(FactureAvoir f);
    public void deleteFactureAvoir(int id);
    public FactureAvoir getFactureAvoirById(int id);
    public List<FactureAvoir> getAllFactureAvoir();
    public List<FactureAvoir> SearchMultiple3(String key);
}
