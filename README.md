##SETUP

1. To support Fabric, create fabric.properties and put your own fabric keys:

```
twitterPluginId=THIS MIGHT BE AUTO GENERATED WHEN YOU ADD THE PLUGIN
apiKey=YOUR_FABRIC_ORG_API_KEY
```

You can get your Fabric API key from your Fabric organization page (something like https://fabric.io/settings/organizations/YOUR_ORG_ID)

2. To support login with Twitter, create secrets.xml and include the following keys:

```
<?xml version="1.0" encoding="utf-8"?>
<resources>
    <string name="twitter_key">YOUR_TWITTER_APP_KEY</string>
    <string name="twitter_secret">YOUR_TWITTER_APP_SECRET</string>
</resources>
```

You can get a new key/secret combo by [creating a twitter app](https://apps.twitter.com/app/new).

## About

This app was crafted during SnipSnap In-house Hackathon April 21-22,2106. Dev by [Audrey Troutt](https://github.com/atroutt) art and design by Jeff Fuller.

### Done during hackathon

* Sketched assets for Jeff
* Created (basic) Login screen
* Created (basic) Lunch List screen
* Integrated twitter auth
* Integrated Firebase for data store and auth with Twitter
* Added ButterKnife for view injection (less boilerplate code)
* Learned about hiding sensitive data in public git repos.
* Created (basic) Lunch spinner screen and got the image spinning.
* Added sticky login (you are in until you log out)
* Add utility to get current user profile information
* Add Glide for image loading (alternative to picasso)
* Put more (fake) people in firebase
* Created onboarding/setting screen for setting when you are available to lunch
* Add Jeff's assets and colors
* Prevent app from rotating on full screen activities

### For some day when I come back to finish this

* Fix match screen so it can appear without crashing the app
* Implement spinner algorithm
* Persist match to Firebase
* Add recycler view to lunch list for lunches
* Persist lunch availability to firebase
* Implement invite-only mechanism so that you have to be pre-approved to join the community.
