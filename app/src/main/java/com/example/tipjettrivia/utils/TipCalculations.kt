
fun calculateTipAmount(totalBillMoney: Double, tipPercentage: Int): Double {
    return if(totalBillMoney > 1 && totalBillMoney.toString().isNotEmpty()) {
        (totalBillMoney * tipPercentage) /100
    } else 0.0
}
fun calculateTipPerPerson(totalBillMoney: Double, splitBy: Int, tipPercentage: Int): Double {
    val bill = calculateTipAmount(totalBillMoney, tipPercentage) + totalBillMoney
    return bill / splitBy
}
