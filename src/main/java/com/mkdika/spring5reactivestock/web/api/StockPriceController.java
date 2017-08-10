/*
 * The MIT License
 *
 * Copyright 2017 Maikel Chandika <mkdika@gmail.com>.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package com.mkdika.spring5reactivestock.web.api;

import com.mkdika.spring5reactivestock.model.Stock;
import io.netty.util.internal.ThreadLocalRandom;
import java.time.Duration;
import java.util.Date;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import static org.springframework.web.bind.annotation.RequestMethod.GET;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

/**
 *
 * @author Maikel Chandika <mkdika@gmail.com>
 */
@RestController
@RequestMapping("/api/stockprice")
public class StockPriceController {

    @RequestMapping(method = GET, value = "/{stockcode}")
    Flux<String> fetchStockPrice(@PathVariable("stockcode") String stockcode) {
        return Flux
                .interval(Duration.ofSeconds(3))
                .map(l -> new Stock(stockcode, new Date(), getRandomPrice(stockcode)).toString()).log();

    }

    private Integer getRandomPrice(String stockcode) {
        Integer min = 0, max = 0;
        switch (stockcode) {
            case "AAPL":        // Apple
                min = 150;
                max = 180;
                break;
            case "GOOGL":       // Google
                min = 930;
                max = 960;
                break;
            case "MSFT":        // Microsoft
                min = 70;
                max = 100;
                break;
            case "FB":          // Facebook
                min = 160;
                max = 190;
                break;
        }
        return ThreadLocalRandom.current().nextInt(min, max);
    }
}
