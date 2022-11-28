package ru.netology.web.test;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.netology.web.data.DataHelper;
import ru.netology.web.page.*;

import static com.codeborne.selenide.Selenide.open;

class MoneyTransferTest {
    @Test
    void shouldTransferMoneyBetweenOwnCardsV1() {
        open("http://localhost:9999");
        var loginPage = new LoginPage();
        var authInfo = DataHelper.getAuthInfo();
        var verificationPage = loginPage.validLogin(authInfo);
        var verificationCode = DataHelper.getVerificationCodeFor(authInfo);
        var dashboardPage = verificationPage.validVerify((verificationCode));
        var firstCardBalance = dashboardPage.getFirstCardBalance();
        var secondCardBalance = dashboardPage.getSecondCardBalance();
        dashboardPage.addMoneyToFirstCard();
        var moneyTransferPage = new MoneyTransferPage();
        var transferInfo = DataHelper.getOtherTransferCard();
        var amount = 200;
        var transferPage = moneyTransferPage.transferMoney(transferInfo, String.valueOf(amount));
        var expectedFirstCardBalance = firstCardBalance + amount;
        var expectedSecondCardBalance = secondCardBalance - amount;
        var actualFirstCardBalance = dashboardPage.getFirstCardBalance();
        var actualSecondCardBalance = dashboardPage.getSecondCardBalance();
        Assertions.assertEquals(expectedFirstCardBalance, actualFirstCardBalance);
        Assertions.assertEquals(expectedSecondCardBalance, actualSecondCardBalance);
    }

    @Test
    void shouldTransferMoneyFromCard2() {
        open("http://localhost:9999");
        var loginPage = new LoginPage();
        var authInfo = DataHelper.getAuthInfo();
        var verificationPage = loginPage.validLogin(authInfo);
        var verificationCode = DataHelper.getVerificationCodeFor(authInfo);
        var dashboardPage = verificationPage.validVerify((verificationCode));
        var firstCardBalance = dashboardPage.getFirstCardBalance();
        var secondCardBalance = dashboardPage.getSecondCardBalance();
        dashboardPage.addMoneyToSecondCard();
        var moneyTransferPage = new MoneyTransferPage();
        var transferInfo = DataHelper.getTransferCard();
        var amount = 200;
        var transferPage = moneyTransferPage.transferMoney(transferInfo, String.valueOf(amount));
        var expectedFirstCardBalance = firstCardBalance - amount;
        var expectedSecondCardBalance = secondCardBalance + amount;
        var actualFirstCardBalance = dashboardPage.getFirstCardBalance();
        var actualSecondCardBalance = dashboardPage.getSecondCardBalance();
        Assertions.assertEquals(expectedFirstCardBalance, actualFirstCardBalance);
        Assertions.assertEquals(expectedSecondCardBalance, actualSecondCardBalance);
    }
}

