//package com.company.ems_api.service;
//
//import com.company.ems_api.entities.Employee;
//import com.company.ems_api.exception.CustomException;
//import com.company.ems_api.repository.ManagerRepo;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import java.util.Optional;
//
//@Service
//public class ManagerService
//{
//    @Autowired
//    private ManagerRepo managerRepo;
//
////    public Employee addManager(Employee manager)
////    {
////        if (managerRepo.existsByMgrEmail(manager.getMgrEmail()))
////        {
////            throw new CustomException("Manager with email " + manager.getMgrEmail() + " already exists.");
////        }
////        return managerRepo.save(manager);
////    }
//
////    public Optional<Employee> findById(Long id)
////    {
////        return managerRepo.findById(id);
////    }
//
//    public void deleteManagerById(Long id)
//    {
//        if (!managerRepo.existsById(id))
//        {
//            throw  new IllegalArgumentException("Manager with ID " +id+ " does not exist.");
//        }
//        managerRepo.deleteManagerById(id);
//    }
//}
//
