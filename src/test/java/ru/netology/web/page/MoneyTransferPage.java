package ru.netology.web.page;

import ru.netology.web.data.DataHelper;
import ru.netology.web.page.DashboardPage;

import static com.codeborne.selenide.Selenide.$;

public class MoneyTransferPage {
    public DashboardPage transferMoney(DataHelper.TransferCard info, String amount) {
        $("[data-test-id=from] input").setValue(info.getNumberCard());
        $("[data-test-id=amount] input").setValue(amount);
        $("[data-test-id=action-transfer]").click();
        return new DashboardPage();
    }

}
