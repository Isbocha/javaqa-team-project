package ru.netology.javaqaproject;

/**
 * Сберегательный счёт
 * Может иметь баланс только в пределах от указанного минимального до указанного максимального включительно.
 * Не может уходить в минус (минимальный баланс не может быть отрицательным).
 * Имеет ставку - количество процентов годовых на остаток.
 */
public class SavingAccount extends Account {
    private final int minBalance;
    private final int maxBalance;

    /**
     * Создаёт новый объект сберегательного счёта с заданными параметрами.
     * Если параметры некорректны (мин. баланс больше максимального и так далее), то
     * должно выкидываться исключения вида IllegalArgumentException.
     *
     * @param initialBalance - начальный баланс
     * @param minBalance     - минимальный баланс
     * @param maxBalance     - максимальный баланс
     * @param rate           - неотрицательное число, ставка в процентах годовых на остаток
     */
    public SavingAccount(int initialBalance, int minBalance, int maxBalance, int rate) {
        if (rate <= 0) {
            throw new IllegalArgumentException(
                    "Накопительная ставка не может быть отрицательной или быть равной нулю, а у вас: " + rate
            );
        }
        if (minBalance < 0) {
            throw new IllegalArgumentException(
                    "Минимальный баланс не может быть отрицательной, а у вас: " + minBalance
            );
        }
        if (maxBalance < minBalance) {
            throw new IllegalArgumentException(
                    "Масимальный баланс не может быть меньше минимального, а у вас: " + maxBalance + "<" + minBalance
            );
        }
        if (initialBalance < minBalance) {
            throw new IllegalArgumentException(
                    "Текущий баланс не может быть меньше минимального, а у вас: " + initialBalance + "<" + minBalance
            );
        }
        if (initialBalance > maxBalance) {
            throw new IllegalArgumentException(
                    "Текущий баланс не может быть больше максимального, а у вас: " + initialBalance + ">" + maxBalance
            );
        }
        this.balance = initialBalance;
        this.minBalance = minBalance;
        this.maxBalance = maxBalance;
        this.rate = rate;
    }

    /**
     * Операция оплаты с карты на указанную сумму.
     * В результате успешного вызова этого метода, баланс должен уменьшиться
     * на сумму покупки. Если же операция может привести к некорректному
     * состоянию счёта (например, баланс может уйти в минус), то операция должна
     * завершиться вернув false и ничего не поменяв на счёте.
     *
     * @param amount - сумма покупки
     * @return true если операция прошла успешно, false иначе.
     */
    @Override
    public boolean pay(int amount) {
        if (amount <= 0) {
            return false;
        }
        int potentialBalance = balance - amount;
        if (potentialBalance >= minBalance) {
            balance = potentialBalance;
            return true;
        } else {
            return false;
        }
    }

    /**
     * Операция пополнения карты на указанную сумму.
     * В результате успешного вызова этого метода, баланс должен увеличиться
     * на сумму покупки. Если же операция может привести к некорректному
     * состоянию счёта, то операция должна
     * завершиться вернув false и ничего не поменяв на счёте.
     *
     * @param amount - сумма пополнения
     * @param amount
     * @return true если операция прошла успешно, false иначе.
     * @return
     */
    @Override
    public boolean add(int amount) {
        if (amount <= 0) {
            return false;
        }
        if (balance + amount <= maxBalance) {
            balance = balance + amount;
            return true;
        } else {
            return false;
        }
    }

    /**
     * Операция расчёта процентов на остаток счёта при условии, что
     * счёт не будет меняться год. Сумма процентов приводится к целому
     * числу через отбрасывание дробной части (так и работает целочисленное деление).
     * Пример: если на счёте 200 рублей, то при ставке 15% ответ должен быть 30.
     *
     * @return
     */

    @Override
    public int yearChange() {
        if (balance == 0) {
            return 0;
        } else {
            return getBalance() * rate / 100;
        }
    }

    public int getMinBalance() {
        return minBalance;
    }

    public int getMaxBalance() {
        return maxBalance;
    }
}