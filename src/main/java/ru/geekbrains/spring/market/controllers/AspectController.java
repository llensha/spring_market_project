package ru.geekbrains.spring.market.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.geekbrains.spring.market.AppAspect;

import javax.annotation.PostConstruct;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/statistics")
public class AspectController {

    private final AppAspect aspect;
    private Map<String, List<Map.Entry<String, Long>>> statisticsMap;

    @PostConstruct
    public void init() {
        statisticsMap = new ConcurrentHashMap<>();
    }

    @GetMapping
    public Map<String, List<Map.Entry<String, Long>>> showStatistics() {
        statisticsMap.put("countMethods",
                aspect.getCountMethods().entrySet().stream().sorted(Map.Entry.comparingByValue(Comparator.reverseOrder())).collect(Collectors.toList()));
        statisticsMap.put("countControllers",
                aspect.getCountControllers().entrySet().stream().sorted(Map.Entry.comparingByValue(Comparator.reverseOrder())).collect(Collectors.toList()));
        return statisticsMap;
    }

}
