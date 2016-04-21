package com.audreytroutt.android.lunchroulette.data;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by audrey on 4/21/16.
 */
public class LunchPerson {

    public enum LunchDay {
        MONDAY(1), TUESDAY(2), WEDNESDAY(4), THURSDAY(8), FRIDAY(16), SATURDAY(32), SUNDAY(64);

        private final int bitValue;

        LunchDay(int bitValue) {
            this.bitValue = bitValue;
        }

        public int bit() {
            return bitValue;
        }
    }

    private String id;
    private String displayName;
    private String authProvider; // everyone is twitter for now
    private String location;
    private int availableLunchDaysBitMask;
    private boolean registrationComplete;
    private String avatarUrl;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public int getAvailableLunchDaysBitMask() {
        return availableLunchDaysBitMask;
    }

    public void setAvailableLunchDaysBitMask(int availableLunchDaysBitMask) {
        this.availableLunchDaysBitMask = availableLunchDaysBitMask;
    }

    public Set<LunchDay> getLunchDays() {
        Set<LunchDay> lunchDays = new HashSet<LunchDay>();
        for (LunchDay day : LunchDay.values()) {
            if ((availableLunchDaysBitMask & day.bit()) == day.bit()) {
                lunchDays.add(day);
            }
        }
        return lunchDays;
    }

    public void addLunchDay(LunchDay day) {
        // OR the new day with the existing bit mask
        availableLunchDaysBitMask |= day.bit();
    }

    public void removeLunchDay(LunchDay day) {
        // existing bit mask AND NOT day
        availableLunchDaysBitMask &= ~day.bit();
    }

    public String getAuthProvider() {
        return authProvider;
    }

    public void setAuthProvider(String authProvider) {
        this.authProvider = authProvider;
    }

    public boolean isRegistrationComplete() {
        return registrationComplete;
    }

    public void setRegistrationComplete(boolean registrationComplete) {
        this.registrationComplete = registrationComplete;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }
}
