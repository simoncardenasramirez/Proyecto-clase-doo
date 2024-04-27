package co.edu.uco.crosscutting;

import java.util.UUID;

public final class UUIDHelper {

    public static final UUID DEFAULT_UUID = UUID.fromString("00000000-0000-0000-0000-000000000000");

    private UUIDHelper() {
    	super();
    }

    public static final boolean isNull(final UUID uuid) {
        return uuid == null;
    }

    public static final boolean isDefault(final UUID uuid) {
        return DEFAULT_UUID.equals(uuid);
    }

    public static final boolean isNullOrEmpty(final UUID uuid) {
        return isNull(uuid) || isDefault(uuid);
    }

    public static final UUID getDefaultValue(final UUID uuid, final UUID defaultValue) {
        return isNullOrEmpty(uuid) ? defaultValue : uuid;
    }

    public static final UUID getDefaultValue(final UUID uuid) {
        return getDefaultValue(uuid, DEFAULT_UUID);
    }

    public static final UUID randomUUID() {
        return UUID.randomUUID();
    }
}
