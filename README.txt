------------------------------- Mobile Apps: Android-------------------------------------------------

Coach training app
------------------

Coach app gebruik drie api

Youtube Data API V3 	--> om gebruik te maken van youtube fimpljes
Maps SDK for Android 	--> google maps carte op de applicatie weer te geven
places API 		--> om nearby places te zoeken op basis van een bepaalde de locatie

API keys moet je op de volgende instellen:
Youtube Data API V3 	--> VideoActivity
Maps SDK for Android	--> values/google_maps_api.xml(google_maps_key)
places API 		--> values/google_maps_api.xml(browser_key)

VEREISTEN
---------
*Minstens 2 activities waartussen informatie wordt uitgewisseld met behulp van Intents
Het gebruik van Fragments die met elkaar communiceren in minstens 1 activity van je applicatie
*Het gebruik van minstens 1 activity waarin een complexe layout wordt opgebouwd
*Het gebruik van threading voor het afhalen van elementen (afbeeldingen, muziek, video of andere artifacts) van het internet en/of het gebruik van minstens 1 Service of IntentService
*Aanwezigheid van action bar of navigation drawer voor de navigatie
*Persistentie van gegevens, gebruikmakende van SQLite of een ORM-oplossing
*Ondersteuning van apparaten met minimum API 19 (Android versie 4.4)
*Minimaal ondersteuning van hdpi, xhdpi en xxhdpi schermen
*Ondersteuning van landscape/portrait mode (m.a.w. verschillende layoutbestanden) voor minstens 1 activity
*Gebruik van JUnit-framework voor het schrijven van testen voor je applicatie (minimum 1)
*Toepassing van de navigation design patterns binnen Android
*Ondersteuning voor minstens Nederlands en Engels. Extra talen mogen voorzien, maar tellen niet voor extra punten.


Extra
-----
Automatische UI Testing
Testen met behulp van monkeyrunner
*Gebruik van een broadcast receiver
Implementatie van een content provider
Het gebruik van een cloud provider voor de opslag van gegevens
Multi-touch ondersteuning
*Ondersteuning van 1 of meerdere widgets
Gebruik van OpenGL
Gebruik van transities
*Sensoren (GPS, beweging, …)
*Bluetooth, NFC
Android Wear uitbreidingen op je app
*App is voorbereid voor publicatie of werd gepubliceerd

BRONNEN:
-------
(Bronnen komen ook in de code voor maar hier is er een compleet lijst van alle gebruikte bronnen)


https://www.youtube.com/watch?v=hD0rybdMtGk
https://www.youtube.com/watch?v=SWsuijO5NGE
https://www.youtube.com/watch?v=CVI4CfdtbkA
https://developer.android.com/reference/android/content/BroadcastReceiver
https://developer.android.com/training/notify-user/build-notification
https://developer.android.com/reference/android/widget/Adapter
https://www.youtube.com/watch?v=vXRoVIGttO4
https://stackoverflow.com/questions/37821148/why-cannot-i-import-androidjunit4-and-activitytestrule-into-my-unit-test-class
https://stackoverflow.com/questions/25187400/updating-textview-on-activity-once-data-in-adapter-class-is-changed
https://developers.google.com/places/web-service/supported_types
https://developers.google.com/places/web-service/search
https://www.youtube.com/watch?v=wKrYU97Wwg4&t=160s
http://pojo.sodhanalibrary.com/Convert
https://www.youtube.com/watch?v=ARezg1D9Zd0
https://www.youtube.com/watch?v=Q5Hfr91Vatw
http://www.androiddocs.com/guide/practices/screens_support.html
https://www.youtube.com/watch?v=dxeKPU0MBU8&index=3&list=PLUaDUKMTvImX6D-4OMDXF0PNLfGQWSlSU&t=99s
https://www.youtube.com/watch?v=6nmnWi_hfC0&list=PLBNheBxhHLQxmCCiHGkXBAIsC1VKpZkSe&index=2
https://developer.android.com/studio/intro/
https://www.youtube.com/watch?v=10SkTlR_t2I
https://www.youtube.com/watch?v=BX_0RItiwcc&t=70s
https://www.youtube.com/watch?v=yOZFId3uOrs
https://github.com/jdulal/AndroidMaterialDashboard
https://openclassrooms.com/fr/courses/3499366-developpez-une-application-pour-android/3568536-adaptez-l-interface-en-fonction-de-la-configuration-du-smartphone#_=_
https://openclassrooms.com/fr/courses/3499366-developpez-une-application-pour-android/3568546-affichez-une-actionbar-et-un-menu
https://stackoverflow.com/questions/20564519/using-an-xml-string-within-a-baseadapter
https://stackoverflow.com/questions/16333754/how-to-customize-listview-using-baseadapter
https://github.com/codepath/android_guides/wiki/Using-a-BaseAdapter-with-ListView
https://developer.android.com/reference/android/widget/LinearLayout#attr_android:orientation
https://www.youtube.com/watch?v=-YCUrHFUxlA&t=4s
https://icones8.fr/icon/set/historique/material
https://www.youtube.com/watch?v=zcnT-3F-9JA
https://www.youtube.com/watch?v=Q5Ndr944U2o
https://stackoverflow.com/questions/45422761/how-can-i-change-colorprimarydark-for-just-one-activity
https://developer.android.com/reference/android/widget/TimePicker
https://www.youtube.com/playlist?list=PLRR7wjtXb1cB-jibndUw-qv79O2KQkG6U
https://www.youtube.com/watch?v=y8R2C86BIUc&list=PLgCYzUzKIBE8KHMzpp6JITZ2JxTgWqDH2&index=1
https://developer.android.com/guide/topics/connectivity/bluetooth
https://developer.android.com/reference/android/content/BroadcastReceiver
https://developer.android.com/guide/components/services
https://www.youtube.com/watch?v=u9gDooP8IhU&list=PLGCjwl1RrtcR1j6EmpBxJyJYowK2QIsdT&index=1
https://www.flaticon.com/
https://www.youtube.com/watch?v=p2ffzsCqrs8
https://developer.android.com/guide/topics/ui/dialogs
https://developers.google.com/places/web-service/supported_types
https://developers.google.com/places/web-service/search
https://www.youtube.com/watch?v=wKrYU97Wwg4&t=160s
http://pojo.sodhanalibrary.com/Convert
https://developer.android.com/reference/android/app/Service
https://developer.android.com/guide/components/fragments
https://developer.android.com/guide/topics/ui/layout/recyclerview
https://www.youtube.com/watch?v=wKrYU97Wwg4&t=160s
https://www.youtube.com/playlist?list=PLzV8uWUcseN91J9bLuVLnlmWlE6wIWTiY
https://developers.google.com/places/web-service/
https://developer.android.com/reference/java/io/Serializable
https://www.supinfo.com/articles/single/1550-serialisation-objet-android
http://www.coderzheaven.com/2012/07/25/serialization-android-simple-example/
https://developer.android.com/reference/java/util/Calendar
https://www.youtube.com/watch?v=xs5406vApTo
https://www.youtube.com/watch?v=UWkEIrBaXqw
https://www.youtube.com/watch?v=7d6iKupzkEg
https://www.youtube.com/watch?v=mN6kM_1M0cY&t=926s
https://www.youtube.com/watch?v=AS92bq3XxkA&t=13s
https://developer.android.com/guide/components/services
https://www.youtube.com/watch?v=p2ffzsCqrs8
https://www.youtube.com/watch?v=zCYQBIcePaw&t=412s
https://developer.android.com/reference/android/media/MediaPlayer
https://developers.google.com/youtube/android/player/




