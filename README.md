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

This app was crafted during SnipSnap In-house Hackathon 2106. Dev by [Audrey Troutt](https://github.com/atroutt) art and design by Jeff Fuller.

## HACKATHON NOTES

### THURSDAY

* Sketched assets for Jeff
* Created (basic) Login screen
* Created (basic) Lunch List screen
* Integrated twitter auth
* Integrated Firebase for data store and auth with Twitter
* Added ButterKnife for view injection (less boilerplate code)
* Learned about hiding sensitive data in public git repos.
* Created (basic) Lunch spinner screen and got the image spinning.
* Added sticky login (you are in until you log out)
