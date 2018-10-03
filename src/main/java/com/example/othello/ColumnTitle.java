package com.example.othello;

public enum ColumnTitle {
    a(0),
    b(1),
    c(2),
    d(3),
    e(4),
    f(5),
    g(6),
    h(7);

    private final int id;

    private ColumnTitle(final int id) {
        this.id = id;
    }

    public int getId() {
        return this.id;
    }
}
