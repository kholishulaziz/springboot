package com.springboot.service;

import com.springboot.model.Miscellaneous;
import com.springboot.repository.MiscellaneousRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MiscellaneousServiceImpl implements MiscellaneousService {

    @Autowired
    private MiscellaneousRepository miscellaneousRepository;

    @Override
    public Miscellaneous addMiscellaneous(Miscellaneous miscellaneous) {
        return miscellaneousRepository.save(miscellaneous);
    }

    @Override
    public List<Miscellaneous> getMiscellaneous() {
        return miscellaneousRepository.findAll();
    }

}
