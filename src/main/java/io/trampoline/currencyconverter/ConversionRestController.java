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
    return new ConversionResponse(toCurrency, BigDecimal.valueOf(.7022 * amount));
  }
}
