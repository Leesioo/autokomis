package pl.sdacademy.autokomis.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.sdacademy.autokomis.dto.OperationDto;
import pl.sdacademy.autokomis.exceptions.WrongObjectException;
import pl.sdacademy.autokomis.model.Customer;
import pl.sdacademy.autokomis.model.Operation;
import pl.sdacademy.autokomis.model.Vehicle;
import pl.sdacademy.autokomis.services.CustomerService;
import pl.sdacademy.autokomis.services.OperationService;
import pl.sdacademy.autokomis.services.VehicleService;

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

    @Autowired
    public OperationController(OperationService operationService, VehicleService vehicleService, CustomerService customerService) {
        this.operationService = operationService;
        this.vehicleService = vehicleService;
        this.customerService = customerService;
    }

    @PostMapping("/buy")
    public String buyVehicle(@ModelAttribute("operationDto") OperationDto operationDto) {
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
        vehicle.setStatus(1);
        Customer customer = customerService.update(new Customer(operationDto));
        Operation operation = new Operation(operationDto);
        operation.setData(data);
        operation.setCustomer(customer);
        operation.setVehicle(vehicle);

        vehicleService.save(vehicle);
        customerService.save(customer);
        operationService.save(operation);

        return "redirect:/vehicle/toSaleList";
    }
}
