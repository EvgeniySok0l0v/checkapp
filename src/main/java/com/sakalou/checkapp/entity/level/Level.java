package com.sakalou.checkapp.entity.level;

/**
 * enum for card level
 */
public enum Level {

    BRONZE(3),
    SILVER(5),
    GOLD(10),
    PLATINUM(13);

    //percent of discount
    private final int discountValue;

    /**
     * default constructor
     * @param discountValue - percent of discount
     */
    Level(int discountValue){
        this.discountValue = discountValue;
    }

    public int getDiscountValue() {
        return discountValue;
    }
}
