package org.psframework.utils;

// Imports
import com.maxmind.geoip2.DatabaseReader;
import com.maxmind.geoip2.exception.GeoIp2Exception;
import com.maxmind.geoip2.model.CityResponse;
import java.io.IOException;
import java.net.InetAddress;
import java.nio.file.Path;

public final class PSGeoIP implements AutoCloseable {
    private final DatabaseReader reader;

    /**
     * Initializes the Geolocation API.
     * @param databasePath The GeoIP.dat file location.
     * @throws IOException The file cannot be found or accessed.
     */
    public PSGeoIP(Path databasePath) throws IOException {
        this.reader = new DatabaseReader.Builder(databasePath.toFile()).build();
    }

    /**
     * Looks up the IP Address.
     * @param ipAddress The given IP Address.
     * @return Information about the IP Address.
     * @throws IOException The IP Address is invalid.
     */
    public GeoIPResult lookup(String ipAddress) throws IOException {
        InetAddress address = InetAddress.getByName(ipAddress);
        try {
            CityResponse response = this.reader.city(address);
            return new GeoIPResult(response.getCountry().getName(), response.getCountry().getIsoCode(), response.getMostSpecificSubdivision().getName(), response.getCity().getName(), response.getLocation().getLatitude(), response.getLocation().getLongitude());
        } catch (GeoIp2Exception ignored) {
            return new GeoIPResult("null", "null", "null", "null", 0.0, 0.0);
        }
    }

    /**
     * Gets the language and country code of an IP Address.
     * @param ipAddress The given IP Address.
     * @return Language and Country code of an IP Address.
     * @throws IOException The IP Address is invalid.
     */
    public GeoIPLanguage getLanguageAndCountry(String ipAddress) {
        try {
            InetAddress address = InetAddress.getByName(ipAddress);
            CityResponse response = this.reader.city(address);
            String countryCode = response.getCountry().getIsoCode();
            if (countryCode == null) {
                return new GeoIPLanguage("en", "us");
            }

            String languageCode = switch (countryCode) {
                case "JP" -> "ja";
                case "CN", "TW" -> "zh";
                case "KR" -> "ko";
                case "FR" -> "fr";
                case "DE", "AT" -> "de";
                case "ES", "MX", "AR", "CO" -> "es";
                case "BR", "PT" -> "pt";
                case "RU" -> "ru";
                case "IT" -> "it";
                case "NL" -> "nl";
                case "PL" -> "pl";
                case "TR" -> "tr";
                case "SA", "EG", "AE", "IQ" -> "ar";
                case "IN" -> "hi";
                case "ID" -> "id";
                case "TH" -> "th";
                case "VN" -> "vi";
                case "IR" -> "fa";
                case "PK" -> "ur";
                case "BD" -> "bn";
                case "UA" -> "uk";
                case "GR" -> "el";
                case "CZ" -> "cs";
                case "SK" -> "sk";
                case "HU" -> "hu";
                case "RO" -> "ro";
                case "BG" -> "bg";
                case "HR" -> "hr";
                case "RS" -> "sr";
                case "SI" -> "sl";
                case "SE" -> "sv";
                case "NO" -> "no";
                case "DK" -> "da";
                case "FI" -> "fi";
                case "EE" -> "et";
                case "LV" -> "lv";
                case "LT" -> "lt";
                case "IL" -> "he";
                case "AF" -> "ps";
                case "ET" -> "am";
                case "KE", "TZ" -> "sw";
                case "GE" -> "ka";
                case "AM" -> "hy";
                case "AZ" -> "az";
                case "KZ" -> "kk";
                case "UZ" -> "uz";
                case "NP" -> "ne";
                case "LK" -> "si";
                case "MM" -> "my";
                case "KH" -> "km";
                case "LA" -> "lo";
                case "MN" -> "mn";
                case "AL" -> "sq";
                case "MK" -> "mk";
                case "BY" -> "be";
                case "IS" -> "is";
                //case "IE", "US", "GB", "CA", "AU", "NZ", "ZA", "NG", "GH" -> "en";
                case "MY" -> "ms";
                case "PH" -> "tl";
                default -> "en";
            };

            return new GeoIPLanguage(languageCode, countryCode.toLowerCase());
        } catch (Exception ignored) {
            return new GeoIPLanguage("en", "us");
        }
    }

    /**
     * Closes the GeoIP.dat database.
     */
    @Override
    public void close() throws IOException {
        this.reader.close();
    }

    public record GeoIPResult(String country, String countryCode, String region, String city, Double latitude, Double longitude) {}
    public record GeoIPLanguage(String languageCode, String countryCode) {}
}