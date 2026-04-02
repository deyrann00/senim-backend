package com.senim.backend;

import com.senim.backend.model.Article;
import com.senim.backend.model.BlacklistedFund;
import com.senim.backend.repository.ArticleRepository;
import com.senim.backend.repository.BlacklistedFundRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

@Component
public class DataSeeder implements CommandLineRunner {

    private final BlacklistedFundRepository fundRepository;
    private final ArticleRepository articleRepository;

    public DataSeeder(BlacklistedFundRepository fundRepository, ArticleRepository articleRepository) {
        this.fundRepository = fundRepository;
        this.articleRepository = articleRepository;
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

        // 2. SEED WIKI ARTICLES / SCAM SCHEMES (New!)
        if (articleRepository.count() == 0) {
            Article a1 = new Article();
            a1.setTitle("Phishing (фишинг)");
            a1.setScamCategory("Online scam");
            a1.setContent("Мошенники создают поддельные сайты банков или популярных сервисов и заставляют пользователей вводить свои данные. Пример: фейковый сайт банка, где человек вводит логин и пароль и теряет доступ к счету.");
            a1.setCreatedAt(LocalDateTime.now());

            Article a2 = new Article();
            a2.setTitle("Investment scam (инвестиционное мошенничество)");
            a2.setScamCategory("Finance scam");
            a2.setContent("Мошенники предлагают вложить деньги в «выгодные проекты» с высокой прибылью. Пример: фейковые сайты с инвестициями в крупные компании, например под видом KazMunayGas.");
            a2.setCreatedAt(LocalDateTime.now());

            Article a3 = new Article();
            a3.setTitle("Phone scam (телефонное мошенничество)");
            a3.setScamCategory("Social engineering");
            a3.setContent("Мошенники звонят и представляются сотрудниками банка или полиции. Пример: просят перевести деньги на «безопасный счет».");
            a3.setCreatedAt(LocalDateTime.now());

            Article a4 = new Article();
            a4.setTitle("Fake online shop (мошенничество с товарами)");
            a4.setScamCategory("E-commerce scam");
            a4.setContent("Мошенники продают товары по низкой цене и требуют предоплату. Пример: человек оплачивает товар, но ничего не получает.");
            a4.setCreatedAt(LocalDateTime.now());

            Article a5 = new Article();
            a5.setTitle("Social media scam (мошенничество в соцсетях)");
            a5.setScamCategory("Social media");
            a5.setContent("Мошенники используют Instagram, Telegram и WhatsApp для обмана. Пример: взломанный аккаунт просит деньги у друзей.");
            a5.setCreatedAt(LocalDateTime.now());

            articleRepository.saveAll(List.of(a1, a2, a3, a4, a5));
            System.out.println("✅ Wiki Articles Database Seeded!");
        }
    }
}