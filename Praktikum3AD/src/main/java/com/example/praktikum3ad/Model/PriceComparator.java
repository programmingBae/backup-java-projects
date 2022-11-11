package com.example.praktikum3ad.Model;

import java.util.Comparator;

public class PriceComparator implements Comparator<MenuItem> {

    @Override
    public int compare(MenuItem o1, MenuItem o2) {
        return o1.getPrice() < o2.getPrice() ? -1 : o1.getPrice() == o2.getPrice() ? 0 : 1;
    }
}
