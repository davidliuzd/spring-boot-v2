package net.liuzd.spring.boot.v2.util;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Locale;

public class MyUtils {

    public static String formatCurrency(String amount) {
        DecimalFormat formatter = new DecimalFormat("###,###,##0.00");
        return formatter.format(Double.parseDouble(amount));
    }

    public static String formatPrice(final double priceAsDouble) {
        NumberFormat formatter = NumberFormat.getCurrencyInstance();
        if (Math.round(priceAsDouble * 100) % 100 == 0) {
            formatter.setMaximumFractionDigits(0);
        }
        return formatter.format(priceAsDouble);
    }

    public static String formatUSPrice(final double priceAsDouble) {
        Locale locale = new Locale("en", "US");
        NumberFormat currencyFormatter = NumberFormat.getCurrencyInstance(locale);
        BigDecimal amount = BigDecimal.valueOf(priceAsDouble);
        amount.setScale(2, BigDecimal.ROUND_HALF_UP);
        return currencyFormatter.format(amount);
    }

    public static void main(String[] args) {
        System.out.println(formatCurrency("50453458345354")); // 50,453,458,345,354.00
        System.out.println(formatPrice(504534583)); // ￥504,534,583
        System.out.println(formatUSPrice(2007867868678.0));//$2,007,867,868,678.00
    }

}
