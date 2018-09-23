package pl.sdacademy.autokomis.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.sdacademy.autokomis.exceptions.WrongObjectException;
import pl.sdacademy.autokomis.model.Vehicle;
import pl.sdacademy.autokomis.services.VehicleService;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/vehicle")
public class VehicleController {

    private final VehicleService vehicleService;

    @Autowired
    public VehicleController(VehicleService vehicleService) {
        this.vehicleService = vehicleService;
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
        Optional<Vehicle> first = vehicleService.findById(id);
        if (!first.isPresent()) {
            throw new WrongObjectException("Nie ma takiego pojazdu");
        }
        if (first.get().getStatus() == 0) {
            first.get().setStatus(1);
            vehicleService.save(first.get());
        }
        return "redirect:/vehicle/toBuyList";
    }

    @GetMapping("/{id}/sell")
    public String sellVehicle(@PathVariable("id") Integer id, Model model) {
        Optional<Vehicle> first = vehicleService.findById(id);
        if (!first.isPresent()) {
            throw new WrongObjectException("Nie ma takiego pojazdu");
        }
        if (first.get().getStatus() == 1) {
            first.get().setStatus(2);
            vehicleService.save(first.get());
        }
        return "redirect:/vehicle/toSaleList";
    }

    @PostMapping("/save")
    public String saveVehicle(@ModelAttribute("vehicle") Vehicle vehicle) {
        Integer typ = vehicle.getStatus();
        vehicleService.save(vehicle);
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
