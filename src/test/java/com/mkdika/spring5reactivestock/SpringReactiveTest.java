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
package com.mkdika.spring5reactivestock;

import java.time.Duration;
import java.util.Date;
import org.junit.Test;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import java.util.function.Consumer;

/**
 *
 * @author Maikel Chandika <mkdika@gmail.com>
 */
public class SpringReactiveTest {
        
    @Test
    public void monoExample() throws InterruptedException {
        System.out.println("Mono Demo:");
        Mono<String> stubMonoWithADelay
                = Mono.just("Maikel").delayElement(Duration.ofSeconds(5));
        stubMonoWithADelay.subscribe(t -> System.out.println("Hello "+t+" at "+new Date()));
        stubMonoWithADelay.subscribe(t -> System.out.println("Apa Kabar "+t+" at "+new Date()));
        Thread.sleep(6000);
    }
    
    @Test
    public void fluxExample() throws InterruptedException {
        System.out.println("Flux Demo:");
        Flux<String> stubFluxStream = Flux
                .just("Andy","Budi","Charlie","Donny","Erwin")
                .delayElements(Duration.ofSeconds(1));
        stubFluxStream.subscribe(t -> System.out.println("Halo "+t));
        stubFluxStream.subscribe(new SubcriberX());
        Thread.sleep(6000);
    }
    
    // implement consumer interface for custom logic.
    class SubcriberX implements Consumer<String> {
        @Override
        public void accept(String t) {
            System.out.println("XXX - Receive: "+t);
        }        
    }
    
}
