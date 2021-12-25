package com.company.day17;

public class Day17 {
    public static void main(String[] args) {
        System.out.println("Part1: " + part1());
        System.out.println("Part2: " + part2());
    }

    public static int xmin = 32;
    public static int xmax = 65;
    public static int ymin = -225;
    public static int ymax = -177;

    public static int part1() {
        // (|y-1| * |y|) / 2
        return ((Math.abs(ymin) - 1) * (Math.abs(ymin))) / 2;
    }

    public static int part2() {
        int vxmin, vxmax, vymin, vymax;

        vxmax = xmax;
        vymin = ymin;
        vxmin = (int) Math.ceil((-1 + Math.sqrt(1 + 8 * xmin)) / 2);
        vymax = Math.abs(ymin) ;

        int count = 0;
        for (int x = vxmin; x <= vxmax; x++) {
            for (int y = vymin; y < vymax; y++) {
                if (runSimul(x, y)) {
                    count++;
                }
            }
        }
        return count;
    }

    private static boolean runSimul(int vx, int vy) {
        int xP = 0, yP = 0;
        while (xP <= xmax && yP >= ymin) {
            xP += vx;
            yP += vy;
            if (vx > 0)
                vx--;
            vy--;
            if (xP >= xmin && xP <= xmax && yP >= ymin && yP <= ymax) {
                return true;
            }
        }
        return false;
    }
}
