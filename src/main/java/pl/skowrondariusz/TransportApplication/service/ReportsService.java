package pl.skowrondariusz.TransportApplication.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.skowrondariusz.TransportApplication.model.MonthlyReport;
import pl.skowrondariusz.TransportApplication.model.Reports;
import pl.skowrondariusz.TransportApplication.model.Transit;
import pl.skowrondariusz.TransportApplication.repository.MonthlyReportRepository;
import pl.skowrondariusz.TransportApplication.repository.ReportsRepository;


import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ReportsService {

    @Autowired
    TransitService transitService;

    @Autowired
    ReportsRepository reportsRepository;


    MonthlyReportRepository monthlyReportRepository;

    @Autowired
    public void setMonthlyReportRepository(MonthlyReportRepository monthlyReportRepository){
        this.monthlyReportRepository = monthlyReportRepository;
    }


    public void addReports(Reports reports) {
        reportsRepository.save(reports);
    }


    public Long calculateTotalDistance(LocalDate startDate, LocalDate endDate) {
        double totalDistance = 0.0;
        List<Transit> transits = transitService.getTransits(startDate, endDate);
        for (Transit transit : transits) {
            if (transit.getDistance() != null) {
                totalDistance = totalDistance + transit.getDistance();
            }
        }
        return (long) totalDistance;
    }


    public Long calculateTotalPrice(LocalDate startDate, LocalDate endDate) {
        double totalPrice = 0.0;
        List<Transit> transits = transitService.getTransits(startDate, endDate);
        for (Transit transit : transits) {
            if (transit.getPrice() != null) {
                totalPrice = totalPrice + transit.getPrice();
            }
        }
        return (long) totalPrice;
    }

    public void addReports1(Reports reports) {
        reportsRepository.save(reports);
    }

    public void addMonthlyReports(MonthlyReport monthlyReport) {
        monthlyReportRepository.save(monthlyReport);
    }



    public List<Reports> findAllReports() {
        Iterable<Reports> all = reportsRepository.findAll();
        List<Reports> reports = convertReportsToList(all);
        return reports;
    }

    private List<Reports> convertReportsToList(Iterable<Reports> all) {
        List<Reports> reports = new ArrayList<>();
        for (Reports report : all) {
            reports.add(report);
        }
        return reports;
    }

    public Optional<Reports> getReport(Long id){
        return reportsRepository.findById(id);
    }




    public List<MonthlyReport> findAll() {
        Iterable<MonthlyReport> all = monthlyReportRepository.findAll();
        List<MonthlyReport> monthlyReports = convertMonthlyReportsToList(all);
        return monthlyReports;
    }

    private List<MonthlyReport> convertMonthlyReportsToList(Iterable<MonthlyReport> all) {
        List<MonthlyReport> monthlyReports = new ArrayList<>();
        for (MonthlyReport monthlyReport : all) {
            monthlyReports.add(monthlyReport);
        }
        return monthlyReports;
    }



}