/**
 * Makes it easier to retrieve pollution data
 */
public record PollutionData(String riverName, int month, int day, int arsic, int lead, double fertilizer, double pesticide) {}