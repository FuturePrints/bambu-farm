package com.tfyre.bambu.printer;

import java.util.EnumSet;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 *
 * @author Francois Steyn - (fsteyn@tfyre.co.za)
 */
public enum Filament {
    UNKNOWN("Unknown", "Unknown", FilamentType.UNKNOWN, "Unknown", true),
    BAMBU_ABS("GFB00", "Bambu ABS", FilamentType.ABS, "ABS", false),
    BAMBU_ASA("GFB01", "Bambu ASA", FilamentType.ASA, "ASA", false),
    BAMBU_PACF("GFN03", "Bambu PA-CF", FilamentType.PACF, "PA-CF", false),
    BAMBU_PA6CF("GFN05", "Bambu PA6-CF", FilamentType.PA6CF, "PA6-CF", false),
    BAMBU_PAHTCF("GFN04", "Bambu PAHT-CF", FilamentType.PACF, "PAHT-CF", false),
    BAMBU_PC("GFC00", "Bambu PC", FilamentType.PC, "PC", false),
    BAMBU_PET_CF("GFT01", "Bambu PET-CF", FilamentType.PETCF, "PET-CF", false),
    BAMBU_PETG_BASIC("GFG00", "Bambu PETG Basic", FilamentType.PETG, "PETG", false),
    BAMBU_PETG_CF("GFG50", "Bambu PETG-CF", FilamentType.PETGCF, "PETG-CF", false),
    BAMBU_PLA_AERO("GFA11", "Bambu PLA Aero", FilamentType.PLA_AERO, "PLA Aero", false),
    BAMBU_PLA_BASIC("GFA00", "Bambu PLA Basic", FilamentType.PLA, "PLA", false),
    BAMBU_PLA_IMPACT("GFA03", "Bambu PLA Impact", FilamentType.PLA, "PLA", false),
    BAMBU_PLA_MARBLE("GFA07", "Bambu PLA Marble", FilamentType.PLA, "PLA", false),
    BAMBU_PLA_MATTE("GFA01", "Bambu PLA Matte", FilamentType.PLA, "PLA", false),
    BAMBU_PLA_METAL("GFA02", "Bambu PLA Metal", FilamentType.PLA, "PLA", false),
    BAMBU_PLA_SILK("GFA05", "Bambu PLA Silk", FilamentType.PLA, "PLA", false),
    BAMBU_PLA_SPARKLE("GFA08", "Bambu PLA Sparkle", FilamentType.PLA, "PLA", false),
    BAMBU_PLA_TOUGH("GFA09", "Bambu PLA Tough", FilamentType.PLA, "PLA", false),
    BAMBU_PLA_CF("GFA50", "Bambu PLA-CF", FilamentType.PLA_CF, "PLA-CF", false),
    BAMBU_SUPPORT_PA("GFS03", "Bambu Support For PA/PET", FilamentType.PLA, "PLA", false),
    BAMBU_SUPPORT_PLA("GFS02", "Bambu Support For PLA", FilamentType.PLA, "PLA", false),
    BAMBU_SUPPORT_G("GFS01", "Bambu Support G", FilamentType.PLA, "PLA", false),
    BAMBU_SUPPORT_W("GFS00", "Bambu Support W", FilamentType.PLA, "PLA", false),
    BAMBU_TPU("GFU01", "Bambu TPU 95A", FilamentType.TPU, "TPU", false),
    GENERIC_ABS("GFB99", "Generic ABS", FilamentType.ABS, "ABS", true),
    GENERIC_ASA("GFB98", "Generic ASA", FilamentType.ASA, "ASA", true),
    GENERIC_HIPS("GFS98", "Generic HIPS", FilamentType.HIPS, "HIPS", true),
    GENERIC_PACF("GFN98", "Generic PA-CF", FilamentType.PACF, "PA-CF", true),
    GENERIC_PA("GFN99", "Generic PA", FilamentType.PA, "PA", true),
    GENERIC_PC("GFC99", "Generic PC", FilamentType.PC, "PC", true),
    GENERIC_PETG("GFG99", "Generic PETG", FilamentType.PETG, "PETG", true),
    GENERIC_PETC_CF("GFG98", "Generic PETG-CF", FilamentType.PETGCF, "PETG-CF", true),
    GENERIC_PLA("GFL99", "Generic PLA", FilamentType.PLA, "PLA", true),
    GENERIC_PLA_HS("GFL95", "Generic PLA-High Speed", FilamentType.PLA, "PLA", true),
    GENERIC_PLA_SILK("GFL96", "Generic PLA Silk", FilamentType.PLA, "PLA", true),
    GENERIC_PLA_CF("GFL98", "Generic PLA-CF", FilamentType.PLA_CF, "PLA-CF", true),
    GENERIC_PVA("GFS99", "Generic PVA", FilamentType.PVA, "PVA", true),
    GENERIC_TPU("GFU99", "Generic TPU", FilamentType.TPU, "TPU", true),
    OVERTURE_PLA("GFL05", "Overture Matte PLA", FilamentType.PLA, "PLA", false),
    OVERTURE_ABS("GFL04", "Overture PLA", FilamentType.PLA, "PLA", false),
    POLYLITE_ABS("GFB60", "PolyLite ABS", FilamentType.ABS, "ABS", false),
    POLYLITE_ASA("GFB61", "PolyLite ASA", FilamentType.ASA, "ASA", false),
    POLYLITE_PETG("GFG60", "PolyLite PETG", FilamentType.PETG, "PETG", false),
    POLYLITE_PLA("GFL00", "PolyLite PLA", FilamentType.PLA, "PLA", false),
    POLYTERRA_PLA("GFL01", "PolyTerra PLA", FilamentType.PLA, "PLA", false),
    ESUN_PLA("GFL03", "eSUN PLA+", FilamentType.PLA, "PLA", false),
    GENERIC_PLA_SILK_01("GFSL99_01", "Generic PLA Silk 01", FilamentType.PLA, "PLA", true),
    GENERIC_PLA_SILK_12("GFSL99_12", "Generic PLA Silk 12", FilamentType.PLA, "PLA", true);


