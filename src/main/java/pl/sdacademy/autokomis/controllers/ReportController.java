package pl.sdacademy.autokomis.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.sdacademy.autokomis.dto.BuysReportDto;
import pl.sdacademy.autokomis.dto.OperationDto;
import pl.sdacademy.autokomis.dto.SalesReportDto;
import pl.sdacademy.autokomis.exceptions.WrongObjectException;
import pl.sdacademy.autokomis.model.Operation;
import pl.sdacademy.autokomis.services.CustomerService;
import pl.sdacademy.autokomis.services.OperationService;
import pl.sdacademy.autokomis.services.VehicleService;
import pl.sdacademy.autokomis.validator.OperationValidator;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/report")
public class ReportController {

    private final OperationService operationService;
    private final VehicleService vehicleService;
    private final CustomerService customerService;
    private final OperationValidator operationValidator;

    @Autowired
    public ReportController(OperationService operationService, VehicleService vehicleService, CustomerService customerService, OperationValidator operationValidator) {
        this.operationService = operationService;
        this.vehicleService = vehicleService;
        this.customerService = customerService;
        this.operationValidator = operationValidator;
    }

    @GetMapping("/soldList")
    public String showSoldList(Model model, Pageable pageable) {
        List<SalesReportDto> report = new ArrayList<>();
        List<Operation> soldOperations = operationService.findAllByType(2);
        soldOperations.forEach(o -> report.add(new SalesReportDto(o, operationService)));
        model.addAttribute("soldList", report);
        return "report/soldList";
    }

    @GetMapping("/sold/{id}/details")
    public String showReportDetails(@PathVariable("id") Integer id, Model model) {
        Optional<Operation> first = operationService.findById(id);
        if (!first.isPresent()) {
            throw new WrongObjectException("Nie ma takiej operacji");
        }
        model.addAttribute("operation", new SalesReportDto(first.get(), operationService));
        return "report/edit";
    }

    @GetMapping("/buyList")
    public String showBuyList(Model model, Pageable pageable) {
        List<BuysReportDto> report = new ArrayList<>();
        List<Operation> buyOperations = operationService.findAllByType(1);
        buyOperations.forEach(o -> report.add(new BuysReportDto(o, operationService)));
        model.addAttribute("buyList", report);
        return "report/buyList";
    }

    @GetMapping("/buy/{id}/details")
    public String showBuyReportDetails(@PathVariable("id") Integer id, Model model) {
        Optional<Operation> first = operationService.findById(id);
        if (!first.isPresent()) {
            throw new WrongObjectException("Nie ma takiej operacji");
        }
        model.addAttribute("operation", new SalesReportDto(first.get(), operationService));
        return "report/edit";
    }




}
