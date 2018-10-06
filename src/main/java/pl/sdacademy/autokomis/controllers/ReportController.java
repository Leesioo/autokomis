package pl.sdacademy.autokomis.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.sdacademy.autokomis.dto.*;
import pl.sdacademy.autokomis.exceptions.WrongObjectException;
import pl.sdacademy.autokomis.model.Operation;
import pl.sdacademy.autokomis.services.CustomerService;
import pl.sdacademy.autokomis.services.OperationService;
import pl.sdacademy.autokomis.services.VehicleService;
import pl.sdacademy.autokomis.validator.OperationValidator;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
        Page<Operation> soldOperations = operationService.findAllByType(2, pageable);
        soldOperations.forEach(o -> report.add(new SalesReportDto(o, operationService)));
        PageImpl page = new PageImpl(report, pageable, soldOperations.getTotalElements());
        model.addAttribute("soldList", report);
        model.addAttribute("soldSummary", getSoldSummary(report));
        model.addAttribute("totalPages" , page.getTotalPages());
        model.addAttribute("pageSize", page.getPageable().getPageSize());
        model.addAttribute("currentPage", page.getPageable().getPageNumber());
        return "report/soldList";
    }

    private SaleSummaryDto getSoldSummary(List<SalesReportDto> report) {
        SaleSummaryDto saleSummaryDto = new SaleSummaryDto();
        saleSummaryDto.setCommission(getCommissionSummary(report));
        saleSummaryDto.setPurchase(getPurchaseSummary(report));
        saleSummaryDto.setSale(getSaleSummary(report));
        return saleSummaryDto;
    }

    private BigDecimal getSaleSummary(List<SalesReportDto> report) {
        BigDecimal sale = report.stream()
                .map(s -> s.getOperationValue())
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        return sale;
    }

    private BigDecimal getPurchaseSummary(List<SalesReportDto> report) {
        BigDecimal purchase = report.stream()
                .map(s -> s.getCenaZakupu())
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        return purchase;
    }

    private BigDecimal getCommissionSummary(List<SalesReportDto> report) {
        BigDecimal commission = report.stream()
                .map(s -> s.getProwizja())
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        return commission;
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
        Page<Operation> buyOperations = operationService.findAllByType(1, pageable);
        buyOperations.forEach(o -> report.add(new BuysReportDto(o, operationService)));
        model.addAttribute("buySummary", getBuySummary(report));
        PageImpl page = new PageImpl(report, pageable, buyOperations.getTotalElements());
        model.addAttribute("buyList", page);
        model.addAttribute("totalPages" , page.getTotalPages());
        model.addAttribute("pageSize", page.getPageable().getPageSize());
        model.addAttribute("currentPage", page.getPageable().getPageNumber());
        return "report/buyList";
    }

    private BuySummaryDto getBuySummary(List<BuysReportDto> report) {
        BuySummaryDto buySummaryDto = new BuySummaryDto();
        buySummaryDto.setPurchase(this.getPurchaseSummaryForPurchaseReport(report));
        return buySummaryDto;
    }

    private BigDecimal getPurchaseSummaryForPurchaseReport(List<BuysReportDto> report) {
        BigDecimal sale = report.stream()
                .map(s -> s.getOperationValue())
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        return sale;
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
