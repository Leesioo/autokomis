package pl.sdacademy.autokomis.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import org.springframework.web.bind.annotation.*;
import pl.sdacademy.autokomis.dto.OperationDto;
import pl.sdacademy.autokomis.exceptions.WrongObjectException;
import pl.sdacademy.autokomis.model.Customer;
import pl.sdacademy.autokomis.model.Operation;
import pl.sdacademy.autokomis.model.Vehicle;
import pl.sdacademy.autokomis.services.CustomerService;
import pl.sdacademy.autokomis.services.OperationService;
import pl.sdacademy.autokomis.services.VehicleService;
import pl.sdacademy.autokomis.validator.OperationValidator;

import javax.validation.Valid;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/operation")
public class OperationController {

    private final OperationService operationService;
    private final VehicleService vehicleService;
    private final CustomerService customerService;
    private final OperationValidator operationValidator;

    @Autowired
    public OperationController(OperationService operationService, VehicleService vehicleService, CustomerService customerService, OperationValidator operationValidator) {
        this.operationService = operationService;
        this.vehicleService = vehicleService;
        this.customerService = customerService;
        this.operationValidator = operationValidator;
    }

    @PostMapping("/buy")
    public String buyVehicle(@Valid @ModelAttribute("operationDto") OperationDto operationDto, BindingResult bindingResult, Model model) {
       try {
           operationValidator.validate(operationDto, bindingResult);
           if (bindingResult.hasErrors()) {
               model.addAttribute(operationDto);
               model.addAttribute("customersList", customerService.findAll());
               return "operation/buy";
           }
           performOperation(operationDto, 1);
           return "redirect:/vehicle/toSaleList";
       } catch (Exception e) {
           model.addAttribute(operationDto);
           model.addAttribute("customersList", customerService.findAll());
           return "operation/buy";
       }
    }

    @PostMapping("/sell")
    public String sellVehicle(@ModelAttribute("operationDto") OperationDto operationDto, BindingResult bindingResult, Model model) {
        try {
            operationValidator.validate(operationDto, bindingResult);
            if (bindingResult.hasErrors()) {
                model.addAttribute(operationDto);
                model.addAttribute("customersList", customerService.findAll());
                return "operation/sell";
            }
            performOperation(operationDto, 2);
            return "redirect:/vehicle/soldList";
        } catch (Exception e) {
            model.addAttribute(operationDto);
            model.addAttribute("customersList", customerService.findAll());
            return "operation/sell";
        }
    }

    private void performOperation(@ModelAttribute("operationDto") OperationDto operationDto, Integer vehicleStatus) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date data = null;
        try {
            data = sdf.parse(operationDto.getOperationData());
        } catch (ParseException e) {
            e.printStackTrace();
        } finally {
            data = new Date();
        }

        Vehicle vehicle = vehicleService.update(new Vehicle(operationDto));
        vehicle.setStatus(vehicleStatus);
        Customer customer = customerService.update(new Customer(operationDto));
        Operation operation = new Operation(operationDto);
        operation.setData(data);
        operation.setCustomer(customer);
        operation.setVehicle(vehicle);

        vehicleService.save(vehicle);
        operationService.save(operation);
    }
}
