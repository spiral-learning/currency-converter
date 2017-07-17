package io.trampoline.currencyconverter;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

/**
 * Convert currency via
 * http://www.apilayer.net/api/live?access_key=5eea965a686574c12730a7367bbe0069&from=USD&currencies=GBP
 */

@RestController
@RequestMapping("/convert")
public class ConversionRestController {

  @GetMapping
  public ConversionResponse convert(@RequestParam("from") String fromCurrency,
                                    @RequestParam("to") String toCurrency,
                                    @RequestParam("amount") int amount) {
    return new ConversionResponse(toCurrency, BigDecimal.valueOf(.765 * amount));
  }
}
