package com.upwordsapi.upwords.solve;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

public class BoardSolverFactory {
    private static final Map<String, Supplier<? extends IBoardSolver>> registeredSuppliers = new HashMap<>();
    private static String defaultImplementation;

    public static IBoardSolver getSolver(String type) {
        Supplier<? extends IBoardSolver> supplier = registeredSuppliers.get(type);
        if (supplier == null) return null;
        return supplier.get();
    }

    public static void registerSupplier(String type, Supplier<? extends IBoardSolver> supplier) {
        registeredSuppliers.put(type, supplier);
    }

    public static void registerDefaultSupplier(String type, Supplier<? extends IBoardSolver> supplier) {
        registerSupplier(type, supplier);
        defaultImplementation = type;
    }

    public static IBoardSolver getDefaultSolver() {
        Supplier<? extends IBoardSolver> supplier = registeredSuppliers.get(defaultImplementation);
        if (supplier == null) return null;
        return supplier.get();
    }
}
