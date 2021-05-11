package io.trampoline.currencyconverter;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

/**
 * TODO: Actually convert currency instead of hard-coded
 * (other data at https://apilayer.com/)
 * Currency Layer is limited to 250 free requests per month:
 * https://api.currencylayer.com/api/live?access_key={access key}&from=USD&currencies=GBP
 * or
 * https://free.currencyconverterapi.com/
 * or
 * https://coinlayer.com/documentation
 * for Crypto
 */
@RestController
@RequestMapping
public class ConversionRestController {

  @GetMapping("/convert")
  public ConversionResponse convert(@RequestParam(name = "from", defaultValue = "USD") String fromCurrency,
                                    @RequestParam("to") String toCurrency,
                                    @RequestParam("amount") BigDecimal amount) {
    if (!fromCurrency.equalsIgnoreCase("USD")) {
      throw new UnknownCurrencyException("Unknown 'from' currency: " + fromCurrency + ", only USD supported.");
    }
    BigDecimal factor = conversionFactorFor(toCurrency);
    return new ConversionResponse(toCurrency, factor.multiply(amount));
  }

  @GetMapping("/convert-dto")
  public ConversionResponse convert2(ConversionRequestIn request) {
    return new ConversionResponse(request.getTo(), conversionFactorFor(request.getTo()).multiply(request.getAmount()));
  }

  private BigDecimal conversionFactorFor(String toCurrency) {
    BigDecimal factor;
    if (toCurrency.equalsIgnoreCase("BTC") || toCurrency.equalsIgnoreCase("XBT")) {
      factor = BigDecimal.valueOf(0.000018); // $55,862 per BTC
    } else if (toCurrency.equalsIgnoreCase("GBP")) {
      factor = BigDecimal.valueOf(0.708);
    } else if (toCurrency.equalsIgnoreCase("JPY")) {
      factor = BigDecimal.valueOf(108.86);
    } else if (toCurrency.equalsIgnoreCase("DOGE")) {
      factor = BigDecimal.valueOf(2.15);
    } else {
      throw new UnknownCurrencyException("Unknown 'to' currency: " + toCurrency + ". Must be one of BTC, GBP, JPY, or DOGE");
    }
    return factor;
  }
}
