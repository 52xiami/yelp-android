package com.yelp.clientlib.entities;

import com.fasterxml.jackson.databind.JsonNode;

import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;

public class BusinessTest {

    @Test
    public void testDeserializeFromJson() throws IOException {
        JsonNode businessNode = JsonTestUtils.getBusinessResponseJsonNode();
        Business business = JsonTestUtils.deserializeJson(businessNode.toString(), Business.class);

        Assert.assertEquals(businessNode.path("display_phone").textValue(), business.displayPhone());
        Assert.assertEquals(businessNode.path("eat24_url").textValue(), business.eat24Url());
        Assert.assertEquals(businessNode.path("id").textValue(), business.id());
        Assert.assertEquals(businessNode.path("image_url").textValue(), business.imageUrl());
        Assert.assertEquals(businessNode.path("is_claimed").asBoolean(), business.isClaimed());
        Assert.assertEquals(businessNode.path("is_closed").asBoolean(), business.isClosed());
        Assert.assertEquals(new Long(businessNode.path("menu_date_updated").asLong()), business.menuDateUpdated());
        Assert.assertEquals(businessNode.path("menu_provider").textValue(), business.menuProvider());
        Assert.assertEquals(businessNode.path("mobile_url").textValue(), business.mobileUrl());
        Assert.assertEquals(businessNode.path("name").textValue(), business.name());
        Assert.assertEquals(businessNode.path("phone").textValue(), business.phone());
        Assert.assertEquals(businessNode.path("reservation_url").textValue(), business.reservationUrl());
        Assert.assertEquals(new Double(businessNode.path("rating").asDouble()), business.rating());
        Assert.assertEquals(businessNode.path("rating_img_url").textValue(), business.ratingImgUrl());
        Assert.assertEquals(businessNode.path("rating_img_url_large").textValue(), business.ratingImgUrlLarge());
        Assert.assertEquals(businessNode.path("rating_img_url_small").textValue(), business.ratingImgUrlSmall());
        Assert.assertEquals(new Integer(businessNode.path("review_count").asInt()), business.reviewCount());
        Assert.assertEquals(businessNode.path("snippet_image_url").textValue(), business.snippetImageUrl());
        Assert.assertEquals(businessNode.path("snippet_text").textValue(), business.snippetText());
        Assert.assertEquals(businessNode.path("url").textValue(), business.url());

        // The following objects are tested in their own tests.
        Assert.assertNotNull(business.categories());
        Assert.assertNotNull(business.deals());
        Assert.assertNotNull(business.giftCertificates());
        Assert.assertNotNull(business.location());
        Assert.assertNotNull(business.reviews());

    }

    @Test
    public void testDeserializeNullableAttributes() throws IOException {
        String businessJsonString = "{\"name\":\"Yelp\"}";
        Business business = JsonTestUtils.deserializeJson(businessJsonString, Business.class);

        Assert.assertEquals("Yelp", business.name());
    }
}
