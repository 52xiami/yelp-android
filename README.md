# yelp-android
A Java library for the Yelp API. 

## Installation
// TODO

## Usage

### Basic usage
This library uses an API object to query against the API. Make an API object by using `YelpAPIFactory` to create a
`YelpAPI` with you API keys.
```
YelpAPIFactory apiFactory = new YelpAPIFactory(consumerKey, consumerSecret, token, tokenSecret);
YelpAPI yelpAPI = apiFactory.createAPI();
```

### [Search API](http://www.yelp.com/developers/documentation/v2/search_api)
Once you have a yelpAPI you can use `search` to make a request to the Search API.

The general params and locale options should be pass to the method as a `Map<String, String>`.
```
Map<String, String> params = new HashMap<>();

// General params
params.put("term", "food");
params.put("limit", "3");

// Locale params
params.put("lang", "fr");

Call<SearchResponse> call = yelpAPI.search('San Francisco', params);
call.execute();
```

The full list of parameters can be found in the [Search API Documentation](https://www.yelp.com/developers/documentation/v2/search_api).

Additionally there are two more search methods for searching by a bounding box or for geographical coordinates:
```
// bounding box
BoundingBoxOptions bounds = BoundingBoxOptions.builder()
        .swLatitude(37.7577)
        .swLongitude(-122.4376)
        .neLatitude(37.785381)
        .neLongitude(-122.391681).build();

yelpAPI.search(bounds, params)

// coordinates
CoordinateOptions coordinate = CoordinateOptions.builder()
        .latitude(37.7577)
        .longitude(-122.4376).build();

Call<SearchResponse> call = yelpAPI.search(coordinate, params)
call.execute();
```

### [Business API](http://www.yelp.com/developers/documentation/v2/business)
To query the Business API, use the `getBusiness` function with a business id. You can also pass in locale parameters in
 a `Map<String, String>` as specified in the [Business API Documentation](http://www.yelp.com/developers/documentation/v2/business).
```
yelpAPI.getBusiness("yelp-san-francisco");
```
You can pass in locale information as well.
```
Map<String, String> params = new HashMap<>();
params.put("lang", "fr");

Call<Business> call = yelpAPI.getBusiness('yelp-san-francisco', params);
call.execute();
```

### [Phone Search API](http://www.yelp.com/developers/documentation/v2/phone_search)
To query the Phone Search API, use the `getPhoneSearch` function with a phone number. Additional parameters can be
passed in by using a `Map<String, String>` as specified in [Phone Search API Documentation](https://www.yelp.com/developers/documentation/v2/phone_search).
```
yelpAPI.getPhoneSearch("+15555555555");
```
You can pass in country code information as well
```
Map<String, String> params = new HashMap<>();
params.put("cc", "US");
params.put("category", "fashion");

Call<SearchResponse> call = yelpAPI.getPhoneSearch("5555555555", params);
call.execute();
```

## Responses
Responses from the API are parsed into Java objects.

Search and phone search responses are parsed into `SearchResponse` objects.
```
Map<String, String> params = new HashMap<>();
params.put("term", "yelp");

Call<SearchResponse> call = yelpAPI.search("San Francisco", params);
Response<SearchResponse> response = call.execute();

SearchResponse searchResponse = response.body();
ArrayList<Business> businesses = searchResponse.businesses();

// 'JapaCurry Truck'
String businessName = businesses.get(0).name();

// 4.0
String rating = businesses.get(0).rating();
```

Business responses are parsed into `BusinessResponse` objects.
```
Call<Business> call = yelpAPI.business("japacurry-truck-san-francisco");
Response<Business> response = call.execute();

Business business = response.body();
// 'JapaCurry Truck'
String businessName = business.name();

// 4.0
String rating = business.rating();
```

For a full list of available response fields, take a look at the documentation or the classes defined in [com.yelp
.clientlib.entities](https://github.com/Yelp/yelp-android/tree/add_readme/src/main/java/com/yelp/clientlib/entities)

## Asynchronous Requests
This library uses [Retrofit](http://square.github.io/retrofit/) as the HTTP client, use #enqueue in Call object to 
enqueue a Callback function.
```
Callback<Business> businessCallback = new Callback<Business>() {
    @Override
    public void onResponse(Response<Business> response, Retrofit retrofit) {
        Business business = response.body();
        // Update UI text with the result.
    }
    @Override
    public void onFailure(Throwable t) {
        // HTTP error happened.
    }
};

Call<Business> call = yelpAPI.getBusiness(businessId);
call.enqueue(businessCallback);
```

You can cancel the asynchronous request by simply use #cancel.
```
Call<Business> call = yelpAPI.getBusiness(businessId);
call.enqueue(businessCallback);
call.cancel()
```

## Contributing
1. Fork it ( http://github.com/yelp/yelp-android/fork )
2. Create your feature branch (git checkout -b my-new-feature)
3. Commit your changes (git commit -am 'Add some feature')
4. Push to the branch (git push origin my-new-feature)
5. Create new Pull Request

//TODO: Add information about test suits.