package pl.sdacademy.autokomis.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.sdacademy.autokomis.dto.OperationDto;
import pl.sdacademy.autokomis.exceptions.WrongObjectException;
import pl.sdacademy.autokomis.model.Customer;
import pl.sdacademy.autokomis.model.Operation;
import pl.sdacademy.autokomis.model.Vehicle;
import pl.sdacademy.autokomis.services.CustomerService;
import pl.sdacademy.autokomis.services.VehicleService;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/vehicle")
public class VehicleController {

    private final VehicleService vehicleService;
    private final CustomerService customerService;

    @Autowired
    public VehicleController(VehicleService vehicleService, CustomerService customerService) {
        this.vehicleService = vehicleService;
        this.customerService = customerService;
    }

    @GetMapping("/toBuyList")
    public String showToBuyList(Model model) {
        List<Vehicle> vehicles = vehicleService.findAllByStatus(0);
        model.addAttribute("vehicles", vehicles);
        return "vehicle/toBuyList";
    }

    @GetMapping("/toSaleList")
    public String showToSaleList(Model model) {
        List<Vehicle> vehicles = vehicleService.findAllByStatus(1);
        model.addAttribute("vehicles", vehicles);
        return "vehicle/toSaleList";
    }

    @GetMapping("/soldList")
    public String showSoldList(Model model) {
        List<Vehicle> vehicles = vehicleService.findAllByStatus(2);
        model.addAttribute("vehicles", vehicles);
        return "vehicle/soldList";
    }

    @GetMapping("/add")
    public String showAddNewVehicle(Model model) {
        model.addAttribute("vehicle", new Vehicle());
        return "vehicle/edit";
    }

    @GetMapping("/{id}/edit")
    public String showEditVehicles(@PathVariable("id") Integer id, Model model) {
        Optional<Vehicle> first = vehicleService.findById(id);
        if (!first.isPresent()) {
            throw new WrongObjectException("Nie ma takiego pojazdu");
        }
        model.addAttribute("vehicle", first.get());
        return "vehicle/edit";
    }

    @GetMapping("/{id}/buy")
    public String buyVehicle(@PathVariable("id") Integer id, Model model) {
        return takeVehicle(id, model, 1);
    }

    @GetMapping("/{id}/lend")
    public String lendVehicle(@PathVariable("id") Integer id, Model model) {
        return takeVehicle(id, model, 3);
    }

    private String takeVehicle(@PathVariable("id") Integer id, Model model, int operationType) {
        Optional<Vehicle> vehicle = vehicleService.findById(id);
        if (!vehicle.isPresent()) {
            throw new WrongObjectException("Nie ma takiego pojazdu");
        }
        Operation operation = new Operation();
        operation.setType(operationType);
        OperationDto operationDto = new OperationDto(vehicle.get(), new Customer(), operation);
        model.addAttribute("operationDto", operationDto);
        model.addAttribute("customersList", customerService.findAll());
        if (operationType == 2) {
            return "operation/sell";
        } else {
            return "operation/buy";
        }
    }

    @GetMapping("/{id}/sell")
    public String sellVehicle(@PathVariable("id") Integer id, Model model) {
        return takeVehicle(id, model, 2);
    }

    @PostMapping("/save")
    public String saveVehicle(@ModelAttribute("vehicle") Vehicle vehicle) {
        Integer typ = vehicle.getStatus();
        try {
            vehicleService.save(vehicle);
        } catch (Exception e){
            throw new WrongObjectException("Coś nie tak");
        }
        switch (typ) {
            case 0:
                return "redirect:/vehicle/toBuyList";
            case 1:
                return "redirect:/vehicle/toSaleList";
            case 2:
                return "redirect:/vehicle/soldList";
            default:
                return "redirect:/";
        }
    }

    @GetMapping("/{id}/delete")
    public String deleteVehicle(@PathVariable("id") Integer id, Model model) {
        vehicleService.deleteById(id);
        return "redirect:/vehicle/list";
    }
}
