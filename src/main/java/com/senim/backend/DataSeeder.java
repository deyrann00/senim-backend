package com.senim.backend;

import com.senim.backend.model.BlacklistedFund;
import com.senim.backend.repository.BlacklistedFundRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataSeeder implements CommandLineRunner {

    private final BlacklistedFundRepository fundRepository;

    public DataSeeder(BlacklistedFundRepository fundRepository) {
        this.fundRepository = fundRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        // Only insert if the table is empty
        if (fundRepository.count() == 0) {

            BlacklistedFund f1 = new BlacklistedFund();
            f1.setName("Fake charity Instagram (Шымкент кейс)");
            f1.setDescription("Мошенник создавал страницу и собирал деньги якобы на лечение детей");
            f1.setReason("Фейковые медицинские документы");
            f1.setStatus("confirmed fraud");

            BlacklistedFund f2 = new BlacklistedFund();
            f2.setName("Фейковые сборы на международную помощь (дело Перизат Кайрат)");
            f2.setDescription("Сбор денег якобы на помощь пострадавшим");
            f2.setReason("Средства использовались мошеннически");
            f2.setStatus("confirmed fraud");

            BlacklistedFund f3 = new BlacklistedFund();
            f3.setName("Псевдо-благотворительные страницы без регистрации");
            f3.setDescription("Страницы без документов и официальной регистрации");
            f3.setReason("Отсутствие отчетности и прозрачности");
            f3.setStatus("under review");

            BlacklistedFund f4 = new BlacklistedFund();
            f4.setName("Сборы через «личные истории»");
            f4.setDescription("Мошенники публикуют эмоциональные истории и просят деньги");
            f4.setReason("Нет подтверждающих документов");
            f4.setStatus("suspicious");

            fundRepository.saveAll(java.util.List.of(f1, f2, f3, f4));
            System.out.println("✅ Blacklist Database Seeded!");
        }
    }
}