package com.exemplo;

import java.util.HashSet;
import java.util.Set;

class Eleitor {
    private static Set<String> eleitoresQueVotaram = new HashSet<>();

    public static boolean podeVotar(String idEleitor) {
        return !eleitoresQueVotaram.contains(idEleitor);
    }

    public static void registrarVoto(String idEleitor) {
        eleitoresQueVotaram.add(idEleitor);
    }
}