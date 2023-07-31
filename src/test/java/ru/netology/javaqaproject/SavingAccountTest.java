package ru.netology.javaqaproject;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class SavingAccountTest {

    @Test
    public void shouldAddLessThanMaxBalance() { //Тест на успешное выполнение операции пополнения
        SavingAccount account = new SavingAccount(
                2_000,
                1_000,
                10_000,
                5
        );

        account.add(3_000);

        Assertions.assertEquals(2_000 + 3_000, account.getBalance());
    }

    @Test
    public void shouldAddLessThanMaxBalanceOne() { //Тест на успешное выполнение операции пополнения (Ниже на единицу)
        SavingAccount account = new SavingAccount(
                2_000,
                1_000,
                10_000,
                5
        );

        account.add(7_999);

        Assertions.assertEquals(2_000 + 7_999, account.getBalance());
    }


    @Test
    public void shouldThrowExceptionWhenRateIsPositive() { //Тест на проверку положительной ставки
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            SavingAccount account = new SavingAccount(
                    0,
                    0,
                    0,
                    0
            );
        });
        //Assertions.assertEquals(5, account.getRate());
    }

    //@Test
    //public void shouldThrowExceptionWhenRateIsNegative() { //Тест на проверку ставки равной нулю
        //SavingAccount account = new SavingAccount(
                //0,
                //0,
                //0,
                //0
        //);

       //account.setRate(0);

        //Assertions.assertEquals(0, account.getRate());
    //}

    @Test
    public void shouldDecreaseBalanceOnSuccessfulPayment() { //Тест на успешное выполнение операции оплаты (
        SavingAccount account = new SavingAccount(
                5_000,
                1_000,
                1_0000,
                5);

        account.pay(2_000);

        Assertions.assertEquals(3_000, account.getBalance());
    }

    @Test
    public void shouldNotChangeBalanceOnUnsuccessfulPayment() { //Тест на некорректную операцию оплаты (при которой баланс уйдет в минус):
        SavingAccount account = new SavingAccount(
                1_000,
                1_000,
                10_000,
                5);

        account.pay(2_000);

        Assertions.assertEquals(1_000, account.getBalance());
    }

    @Test
    public void shouldNotChangeBalanceOnPaymentWithNegativeAmount() { //Тест на попытку оплаты отрицательной суммы
        SavingAccount account = new SavingAccount(
                5_000,
                1_000,
                1_0000,
                5);

        account.pay(-2_000);

        Assertions.assertEquals(5_000, account.getBalance());
    }

    @Test
    public void shouldNotChangeBalanceOnPaymentWithZeroAmount() { //Тест на попытку оплаты суммы равной нулю
        SavingAccount account = new SavingAccount(
                5_000,
                1_000,
                10_000,
                5);

        account.pay(0);

        Assertions.assertEquals(5_000, account.getBalance());
    }

    @Test
    public void shouldChangeBalanceOnPaymentIfBalanceMoreMinBalance() { //Тест на попытку оплаты, в случае
        // если минимальный баланс больше полученного баланса

        SavingAccount account = new SavingAccount(
                2_500,
                500,
                10_000,
                5);

        account.pay(1_500);
        Assertions.assertEquals(1_000, account.getBalance());
    }

    @Test
    public void shouldNotChangeBalanceOnUnsuccessfulAdd() { //Тест на некорректную операцию пополнения
        // (при которой баланс превышает максимальный)
        SavingAccount account = new SavingAccount(
                9_000,
                1_000,
                10_000,
                5);

        account.add(3_000);

        Assertions.assertEquals(9_000, account.getBalance());
    }

    @Test
    public void shouldNotChangeBalanceOnUnsuccessfulAddOne() { //Тест на некорректную операцию пополнения
        // (при которой баланс превышает максимальный на 1)
        SavingAccount account = new SavingAccount(
                10_000,
                1_000,
                10_000,
                5);

        account.add(1);

        Assertions.assertEquals(10_000, account.getBalance());
    }

    @Test
    public void shouldNotChangeIfBalanceEqualMaxBalance() { //Тест при которой итоговый баланс равен максимальному
        // (при которой баланс превышает максимальный на 1)
        SavingAccount account = new SavingAccount(
                5_000,
                1_000,
                10_000,
                5);

        account.add(5_001);

        Assertions.assertEquals(5_000, account.getBalance());
    }

    @Test
    public void shouldNotChangeBalanceOnAddWithZeroAmount() { //Тест на попытку пополнения суммы равной нулю
        SavingAccount account = new SavingAccount(
                5_000,
                1_000,
                10_000,
                5);

        account.add(0);

        Assertions.assertEquals(5_000, account.getBalance());
    }

    @Test
    public void shouldCalculateInterestOnPositiveBalance() { //Тест на успешный расчёт процентов
        // при положительном балансе
        SavingAccount account = new SavingAccount(
                5_000,
                1_000,
                10_000,
                5);

        account.yearChange();

        Assertions.assertEquals(250, account.yearChange()); // 5000 (баланс) / 100 * 5 (ставка) = 250
    }

    @Test
    public void shouldCalculateInterestOnNegativeBalance() { //Тест на успешный расчёт процентов при отрицательном балансе
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            SavingAccount account = new SavingAccount(
                    -2_000,
                    1_000,
                    10_000,
                    5);
        });
    }

    @Test
    public void shouldCalculateInterestOnZeroBalance() { //Тест на успешный расчёт процентов при балансе равном нулю
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            SavingAccount account = new SavingAccount(
                            0,
                            1_000,
                            10_000,
                            5);
        });
    }

    @Test
    public void shouldCalculateInterestOnMinBalance() { //Тест на успешный расчёт процентов
        // при минимально допустимом балансе
        SavingAccount account = new SavingAccount(
                1_000,
                1_000,
                10_000,
                5);

        account.yearChange();

        Assertions.assertEquals(50, account.yearChange()); // 1000 (баланс) / 100 * 5 (ставка) = 0
    }

    @Test
    public void shouldCalculateInterestOnMaxBalance() { //Тест на успешный расчёт процентов
        // при максимально допустимом балансе
        SavingAccount account = new SavingAccount(
                10_000,
                1_000,
                10_000,
                5);

        account.yearChange();

        Assertions.assertEquals(500, account.yearChange()); // 10000 (баланс) / 100 * 5 (ставка) = 500
    }

    @Test
    public void shouldThrowExceptionForNegativeRate() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> { //Тест на некорректное значение ставки
            SavingAccount account = new SavingAccount(
                    1_000,
                    500,
                    2_000,
                    -5);
        });
    }
    @Test
    public void shouldThrowExceptionForZeroRate() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> { //Тест на некорректное значение ставки
            SavingAccount account = new SavingAccount(
                    1_000,
                    500,
                    2_000,
                    0);
        });
    }

    @Test
    public void shouldThrowExceptionForAllInvalidParameters() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> { //Тест на некорректное значение
            new SavingAccount(
                    -1_000,
                    -2000,
                    -500,
                    5);
        });
    }
}