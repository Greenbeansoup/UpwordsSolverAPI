package com.upwordsapi.upwords.solve;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

public class BoardSolverFactory {
    private static final Map<BoardSolveType, Supplier<? extends IBoardSolver>> registeredSuppliers = new HashMap<>();

    public static IBoardSolver getSolver(BoardSolveType type) {
        Supplier<? extends IBoardSolver> supplier = registeredSuppliers.get(type);
        if (supplier == null) return null;
        return supplier.get();
    }

    public static void registerSupplier(BoardSolveType type, Supplier<? extends IBoardSolver> supplier) {
        registeredSuppliers.put(type, supplier);
    }
}
