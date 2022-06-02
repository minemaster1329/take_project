package com.example.take_project.services;

import com.example.take_project.dto.clientpackage.NewClientPackageDto;
import com.example.take_project.dto.clientpackage.UpdatedClientPackageDto;
import com.example.take_project.models.ClientPackage;

public interface ClientPackageServiceInterface extends BasicCRUDServiceInterfaceTyped<ClientPackage, NewClientPackageDto, UpdatedClientPackageDto>{
}
