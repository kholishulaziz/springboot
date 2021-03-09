package com.springboot.service;

import com.springboot.model.Miscellaneous;

import java.util.List;

public interface MiscellaneousService {

    Miscellaneous addMiscellaneous(Miscellaneous miscellaneous);

    List<Miscellaneous> getMiscellaneous();

}
