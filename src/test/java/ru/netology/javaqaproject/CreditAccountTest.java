package ru.netology.javaqaproject;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class CreditAccountTest {
    @Test
    public void shouldAddToPositiveBalance() { //пополнение счета
        CreditAccount account = new CreditAccount(
                0,
                5_000,
                15
        );
        account.add(3_000);
        Assertions.assertEquals(3_000, account.getBalance());
    }

    @Test
    public void shouldAddToPositiveBalance2() { //пополнение счета
        CreditAccount account = new CreditAccount(
                3_000,
                5_000,
                15
        );
        account.add(3_000);
        Assertions.assertEquals(6_000, account.getBalance());
    }

    @Test
    public void shouldLimit() { //сумма литима
        CreditAccount account = new CreditAccount(
                0,
                75_000,
                15
        );
        Assertions.assertEquals(75_000, account.getCreditLimit());
    }

    @Test
    public void shouldRate() { //ставка
        CreditAccount account = new CreditAccount(
                0,
                5_000,
                25
        );
        Assertions.assertEquals(25, account.getRate());
    }

    @Test
    public void tetsException1() { // тест на исключительные ситуации
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            CreditAccount accounts = new CreditAccount(
                    1000,
                    5_000,
                    -15
            );
        });
    }

    @Test
    public void tetsException2() { // тест на исключительные ситуации
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            CreditAccount accounts = new CreditAccount(
                    1000,
                    -5_000,
                    15
            );
        });
    }

    @Test
    public void tetsException3() { // тест на исключительные ситуации
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            CreditAccount accounts = new CreditAccount(
                    -1000,
                    5_000,
                    15
            );
        });
    }

    @Test
    public void tetsException4() { // тест на исключительные ситуации
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            CreditAccount accounts = new CreditAccount(
                    -1000,
                    -5_000,
                    -15
            );
        });
    }

    @Test
    public void shouldPayToPositiveBalance1() { // тест на покупку в пределах общего (доступного) лимита
        CreditAccount account = new CreditAccount(
                1_000,
                5_000,
                15
        );
        account.pay(3_000);
        Assertions.assertEquals(-2_000, account.getBalance());
    }

    @Test
    public void shouldPayToAllBalance() { // тест на покупку на сумму общего (достуного) лимита
        CreditAccount account = new CreditAccount(
                1_000,
                5_000,
                15
        );
        account.pay(6_000);
        Assertions.assertEquals(-5_000, account.getBalance());
    }

    @Test
    public void shouldPayToMoreAllBalance() { // тест на покупку на сумму свыше общего (достуного) лимита
        CreditAccount account = new CreditAccount(
                1_000,
                5_000,
                15
        );
        account.pay(7_000);
        Assertions.assertEquals(1_000, account.getBalance());
    }

    @Test
    public void shouldPayToIncorrectAmount1() { // тест на пополнение счета на некорректную сумму
        CreditAccount account = new CreditAccount(
                1_000,
                5_000,
                15
        );
        account.add(-1000);
        Assertions.assertEquals(1_000, account.getBalance());
    }

    @Test
    public void shouldPayToIncorrectAmount2() { // тест на пополнение счета на некорректную сумму
        CreditAccount account = new CreditAccount(
                1_000,
                5_000,
                15
        );
        account.add(-1);
        Assertions.assertEquals(1_000, account.getBalance());
    }

    @Test
    public void shouldAddToPositiveBalance3() { //тест на пополнение счета на 0
        CreditAccount account = new CreditAccount(
                1_000,
                5_000,
                15
        );
        account.add(0);
        Assertions.assertEquals(1_000, account.getBalance());
    }

    @Test
    public void shouldAddToPositiveBalance4() { //тест на пополнение счета на корректную сумму
        CreditAccount account = new CreditAccount(
                1_000,
                5_000,
                15
        );
        account.add(4_000);
        Assertions.assertEquals(5_000, account.getBalance());
    }

    @Test
    public void shouldAddToPositiveBalance5() { //тест на пополнение счета на 1
        CreditAccount account = new CreditAccount(
                0,
                5_000,
                15
        );
        account.add(1);
        Assertions.assertEquals(1, account.getBalance());
    }

    @Test
    public void shouldAddToNegativeBalance1() { //тест на пополнение счета на (погашение кредита)
        CreditAccount account = new CreditAccount(
                0,
                5_000,
                15
        );
        account.pay(4_500);
        account.add(3_200);
        Assertions.assertEquals(-1_300, account.getBalance());
    }

    @Test
    public void shouldAddToNegativeBalance2() { //тест на пополнение счета на (погашение кредита)
        CreditAccount account = new CreditAccount(
                0,
                5_000,
                15
        );
        account.pay(4_500);
        account.add(7_200);
        Assertions.assertEquals(2_700, account.getBalance());
    }

    @Test
    public void shouldCalculationOfInterest1() { // тест на расчет процентов (задолженности по кредтиту нет)
        CreditAccount account = new CreditAccount(
                75_000,
                5_000,
                15
        );
        Assertions.assertEquals(0, account.yearChange());
    }

    @Test
    public void shouldCalculationOfInterest2() { // тест на расчет процентов (задолженность по кредтиту 5_000)
        CreditAccount account = new CreditAccount(
                0,
                5_000,
                15
        );
        account.pay(5_000);
        Assertions.assertEquals(-750, account.yearChange());
    }

    @Test
    public void shouldCalculationOfInterest3() { // тест на расчет процентов (задолженность по кредтиту 0)
        CreditAccount account = new CreditAccount(
                0,
                5_000,
                15
        );
        Assertions.assertEquals(0, account.yearChange());
    }

    @Test
    public void shouldCalculationOfInterest4() { // тест на расчет процентов (задолженность по кредтиту 1)
        CreditAccount account = new CreditAccount(
                0,
                5_000,
                100
        );
        account.pay(1);
        Assertions.assertEquals(-1, account.yearChange());
    }

    @Test
    public void shouldCalculationOfInterest5() { // тест на расчет процентов (задолженность по кредтиту 4_999)
        CreditAccount account = new CreditAccount(
                0,
                5_000,
                100
        );
        account.pay(4_999);

        Assertions.assertEquals(-4_999, account.yearChange());
    }

}
