package com.acid.Project.Catalog;

import com.acid.Project.Catalog.domain.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
// TODO: QUITAR BUCLES Y GUARDAR 1 POR 1 CADA ELEMENTO
@Component
public class DataSeeder implements ApplicationRunner {

    @Autowired
    private ShirtRepository shirtRepository;

    private static final Logger log = LoggerFactory.getLogger(DataSeeder.class);

    private static final String[] LINES = new String[]{
            "1 V-NECH BASIC SHIRT 100 S: 4 / M:9 / L:0",
            "2 CONTRASTING FABRIC T-SHIRT 50 S: 35 / M:9 / L:9",
            "3 RAISED PRINT T-SHIRT 80 S: 20 / M:2 / L:20",
            "4 PLEATED T-SHIRT 3 S: 25 / M:30 / L:10",
            "5 CONTRASTING LACE T-SHIRT 650 S: 0 / M:1 / L:0",
            "6 SLOGAN T-SHIRT 20 S: 9 / M:2 / L:5"
    };


    private static final Pattern LINE_PATTERN =
            Pattern.compile("^\\s*(\\d+)\\s+(.+?)\\s+(\\d+)\\s+(.*)\\s*$");

    private static final Pattern STOCK_TOKEN =
            Pattern.compile("\\b([SML])\\s*:\\s*(\\d+)\\b", Pattern.CASE_INSENSITIVE);

    @Override
    public void run(ApplicationArguments args) {
        for (String line : LINES) {
            try {
                Parsed p = parseLine(line);

                Shirt shirt = Shirt.create(p.id, p.name, p.sales, p.stock);

                shirtRepository.save(shirt);
                log.info("Upsert OK id={} name='{}' sales={} stock={}", p.id, p.name, p.sales, p.stock);
            } catch (Exception e) {
                log.error("Error parseando/sembrando línea: \"{}\" -> {}", line, e.getMessage());
            }
        }
    }

    private Parsed parseLine(String line) {
        Matcher m = LINE_PATTERN.matcher(line);
        if (!m.matches()) {
            throw new IllegalArgumentException("Formato inválido");
        }
        Long id = Long.valueOf(m.group(1));
        String name = m.group(2).trim();
        int sales = Integer.parseInt(m.group(3).trim());
        String stockSpec = m.group(4);

        Map<String, Integer> stock = new HashMap<>();
        Matcher st = STOCK_TOKEN.matcher(stockSpec);
        while (st.find()) {
            String sizeToken = st.group(1).toUpperCase();
            int qty = Integer.parseInt(st.group(2));
            stock.put(sizeToken, qty);
//            switch (sizeToken) {
//                case "S" -> stock.put(Size.S, Quantity.from(qty));
//                case "M" -> stock.put(Size.M, Quantity.from(qty));
//                case "L" -> stock.put(Size.L, Quantity.from(qty));
//            }
        }

//        for (Size sz : new Size[]{Size.S, Size.M, Size.L}) {
//            stock.putIfAbsent(sz, Quantity.from(0));
//        }

        return new Parsed(id, name, sales, stock);
    }

    private record Parsed(Long id, String name, int sales, Map<String, Integer> stock) {}
}
