package readability;

public enum Year {
    Grade0  (1, 6),
    Grade1  (2, 7),
    Grade3  (3, 9),
    Grade4  (4, 10),
    Grade5  (5, 11),
    Grade6  (6, 12),
    Grade7  (7, 13),
    Grade8  (8, 14),
    Grade9  (9, 15),
    Grade10 (10, 16),
    Grade11 (11, 17),
    Grade12 (12, 18),
    Grade13 (13, 24),
    Grade14 (14, 24);

    int index;
    int age;

    Year(int index, int age) {
        this.index = index;
        this.age = age;
    }

    public int getIndex() {
        return index;
    }

    public int getAge() {
        return age;
    }

    public static int find(double index) {
        index = Math.round(index);
        for (Year value : values()) {
            if (index == (value.index)) {
                return value.age;
            }
        }
        return 0;
    }
}
