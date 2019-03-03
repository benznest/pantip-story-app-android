package com.benznestdeveloper.pantipstory.sort;

import com.benznestdeveloper.pantipstory.dao.RoomDao;

import java.util.Comparator;

/**
 * Created by benznest on 08-Oct-17.
 */

public class MySortRoom {
    public static class CompareCount implements Comparator<RoomDao> {
        int orderBy = MySortManager.ORDER_BY_ASCENDING;

        public CompareCount() {
        }

        public CompareCount(int orderBy) {
            this.orderBy = orderBy;
        }

        @Override
        public int compare(RoomDao o1, RoomDao o2) {
            double sum1 = o1.getCount();
            double sum2 = o2.getCount();

            if (orderBy == MySortManager.ORDER_BY_ASCENDING) {
                return asc(sum1, sum2);
            } else {
                return desc(sum1, sum2);
            }
        }
    }

    private static int asc(double sum1, double sum2) {
        if (sum1 > sum2) {
            return 1;
        } else if (sum1 < sum2) {
            return -1;
        }
        return 0;
    }

    private static int desc(double sum1, double sum2) {
        if (sum1 < sum2) {
            return 1;
        } else if (sum1 > sum2) {
            return -1;
        }
        return 0;
    }
}
