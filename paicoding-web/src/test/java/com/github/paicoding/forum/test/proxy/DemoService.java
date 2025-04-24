package com.github.paicoding.forum.test.proxy;

import org.junit.Test;

public class DemoService {

    public String showHello(String arg) {
        System.out.println("in function!");
        System.out.println("before return:" + arg);
        return "prefix_" + arg;
    }
}
