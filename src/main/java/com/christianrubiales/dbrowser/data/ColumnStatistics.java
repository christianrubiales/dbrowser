package com.christianrubiales.dbrowser.data;

import lombok.Data;

@Data
public class ColumnStatistics {

    private Double min;
    private Double max;
    private Double average;
    private Double median;

}
