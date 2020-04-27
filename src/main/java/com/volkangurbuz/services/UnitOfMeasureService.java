package com.volkangurbuz.services;

import com.volkangurbuz.commands.UnitOfMeasureCommand;

import java.util.Set;

public interface UnitOfMeasureService {

  Set<UnitOfMeasureCommand> listAllUoms();
}
