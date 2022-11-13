import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Client {

  static int idCounter = 0;

  int id;
  private Map<CurrencyType, Double> balance;
  private Lock locker = new ReentrantLock();

  public Client(HashMap<CurrencyType, Double> money) {
    this.id = idCounter++;
    this.balance = money;
  }

  public boolean SendMoney(double money, CurrencyType currencyType){
    try {
      locker.lock();
      if (balance.get(currencyType) < money)
        return false;

      double oldBalance = balance.remove(currencyType);
      balance.put(currencyType, oldBalance - money);
      return true;
    }
    finally {
      locker.unlock();
    }

  }

  public void PutMoney(double money, CurrencyType currencyType){
    try {
      locker.lock();

      if (balance.containsKey(currencyType)) {
        double keyBalance = balance.remove(currencyType);
        balance.put(currencyType, keyBalance + money);
      }
      else {
        balance.put(currencyType, money);
      }
    }
    finally {
      locker.unlock();
    }
  }

  @Override
  public int hashCode() {
    return Objects.hash(this.id);
  }
}
