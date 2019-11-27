package pl.skowrondariusz.TransportApplication.security.service;

import org.springframework.social.facebook.api.*;

import java.util.List;

public class UserOperationsImpl implements UserOperations {
    @Override
    public User getUserProfile() {
        return null;
    }

    @Override
    public User getUserProfile(String userId) {
        return null;
    }

    @Override
    public byte[] getUserProfileImage() {
        return new byte[0];
    }

    @Override
    public byte[] getUserProfileImage(String userId) {
        return new byte[0];
    }

    @Override
    public byte[] getUserProfileImage(ImageType imageType) {
        return new byte[0];
    }

    @Override
    public byte[] getUserProfileImage(String userId, ImageType imageType) {
        return new byte[0];
    }

    @Override
    public byte[] getUserProfileImage(Integer width, Integer height) {
        return new byte[0];
    }

    @Override
    public byte[] getUserProfileImage(String userId, Integer width, Integer height) {
        return new byte[0];
    }

    @Override
    public List<Permission> getUserPermissions() {
        return null;
    }

    @Override
    public List<UserIdForApp> getIdsForBusiness() {
        return null;
    }

    @Override
    public List<PlaceTag> getTaggedPlaces() {
        return null;
    }

    @Override
    public PagedList<Reference> search(String query) {
        return null;
    }


    static final String[] PROFILE_FIELDS = {
            "id", "about", "age_range", "birthday", "cover", "currency", "devices", "education", "email",
            "favorite_athletes", "favorite_teams", "first_name", "gender", "hometown", "inspirational_people", "installed", "install_type",
            "is_verified", "languages", "last_name", "link", "locale", "location", "meeting_for", "middle_name", "name", "name_format",
            "political", "quotes", "payment_pricepoints", "relationship_status", "religion", "security_settings", "significant_other",
            "sports", "test_group", "timezone", "third_party_id", "updated_time", "verified", "video_upload_limits", "viewer_can_send_gift",
            "website", "work"
    };
}
