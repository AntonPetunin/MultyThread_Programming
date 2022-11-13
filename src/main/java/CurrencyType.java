public enum CurrencyType {
  RUB(1),
  EU(0.0165),
  USD(0.016),
  KZT(7.54);

  private final double value;

  CurrencyType(double value) {
    this.value = value;
  }

  public double getValue(){
    return this.value;
  }
}