    private static final Map<String, Filament> MAP = EnumSet.allOf(Filament.class).stream().collect(Collectors.toMap(Filament::getCode, Function.identity()));
    private static final Function<Filament, String> MAPPER_DESCRIPTION = filament -> filament.getDescription();
    private static final Function<Filament, String> MAPPER_TYPE = filament -> filament.getType().getDescription();

    private final String code;
    private final String description;
    private final FilamentType type;
    private final String trayType;
    private final boolean isGeneric; // New field to identify generic filaments

    private Filament(final String code, final String description, final FilamentType type, final String trayType, final boolean isGeneric) {
        this.code = code;
        this.description = description;
        this.type = type;
        this.trayType = trayType;
        this.isGeneric = isGeneric;
    }

    public String getTrayType() {
        return trayType;
    }

    public boolean isGeneric() {
        return isGeneric;
    }
    
    public String getCode() {
        return code;
    }

    public String getDescription() {
        return description;
    }

    public FilamentType getType() {
        return type;
    }

    public static Optional<Filament> getFilamentByTrayType(final String trayType) {
        // Attempt to find a specific filament
        Optional<Filament> specific = EnumSet.allOf(Filament.class).stream()
                .filter(filament -> filament.trayType.equalsIgnoreCase(trayType) && !filament.isGeneric)
                .findFirst();

        if (specific.isPresent()) {
            return specific;
        }

        // Fallback to a generic filament if specific is not found
        return EnumSet.allOf(Filament.class).stream()
                .filter(filament -> filament.trayType.equalsIgnoreCase(trayType) && filament.isGeneric)
                .findFirst();
    }

    public static String getFilamentDescription(final String code, final boolean fullName) {
        final Function<Filament, String> mapper = fullName ? MAPPER_DESCRIPTION : MAPPER_TYPE;
        return Optional.ofNullable(MAP.get(code))
                .map(mapper)
                .orElseGet(() -> mapper.apply(UNKNOWN));
    }

}
