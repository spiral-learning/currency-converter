package io.trampoline.currencyconverter;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

/**
 * TODO: Actually convert currency instead of hard-coded via
 * http://www.apilayer.net/api/live?access_key={access key}&from=USD&currencies=GBP
 */

@RestController
@RequestMapping("/convert")
public class ConversionRestController {

  @GetMapping
  public ConversionResponse convert(@RequestParam("from") String fromCurrency,
                                    @RequestParam("to") String toCurrency,
                                    @RequestParam("amount") int amount) {
    if (!fromCurrency.equalsIgnoreCase("USD")) {
      throw new UnknownCurrencyException();
    }
    double factor;
    if (toCurrency.equalsIgnoreCase("BTC") || toCurrency.equalsIgnoreCase("XBT")) {
      factor = 0.00012;
    } else if (toCurrency.equalsIgnoreCase("GBP")) {
      factor = 0.76;
    } else {
      throw new UnknownCurrencyException();
    }
    return new ConversionResponse(toCurrency, BigDecimal.valueOf(factor * amount));
  }
}
