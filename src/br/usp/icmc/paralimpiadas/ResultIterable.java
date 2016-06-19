package br.usp.icmc.paralimpiadas;

import java.sql.ResultSet;

@FunctionalInterface
public interface ResultIterable {
    void iterate (ResultSet rs);
}
