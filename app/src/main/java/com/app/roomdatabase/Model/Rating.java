package com.app.roomdatabase.Model;

import java.io.Serializable;


public class Rating implements Serializable {
        private double rate;
        private int count;

        public double getRate() {
            return rate;
        }

        public int getCount() {
            return count;
        }
    }

