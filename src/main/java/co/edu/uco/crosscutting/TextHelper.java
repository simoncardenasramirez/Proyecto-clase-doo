package co.edu.uco.crosscutting;


public final class TextHelper {
	
	public static final String EMPTY = "";
	public static final String UNDERLINE = "_";
	
	private TextHelper() {
		super();
	}
	
	public static final boolean isNull(final String string) {
		return ObjectHelper.getObjectHelper().isNull(string);
	}
	
	public static final boolean isNullOrEmpty(final String string) {
		return isNull(string) || EMPTY.equals(string.trim());
	}
	
	public static final String getDefaultValue(final String string ,final String defaultValue) {
		return ObjectHelper.getObjectHelper().getDefaultValue(string, null);
	}
	
	public static final String getDefaultValue(final String string) {
		return getDefaultValue(string, EMPTY);
	}
	
	public static final String applyTrim(final String string) {
		return getDefaultValue(string).trim();
	}
	
	public static final String concatenate(String...strings) {
		final StringBuilder sb = new StringBuilder(EMPTY);
		
		if(!ObjectHelper.getObjectHelper().isNull(strings)) {
			for (final var  string : strings) {
				sb.append(applyTrim(string));
		
			}
		}
		
		return sb.toString();
	}
	
	
	
	
	
}
